package com.chenzhou.bos.service.action.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.take_delivery.WayBill;
import com.chenzhou.bos.dao.action.base.impl.WayBillDao;
import com.chenzhou.bos.service.action.base.WayBillService;
@Transactional
@Service
public class WayBillServiceImpl implements WayBillService {

	@Resource
	private WayBillDao wayBillDao;
	
	@Override
	public void save(WayBill wayBill) {
		wayBillDao.save(wayBill);
	}

}
