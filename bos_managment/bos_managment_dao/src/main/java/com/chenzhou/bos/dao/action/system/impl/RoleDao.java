package com.chenzhou.bos.dao.action.system.impl;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.system.Role;
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

	@Query("select r from Role r inner join r.users u where u.id=?")
	Set<Role> findByUser(int id);

}
