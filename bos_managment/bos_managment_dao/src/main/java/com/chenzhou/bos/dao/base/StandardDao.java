package com.chenzhou.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chenzhou.bos.bean.base.Standard;

@Repository("standardDao")
@Transactional
public interface StandardDao extends JpaRepository<Standard, Integer> {
	//自定义查询，通过name查询
	Standard findByName(String name);
	
	@Query("from Standard where name=?")
	Standard findByNamexxx(String name);
	
	//根据名字模糊查询
	List<Standard> findByNameLike(String name);
	
	//查询名字为空的
	List<Standard> findByNameIsNull();
	
	//多条件查询
	Standard findByNameAndMaxLength(String name,Integer maxLength);
	
	// 使用JPQL进行非标准命名查询
	@Query("from Standard where name like ?1 and maxLength=?2")
	Standard findByNamexxxJPA(String name, Integer maxLength);
	
	@Query("from Standard where name like ?2 and maxLength=?1")
	Standard findByNamexxxJPA(Integer maxLength,String name);
	
	//使用原生sql语句查询
	@Query(value="select *from T_Standard where c_name like ?", nativeQuery=true)
	Standard findByNamexxxJPA(String name);
	
	//执行删除操作
	@Modifying
	@Query("delete from Standard where id=?")
	void deleteById(Integer id);
	
	//执行自定义修改的操作
	@Modifying
	@Query("update Standard set maxLength=?1 where name=?2")
	void updateMaxLengthByName(Integer maxLengthName,String name);
	
	
}



































