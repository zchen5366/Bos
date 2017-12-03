package com.chenzhou.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.base.FixedArea;
@Repository("fixedAreaDao")
public interface FixedAreaDao extends JpaRepository<FixedArea, String> {
	
}
