import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenzhou.bos.bean.system.Permission;
import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.dao.action.system.UserDao;
import com.chenzhou.bos.dao.action.system.impl.PermissionDao;
import com.chenzhou.crm.service.Customer;
import com.chenzhou.crm.service.impl.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestCXF {
	
	@Resource(name="crmService")
	private CustomerService customerService;
	@Test
	public void test() {
		List<Customer> list = customerService.findAll();
		System.out.println(list);
	}
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void test2(){
		/*List<User> list = userDao.findByPermissionId(1001);
		for (User user : list) {
			System.out.println(user);
		}*/
	}
	
	@Resource
	private PermissionDao permissionDao;
	
	@Test
	public void test3(){
		/*List<Permission> list = permissionDao.findPermissionByUserId(1001);
		for (Permission permission : list) {
			System.out.println(permission);
		}*/
		List<Permission> findAll = permissionDao.findAll();
		for (Permission permission : findAll) {
			
			System.out.println(permission);
		}
	}
}
