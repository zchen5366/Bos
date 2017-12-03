package com.chenzhou.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.service.action.system.UserService;
import com.chenzhou.bos.web.action.common.CommonAction;
import com.opensymphony.xwork2.ActionContext;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class UserAction extends CommonAction<User> {
	private static final long serialVersionUID = 1L;

	public UserAction() {
		super(User.class);
	}
	
	private String validateCode;
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	@Resource
	private UserService userService; 
	
	@Action(value="userAction_login",results={@Result(name = "success", location = "/index.html", type = "redirect"),
											  @Result(name = "login", location = "/login.html", type = "redirect")})
	public String login() {
		//获得存入session中的验证码
		String serverCode = (String) ActionContext.getContext().getSession().get("validateCode");
		//判断验证码是否相同
		if(StringUtils.isNotEmpty(serverCode) && serverCode.equals(validateCode)) {
			//用shiro框架提供给的登录方法，获取subject
			Subject subject = SecurityUtils.getSubject();
			//创建用户名密码的令牌
			UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
			//执行登录
			subject.login(token);
			try {
				//登录成功后获取user
				User user = (User) subject.getPrincipal();
				//把user存入session中
				ActionContext.getContext().getSession().put("user", user);
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	@Action(value="userAction_logout",results={@Result(name="login",location="login.html",type="redirect")})
	public String logout() {
		//获得当前用户
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return LOGIN;
	}
	
	@Action("userAction_findAll")
	public String findAll() throws IOException {
		List<User> list = userService.findAll();
		list2Json(list, new String[]{"roles"});
		return NONE;
	}
	
	private Integer[] roleIds;
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	
	@Action(value="userAction_save",results={@Result(name="success",location="/pages/system/userlist.html",type="redirect")})
	public String save() {
		System.out.println("save方法");
		userService.save(model,roleIds);
		return SUCCESS;
	}
}
