package com.chenzhou.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.take_delivery.WorkBill;
@Repository
public interface WorkBillDao extends JpaRepository<WorkBill, Integer> {

}
