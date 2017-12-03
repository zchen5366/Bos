package bos_managment_dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenzhou.bos.bean.base.Standard;
import com.chenzhou.bos.dao.base.StandardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataJpaTest {

	@Resource(name="standardDao")
	private StandardDao standardDao;
	
	@Test
	public void test01() {
		standardDao.findAll();
	}
	
	@Test
	public void testsave() {
		Standard entity = new Standard();
		entity.setName("吴京");
		entity.setMaxLength(100);
		standardDao.save(entity);
	}
	
	@Test
	public void testupdate() {
		Standard entity = new Standard();
		entity.setId(1);
		entity.setName("陈洲");
		entity.setMaxLength(200);
		standardDao.save(entity);
	}
	
	@Test
	public void testdelete() {
		standardDao.delete(2);
	}
	
	@Test
	public void testfindone() {
		Standard standard = standardDao.findOne(1);
		System.out.println(standard);
	}
	
	@Test
	public void testfindByName() {
		Standard standard = standardDao.findByName("陈洲");
		System.out.println(standard);
	}
	
	@Test
	public void testfindByNamexxx() {
		Standard standard = standardDao.findByNamexxx("陈洲");
		System.out.println(standard);
	}
	
	@Test
	public void testfindByNameLike() {
		List<Standard> list = standardDao.findByNameLike("%陈%");
		System.out.println(list);
	}
	
	@Test
	public void testfindByNameIsNull() {
		List<Standard> list = standardDao.findByNameIsNull();
		System.out.println(list);
	}
	
	//多条件查询
	@Test
	public void testfindByNameAndMaxLength() {
		Standard standard = standardDao.findByNameAndMaxLength("陈洲",200);
		System.out.println(standard);
	}
	
	//使用Jpql进取型非标准的语句查询
	@Test
	public void findByNamexxxJPA() {
		Standard standard = standardDao.findByNamexxxJPA(200,"%洲%");
		System.out.println(standard);
	}
	
	//使用原生sql进行查询
	@Test
	public void findByNamexxxJPA01() {
		Standard standard = standardDao.findByNamexxxJPA("%洲%");
		System.out.println(standard);
	}
	
	//执行删除操作
	@Test
	public void deleteById() {
		standardDao.deleteById(1);
	}
	
	//执行自定义修改的操作
	@Test
	public void updateMaxLengthByName() {
		standardDao.updateMaxLengthByName(200,"习近平");
	}
}












