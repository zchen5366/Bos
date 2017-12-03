package com.chenzhou.bos.service.action.system;

import java.util.List;

import com.chenzhou.bos.bean.system.User;

public interface UserService {

	List<User> findAll();

	void save(User model, Integer[] roleIds);

}
