package com.chenzhou.bos.service.action.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.system.Menu;
import com.chenzhou.bos.bean.system.User;

public interface MenuService {

	List<Menu> findAll();

	void save(Menu model);

	Page<Menu> findAll(Pageable pageable);

	List<Menu> findByUser(User user);

}
