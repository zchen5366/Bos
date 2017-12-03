package com.chenzhou.bos.dao.action.base.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.take_delivery.WayBill;
@Repository
public interface WayBillDao extends JpaRepository<WayBill, Integer> {

}
