package com.chenzhou.bos.service.action.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.system.Permission;
import com.chenzhou.bos.dao.action.system.impl.PermissionDao;
import com.chenzhou.bos.service.action.system.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public Page<Permission> findAll(Pageable pageable) {
		return permissionDao.findAll(pageable);
	}

	@Override
	public void save(Permission model) {
		permissionDao.save(model);
	}

	@Override
	public List<Permission> findAll() {
		return permissionDao.findAll();
	}

}
