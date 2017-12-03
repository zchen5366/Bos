package com.chenzhou.bos.service.action.base.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chenzhou.bos.bean.base.Standard;
import com.chenzhou.bos.dao.base.StandardDao;
import com.chenzhou.bos.service.action.base.StandardService;

@Service("standardService")
public class StandardServiceImpl implements StandardService {

	@Resource(name="standardDao")
	private StandardDao standardDao;
	
	@Override
	public void save(Standard standard) {
		standardDao.save(standard);
	}

	@Override
	public Page<Standard> findByPage(Pageable pageable) {
		return standardDao.findAll(pageable);
	}

	@Override
	public List<Standard> findAll() {
		return standardDao.findAll();
	}
}
