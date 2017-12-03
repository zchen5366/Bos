package com.chenzhou.bos.service.action.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.system.Menu;
import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.dao.action.system.impl.MenuDao;
import com.chenzhou.bos.service.action.system.MenuService;
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findAll() {
		return menuDao.findByParentMenuIsNull();
	}

	@Override
	public void save(Menu model) {
		if(model.getParentMenu()!=null && model.getParentMenu().getId()==0) {
			model.setParentMenu(null);
		}
		menuDao.save(model);
	}

	@Override
	public Page<Menu> findAll(Pageable pageable) {
		return menuDao.findAll(pageable);
	}

	@Override
	public List<Menu> findByUser(User user) {
		if("admin".equals(user.getUsername())) {
			return menuDao.findByParentMenuIsNull();
		}
		return menuDao.findByUser(user.getId());
	}

}
