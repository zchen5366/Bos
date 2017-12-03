package com.chenzhou.bos.dao.action.system.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.system.Permission;
@Repository
public interface PermissionDao extends JpaRepository<Permission, Integer> {

	
	@Query("select p from Permission p inner join p.roles r inner join r.users u where u.id = ?")
	List<Permission> findPermissionByUserId(int id);

}
