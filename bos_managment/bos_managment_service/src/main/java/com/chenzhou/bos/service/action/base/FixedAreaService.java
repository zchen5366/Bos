package com.chenzhou.bos.service.action.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.base.FixedArea;

public interface FixedAreaService {

	void save(FixedArea model);

	Page<FixedArea> findAll(Pageable pageable);

	void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);

	void assignSubArea2FixedArea(List<String> subAreaIds, String id);

}
