package com.chenzhou.bos.service.action.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.base.SubArea;

public interface SubAreaService {

	void save(SubArea model);

	Page<SubArea> findByPage(Pageable pageable);

	List<SubArea> findByfixedAreaIsNull();

	List<SubArea> findByFixedArea(String fixedAreaId);

	List<SubArea> findAll();

	List<Object[]> getChartData();

}
