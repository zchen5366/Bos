package com.chenzhou.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.base.SubArea;

@Repository("subareaDao")
public interface SubAreaDao extends JpaRepository<SubArea, String> {

	List<SubArea> findByfixedAreaIsNull();
	
	@Query("from SubArea where fixedArea=(from FixedArea where id=?)")
	List<SubArea> findByFixedArea(String fixedAreaId);

	@Modifying
	@Query("update SubArea set fixedArea=null where fixedArea=(from FixedArea where id=?)")
	void clearFixedAreaId(String id);
	
	@Modifying
	@Query("update SubArea set fixedArea=(from FixedArea where id=?2) where id=?1")
	void assignFixedArea(String subAreaId, String id);
	
	@Query("select a.province,count(*) from SubArea s inner join s.area a group by a.province")
	List<Object[]> getChartData();
}
