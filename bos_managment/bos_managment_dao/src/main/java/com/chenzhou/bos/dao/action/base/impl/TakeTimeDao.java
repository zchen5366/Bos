package com.chenzhou.bos.dao.action.base.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.base.TakeTime;

@Repository
public interface TakeTimeDao extends JpaRepository<TakeTime, Integer> {

}	
