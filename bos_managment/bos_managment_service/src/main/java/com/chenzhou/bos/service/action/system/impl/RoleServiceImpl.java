package com.chenzhou.bos.service.action.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.system.Menu;
import com.chenzhou.bos.bean.system.Permission;
import com.chenzhou.bos.bean.system.Role;
import com.chenzhou.bos.dao.action.system.impl.RoleDao;
import com.chenzhou.bos.service.action.system.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public Page<Role> findAll(Pageable pageable) {
		return roleDao.findAll(pageable);
	}

	@Override
	public void save(Role model, String menuIds, Integer[] permissionIds) {
		roleDao.save(model);
		//封装menu
		if(StringUtils.isNotEmpty(menuIds)){
			String[] split = menuIds.split(",");
			for (String menuId : split) {
				Menu menu = new Menu();
				menu.setId(Integer.parseInt(menuId));
				model.getMenus().add(menu);
			}
		}
		//封装permission
		if(permissionIds != null && permissionIds.length>0) {
			for (Integer permissionId : permissionIds) {
				Permission permission = new Permission();
				permission.setId(permissionId);
				model.getPermissions().add(permission);
			}
		}
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
