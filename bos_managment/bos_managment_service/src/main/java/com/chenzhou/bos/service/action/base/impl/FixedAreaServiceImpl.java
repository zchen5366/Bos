package com.chenzhou.bos.service.action.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.bean.base.FixedArea;
import com.chenzhou.bos.bean.base.TakeTime;
import com.chenzhou.bos.dao.action.base.impl.TakeTimeDao;
import com.chenzhou.bos.dao.base.CourierDao;
import com.chenzhou.bos.dao.base.FixedAreaDao;
import com.chenzhou.bos.dao.base.SubAreaDao;
import com.chenzhou.bos.service.action.base.FixedAreaService;

@Transactional
@Service("fixedAreaService")
public class FixedAreaServiceImpl implements FixedAreaService {

	@Resource(name="fixedAreaDao")
	private FixedAreaDao fixedAreaDao;
	
	@Resource(name="courierDao")
	private CourierDao courierDao; 
	
	@Resource
	private TakeTimeDao takeTimeDao;
	
	@Resource
	private SubAreaDao subAreaDao;
	
	@Override
	public void save(FixedArea model) {
		fixedAreaDao.save(model);
	}
	@Override
	public Page<FixedArea> findAll(Pageable pageable) {
		return fixedAreaDao.findAll(pageable);
	}
	//定区关联快递员
	@Override
	public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
		//根据id查找定区
		FixedArea fixedArea = fixedAreaDao.findOne(id);
		System.out.println(fixedArea);
		//根据courierId查询快递员
		Courier courier = courierDao.findOne(courierId);
		//根据takeTimeId查询排班时间
		TakeTime takeTime = takeTimeDao.findOne(takeTimeId);
		//把定区关联快递员
		fixedArea.getCouriers().add(courier);
		//把排版时间关联快递员
		courier.setTakeTime(takeTime);
	}
	@Override
	public void assignSubArea2FixedArea(List<String> subAreaIds, String id) {
		//把定期为id的分区fixedArea清空
		subAreaDao.clearFixedAreaId(id);
		for (String subAreaId : subAreaIds) {
			subAreaDao.assignFixedArea(subAreaId, id);
		}
	}

}
