package com.chenzhou.bos.service.action.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chenzhou.bos.bean.base.Standard;

public interface StandardService {
	void save(Standard standard);

	Page<Standard> findByPage(Pageable pageable);

	List<Standard> findAll();
}
