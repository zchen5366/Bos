package com.chenzhou.bos.service.action.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.Area;
import com.chenzhou.bos.dao.base.AreaDao;
import com.chenzhou.bos.service.action.base.AreaService;
@Transactional
@Service("areaService")
public class AreaServiceImpl implements AreaService {
	@Resource(name="areaDao")
	private AreaDao areaDao;
	
	@Override
	public void save(List<Area> list) {
		areaDao.save(list);
	}

	@Override
	public Page<Area> findAll(Pageable pageable) {
		return areaDao.findAll(pageable);
	}

	@Override
	public List<Area> findAll() {
		return areaDao.findAll();
	}

	@Override
	public List<Area> findLike(String q) {
		return areaDao.findLike("%"+q+"%");
	}

}
