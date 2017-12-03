package com.chenzhou.bos.service.action.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.system.Role;

public interface RoleService {

	Page<Role> findAll(Pageable pageable);

	void save(Role model, String menuIds, Integer[] permissionIds);

	List<Role> findAll();
	
}
