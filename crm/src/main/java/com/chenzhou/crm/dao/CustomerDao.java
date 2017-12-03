package com.chenzhou.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.crm.bean.Customer;

@Repository("customerDao")
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	//查询为空的客户放在多选框的左边
	List<Customer> findByFixedAreaIdIsNull();
	
	//查询关联此定区的所有的客户,放在多选框的右边
	@Query("from Customer where fixedAreaId=?")
	List<Customer> findByFixedAreaId(String fixedAreaId);
	
	@Modifying
	@Query("update Customer set fixedAreaId=null where fixedAreaId=?")
	void clearFixedAreaId(String id);

	@Modifying
	@Query("update Customer set fixedAreaId=?2 where id=?1")
	void assignCustomer(Integer customerId, String id);

	Customer findByTelephone(String telephone);

	@Modifying
	@Query("update Customer set type=1 where id=?")
	void activeCustomer(Integer id);

	//登录功能
	Customer findByTelephoneAndPassword(String telephone, String password);

	@Query("select fixedAreaId from Customer where address=?")
	String findFixedAreaIdByAddress(String address);
	
	
	
}
