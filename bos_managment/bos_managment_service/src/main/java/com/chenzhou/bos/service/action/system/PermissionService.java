package com.chenzhou.bos.service.action.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.system.Permission;

public interface PermissionService {

	Page<Permission> findAll(Pageable pageable);

	void save(Permission model);

	List<Permission> findAll();
	
}
