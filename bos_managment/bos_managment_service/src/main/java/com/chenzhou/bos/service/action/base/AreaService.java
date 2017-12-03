package com.chenzhou.bos.service.action.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.base.Area;

public interface AreaService {

	void save(List<Area> list);

	Page<Area> findAll(Pageable pageable);

	List<Area> findAll();

	List<Area> findLike(String q);
	
}
