package com.chenzhou.bos.service.action.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.chenzhou.bos.bean.base.Courier;

public interface CourierService {

	void save(Courier courier);

	Page<Courier> findByPage(Specification<Courier> specification, Pageable pageable);

	void batchDel(Integer id);

	List<Courier> findByDeltagIsNull();

}
