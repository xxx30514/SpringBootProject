package mysite.cardstore;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.admin.pojo.Emp;
import mysite.cardstore.mapper.EmpMapper;
import mysite.cardstore.service.EmpService;

@Slf4j
@SpringBootTest
class CardstoreApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	EmpMapper empMapper;
	@Autowired
	EmpService empService;
	
	@Test
	void contextLoads() {
		Long long1 = jdbcTemplate.queryForObject("select count(*) from t_emp", Long.class);
		log.info("總資料數:{}筆資料",long1);
	}
	
	@Test
	void testEmpMapper() {
		Emp emp = empMapper.selectById(1);
		Emp emp2 = empService.getEmpById(1);
		log.info("員工資訊:{}",emp);
		log.info("員工資訊:{}",emp2);
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
		empMapper.deleteById(35);

	}
	@Test
	void testGetAllEmp() {
		empMapper.selectList(null);

	}

}
