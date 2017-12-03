package com.chenzhou.bos.fore.web.action;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.fore.utils.MailUtils;
import com.chenzhou.bos.fore.utils.SmsUtils;
import com.chenzhou.crm.service.Customer;
import com.chenzhou.crm.service.impl.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private static final long serialVersionUID = 1L;
	
	private Customer model = new Customer();
	
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	
	//customerAction_sendValidateCode
	@Action("customerAction_sendValidateCode")
	public String sendValidateCode() throws IOException {
		//随机生成4位数的验证码
		String code = RandomStringUtils.randomNumeric(6);
//		String code = "123456";
		System.out.println(code);
		//将验证码和手机号存入session中
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(model.getTelephone(), code);
		//发送短信
		String msg = "尊敬的客户你好，您本次获取的验证码为:" + code;
		String result = SmsUtils.sendSmsByWebService(model.getTelephone(), msg);
		//验证result的长度是否是16位数并且不为空
		String flag;
		if(StringUtils.isNotEmpty(result) && result.length()==16) {
			flag = "ok";
		}else {
			flag = "error";
		}
		System.out.println(flag);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(flag);
		return NONE;
	}
	
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Resource(name="crmService")
	private CustomerService customerServcie;
	
	//customerAction_regist
	@Action(value="customerAction_regist",results={
			@Result(name="success",location="signup-success.html",type="redirect"),
			@Result(name="error",location="signup-fail.html",type="redirect")})
	public String register() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		//通过手机号得到存入session的验证码
		String code = session.get(model.getTelephone()).toString();
		//比对从页面传入的验证码和放入session的验证码是否一致
		if(checkcode!=null && checkcode.equals(code)) {
			customerServcie.regist(model);
			//注册后生成36位激活码
			String activeCode = RandomStringUtils.randomNumeric(36);
//			String activeCode = "123456";
			//把激活码存进redis中
			redisTemplate.opsForValue().set(model.getTelephone(), activeCode, 1, TimeUnit.DAYS);
			//发送邮件
			String emailBody = "欢迎注册速运快递,请点击本<a href='http://localhost:8280/bos_fore/customerAction_activeCustomer?telephone="+model.getTelephone()+"&activeCode="+activeCode+"'>链接</a>激活";
//			MailUtils.sendMail(model.getEmail(), "速运快递邮件激活", emailBody);
			
			//用消息队列来发送邮件
			jmsTemplate.send("emailQuene", new MessageCreator(){

				@Override
				public Message createMessage(Session session) throws JMSException {
					MapMessage mapMessage = session.createMapMessage();
					mapMessage.setString("email", model.getEmail());
					mapMessage.setString("subject", "速运快递邮件激活");
					mapMessage.setString("emailBody", emailBody);
					return mapMessage;
				}
			});
			
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	private String activeCode;
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	
	@Resource
	private JmsTemplate jmsTemplate;
	
	//customerAction_activeCustomer注册激活
	@Action(value="customerAction_activeCustomer",results={
			@Result(name="success",location="signup-success.html",type="redirect"),
			@Result(name="error",location="signup-fail.html",type="redirect"),
			@Result(name="actived",location="login.html",type="redirect")})
	public String activeCustomer() {
		//根据telephone来查询客户
		Customer customer = customerServcie.findByTelephone(model.getTelephone());
		System.out.println(customer);
		//比对存入redis中的激活码和请求的激活码是否相同
		String serverCode = redisTemplate.opsForValue().get(model.getTelephone());
		System.out.println(serverCode);
		System.out.println(activeCode);
		if(serverCode!=null && serverCode.equals(activeCode)) {
			
			if(customer.getType()!=null && customer.getType()==1) {
				return "actived";
			}
			//如果用户未激活就把type改为1
			customerServcie.activeCustomer(customer.getId());
			//如果已经激活跳转到已激活页面并清空
			redisTemplate.delete(model.getTelephone());
			return SUCCESS;
		}
		return ERROR;
	}
	
	//登录的功能
	@Action(value="customerAction_login",results={@Result(name="success",location="index.html",type="redirect"),
												  @Result(name="error",location="login.html",type="redirect")})
	public String login() {
		Customer customer = customerServcie.findByTelephoneAndPassword(model.getTelephone(), model.getPassword());
		String serverCode = (String) ActionContext.getContext().getSession().get("validateCode");
//		System.out.println(customer);
		if(customer!=null && customer.getType()==1 && serverCode!=null && serverCode.equals(checkcode)) {
			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public Customer getModel() {
		return model;
	}

}
