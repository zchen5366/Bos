package com.chenzhou.bos.service.action.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.TakeTime;
import com.chenzhou.bos.dao.action.base.impl.TakeTimeDao;
import com.chenzhou.bos.service.action.base.TakeTimeService;

@Transactional
@Service
public class TakeTimeServiceImpl implements TakeTimeService {
	
	@Resource
	private TakeTimeDao takeTimeDao;
	
	@Override
	public List<TakeTime> findAll() {
		return takeTimeDao.findAll();
	}

}
