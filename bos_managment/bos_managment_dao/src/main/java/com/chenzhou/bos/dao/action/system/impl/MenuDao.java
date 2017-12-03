package com.chenzhou.bos.dao.action.system.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.system.Menu;
@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {

	List<Menu> findByParentMenuIsNull();

	@Query("select m from Menu m inner join m.roles r inner join r.users u where u.id=?")
	List<Menu> findByUser(int id);

}
