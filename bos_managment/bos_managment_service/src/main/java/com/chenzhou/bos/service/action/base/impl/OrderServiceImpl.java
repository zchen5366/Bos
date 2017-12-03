package com.chenzhou.bos.service.action.base.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.Area;
import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.bean.base.FixedArea;
import com.chenzhou.bos.bean.base.SubArea;
import com.chenzhou.bos.bean.take_delivery.Order;
import com.chenzhou.bos.bean.take_delivery.WorkBill;
import com.chenzhou.bos.dao.action.base.impl.OrderDao;
import com.chenzhou.bos.dao.base.AreaDao;
import com.chenzhou.bos.dao.base.FixedAreaDao;
import com.chenzhou.bos.dao.base.WorkBillDao;
import com.chenzhou.bos.service.action.base.OrderService;
import com.chenzhou.crm.service.impl.CustomerService;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;
	
	@Resource
	private AreaDao areaDao;
	
	@Resource
	private FixedAreaDao fixedAreaDao;
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private WorkBillDao workBillDao;
	
	@Override
	public void save(Order order) {
		Area sendArea = order.getSendArea();
		Area recArea = order.getRecArea();
		//保顿订单的操作
		if(sendArea!=null) {
			String province = sendArea.getProvince();
			String district = sendArea.getDistrict();
			String city = sendArea.getCity();
			Area sendArea1 = areaDao.findByProvinceAndCityAndDistrict(province,city,district);
			System.out.println("sendArea1="+sendArea1);
			order.setSendArea(sendArea1);
			
			
		}
		if(recArea!=null) {
			String province1 = recArea.getProvince();
			String city1 = recArea.getCity();
			String district1 = recArea.getDistrict();
			Area recArea1 = areaDao.findByProvinceAndCityAndDistrict(province1,city1,district1);
			System.out.println("recArea1="+recArea1);
			order.setRecArea(recArea1);
		}
		order.setOrderNum(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setOrderTime(Calendar.getInstance().getTime());
		orderDao.save(order);
		if(sendArea!=null) {
			//完成自动分单的功能,只要客户address使用百度地图上的地址就并且能找到adress在客户表中对应的定区就可以完成自动分单
			if(order.getSendAddress()!=null) {
				System.out.println(order.getSendAddress());
				String fixedAreaId = customerService.findFixedAreaIdByAddress(order.getSendAddress());
				System.out.println(fixedAreaId);
				if(fixedAreaId!=null) {
					//根据定区id找到对应的定区
					FixedArea fixedArea = fixedAreaDao.findOne(fixedAreaId);
					//根据定区关联的快递员来完成自动分单
					Set<Courier> couriers = fixedArea.getCouriers();
					//随机得到一个快递员进行自动分单
					if(!couriers.isEmpty()) {
						findCourier(order,couriers);
						return;
					}
				}
				//如果定区id为null时，说明客户中查不到对应的Adress，name可以从定区关联的分区查找所对应的定区并分配快递员
				//查找此区域中所有的分区
				Set<SubArea> subareas = sendArea.getSubareas();
				if(!subareas.isEmpty()) {
					for (SubArea subArea : subareas) {
						//分区的关键字和辅助关键字
						String keyWords = subArea.getKeyWords();
						String assistKeyWords = subArea.getAssistKeyWords();
						//得到详细地址
						String sendAddress = order.getSendAddress();
						if(!sendAddress.isEmpty()) {
							if(sendAddress.contains(keyWords)||sendAddress.contains(assistKeyWords)) {
								FixedArea fixedArea = subArea.getFixedArea();
								Set<Courier> couriers = fixedArea.getCouriers();
								findCourier(order, couriers);
								return;
							}
						}
					}
				}
			}
			//======人工分单
			order.setOrderType("人工分单");
		}
	}
	
	private void findCourier(Order order, Set<Courier> couriers) {
		Courier courier = couriers.iterator().next(); 
		order.setCourier(courier);
		// 生成工单
        WorkBill workBill = new WorkBill();
        workBill.setAttachbilltimes(0);
        workBill.setBuildtime(new Date());
        workBill.setCourier(courier);
        workBill.setOrder(order);
        workBill.setPickstate("新单");
        workBill.setRemark(order.getRemark());
        workBill.setSmsNumber("123");
        workBill.setType("新");
        // 保存工单
        workBillDao.save(workBill);
        // 发送工单信息给快递员,此处打印日志进行模拟
        System.out.println("工单信息:请到" + order.getSendAddress() + "取件,客户电话:" + order.getSendMobile());
	}

}
