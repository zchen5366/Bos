package com.chenzhou.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.crm.bean.Customer;
import com.chenzhou.crm.dao.CustomerDao;
import com.chenzhou.crm.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao customerDao;

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> findByFixedAreaIdIsNull() {
		return customerDao.findByFixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findByFixedAreaId(String fixedAreaId) {
		return customerDao.findByFixedAreaId(fixedAreaId);
	}

	@Override
	public void assignCustomers2FixedArea(List<Integer> customerIds, String id) {
		customerDao.clearFixedAreaId(id);
		for (Integer customerId : customerIds) {
			customerDao.assignCustomer(customerId,id);
		}
	}

	@Override
	public void regist(Customer customer) {
		customerDao.save(customer);
	}

	//激活用户
	@Override
	public Customer findByTelephone(String telephone) {
		return customerDao.findByTelephone(telephone);
	}

	@Override
	public void activeCustomer(Integer id) {
		customerDao.activeCustomer(id);
	}

	@Override
	public Customer findByTelephoneAndPassword(String telephone, String password) {
		return customerDao.findByTelephoneAndPassword(telephone,password);
	}

	@Override
	public String findFixedAreaIdByAddress(String address) {
		return customerDao.findFixedAreaIdByAddress(address);
	}
	
	
}











