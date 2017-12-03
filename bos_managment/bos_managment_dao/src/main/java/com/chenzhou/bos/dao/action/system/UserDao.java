package com.chenzhou.bos.dao.action.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chenzhou.bos.bean.system.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

	/*@Query("select u from User inner join u.roles r where r.id=?")
	List<User> findByPermissionId(int i);*/

}
