package com.chenzhou.bos.service.action.system;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.chenzhou.bos.bean.system.Permission;
import com.chenzhou.bos.bean.system.Role;
import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.dao.action.system.UserDao;
import com.chenzhou.bos.dao.action.system.impl.PermissionDao;
import com.chenzhou.bos.dao.action.system.impl.RoleDao;
@Component
public class BosRealm extends AuthorizingRealm {

	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if(user.equals("admin")) {
			//如果是admin用户授予所有角色
			List<Role> list = roleDao.findAll();
			for (Role role : list) {
				info.addRole(role.getKeyword());
			}
			//授予所有权限
			List<Permission> list2 = permissionDao.findAll();
			for (Permission permission : list2) {
				info.addStringPermission(permission.getKeyword());
			}
		}else {
			//如果是普通用户则通过用户查询出来对应的角色和权限
			Set<Role> roles = roleDao.findByUser(user.getId());
			for (Role role : roles) {
				info.addRole(role.getKeyword());
			}
			List<Permission> list = permissionDao.findPermissionByUserId(user.getId());
			for (Permission permission : list) {
				info.addStringPermission(permission.getKeyword());
			}
		}
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//强转令牌
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//获取用户名
		String username = token.getUsername();
		//根据用户名查找数据库
		User user = userDao.findByUsername(username);
		if(user!=null) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
			return info;
		}
		return null;
	}

}
