package mysite.cardstore;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.mapper.EmpMapper;
import mysite.cardstore.mapper.OrderMapper;
import mysite.cardstore.pojo.Emp;
import mysite.cardstore.pojo.Order;
import mysite.cardstore.pojo.User;
import mysite.cardstore.service.EmpService;
import mysite.cardstore.service.UserService;

@Slf4j
@SpringBootTest
class CardstoreApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	EmpMapper empMapper;
	@Autowired
	EmpService empService;
	@Autowired
	UserService userService;
	@Autowired
	OrderMapper orderMapper;
	
	@Test
	void contextLoads() {
		Long long1 = jdbcTemplate.queryForObject("select count(*) from t_emp", Long.class);
		log.info("總資料數:{}筆資料",long1);
	}
	
	@Test
	void testEmpMapper() {
		Emp emp = empMapper.selectById(1);
		Emp emp2 = empService.getEmpById(1);
		Emp emp3 = empMapper.getEmp(5);
		log.info("員工資訊:{}",emp);
		log.info("員工資訊:{}",emp2);
		log.info("員工資訊:{}",emp3);
	}
	
	@Test
	void testInsertEmp() {
		Emp emp = new Emp();
		emp.setEmpName("新增測試");
		emp.setEmpAge(25);
		emp.setEmpGender("男");
		empMapper.insert(emp);

	}
	@Test
	void testUpdateEmp() {
		Emp emp = new Emp();
		emp.setEmpId(36);
		emp.setEmpName("新增測試更新");
		emp.setEmpAge(26);
		emp.setEmpGender("男");
		empMapper.updateById(emp);

	}
	@Test
	void testDeleteUpdateEmp() {
		empService.removeById(55);

	}
	@Test
	void testGetAllEmp() {
		empMapper.selectList(null);

	}
	@Test
	void testGetAll() {
		empService.list();
	}
	@Test
	void testPage() {
		IPage<Emp> page = new Page(1,5); 
		empMapper.selectPage(page, null);

	}
	@Test
	void testGetBy() {
		QueryWrapper<Emp> emp =new QueryWrapper<>();
		emp.like("emp_name", "王" );
		empMapper.selectList(emp);

	}
	@Test
	void testGetBy2() {
		String name = "王";
		IPage<Emp> page = new Page(1,5); 
		LambdaQueryWrapper<Emp> emp =new LambdaQueryWrapper<Emp>();
		emp.like(StringUtils.isNotEmpty(name),Emp::getEmpName, name);
		empMapper.selectPage(page,emp);
		empService.page(page);

	}
	@Test
	void testService() {
		String name = "王";
		IPage<Emp> page = new Page(1,5); 
		LambdaQueryWrapper<Emp> emp =new LambdaQueryWrapper<Emp>();
		emp.like(StringUtils.isNotEmpty(name),Emp::getEmpName, name);
		empService.page(page,emp);

	}
	@Test
	void testMybatisInsert() {
		Emp emp = new Emp();
		emp.setEmpName("新增測試");
		emp.setEmpAge(25);
		emp.setEmpGender("男");
		empMapper.insertEmp(emp);
	}
	@Test
	void testMybatisInsert2() {
		User user = new User();
		user.setUserAccount("1234567");
		user.setUserPassword("123456");
		user.setUserEmail("12001@gmail.com");
		userService.saveUser(user);
	}
	@Test
	void testMybatisgetEmp() {
		Emp emp = empMapper.getEmp(15);
		System.out.println(emp);
	}
	@Test
	void testBatchInsert() {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderNum((long) 465465465);
		order.setProductId(1);
		Order order2 =new Order();
		order2.setOrderNum((long) 123453453);
		order2.setProductId(2);
		orders.add(order);
		orders.add(order2);
		orderMapper.batchSaveOrder(orders);
		
	}

}
