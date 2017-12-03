package com.chenzhou.bos.service.action.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.dao.base.CourierDao;
import com.chenzhou.bos.service.action.base.CourierService;
@Transactional
@Service("courierService")
public class CourierServiceImpl implements CourierService {
	@Resource(name="courierDao")
	private CourierDao courierDao;
	
	@Override
	public void save(Courier courier) {
		courierDao.save(courier);
	}

	@Override
	public Page<Courier> findByPage(Specification<Courier> specification, Pageable pageable) {
		return courierDao.findAll(specification, pageable);
	}

	@RequiresPermissions("courier_batchDel")
	@Override
	public void batchDel(Integer id) {
		courierDao.batchDel(id);
	}

	@Override
	public List<Courier> findByDeltagIsNull() {
		return courierDao.findByDeltagIsNull();
	}
}
