package com.chenzhou.bos.service.action.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.system.Role;
import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.dao.action.system.UserDao;
import com.chenzhou.bos.service.action.system.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void save(User model, Integer[] roleIds) {
		userDao.save(model);
		if(roleIds!=null && roleIds.length>0) {
			for (Integer roleId : roleIds) {
				Role role = new Role();
				role.setId(roleId);
				model.getRoles().add(role);
			}
		}
	}

}
