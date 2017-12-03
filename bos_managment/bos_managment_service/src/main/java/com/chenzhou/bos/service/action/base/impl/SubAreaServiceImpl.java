package com.chenzhou.bos.service.action.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.SubArea;
import com.chenzhou.bos.dao.base.SubAreaDao;
import com.chenzhou.bos.service.action.base.SubAreaService;

@Transactional
@Service("subareaService")
public class SubAreaServiceImpl implements SubAreaService {
	
	@Resource(name="subareaDao")
	private SubAreaDao subareaDao;

	@Override
	public void save(SubArea model) {
		subareaDao.save(model);
	}

	@Override
	public Page<SubArea> findByPage(Pageable pageable) {
		return subareaDao.findAll(pageable);
	}
	
	@Override
	public List<SubArea> findByfixedAreaIsNull() {
		return subareaDao.findByfixedAreaIsNull();
	}

	@Override
	public List<SubArea> findByFixedArea(String fixedAreaId) {
		return subareaDao.findByFixedArea(fixedAreaId);
	}

	@Override
	public List<SubArea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Object[]> getChartData() {
		return subareaDao.getChartData();
	}

}
