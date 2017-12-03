package com.chenzhou.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.bean.base.FixedArea;

@Repository("courierDao")
public interface CourierDao extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier> {
	
	@Modifying
	@Query("update Courier set deltag=1 where id=?")
	void batchDel(Integer id);

	List<Courier> findByDeltagIsNull();

	List<Courier> findByFixedAreas(FixedArea fixedArea);
	
}
