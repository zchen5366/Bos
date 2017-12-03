package com.chenzhou.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.chenzhou.crm.bean.Customer;

@WebService
public interface CustomerService {
	List<Customer> findAll();
	List<Customer> findByFixedAreaIdIsNull();
	List<Customer> findByFixedAreaId(String fixedAreaId);
	void assignCustomers2FixedArea(List<Integer> customerIds,String id);
	void regist(Customer customer);
	//根据手机号获取用户
	Customer findByTelephone(String telephone);
	//激活用户
	void activeCustomer(Integer id);
	//登录功能通过手机号和密码测试时候登录成功
	Customer findByTelephoneAndPassword(String telephone,String password);
	//通过客户的地址进行自动分单
	String findFixedAreaIdByAddress(String address);
}
