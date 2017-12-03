package com.chenzhou.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.base.Area;
@Repository("areaDao")
public interface AreaDao extends JpaRepository<Area, String> {
	
	@Query("from Area where province like ?1 or city like ?1 or district like ?1 or citycode like ?1 or shortcode like ?1")
	List<Area> findLike(String q);
	
	Area findByProvinceAndCityAndDistrict(String province, String city, String district);
}
