package mysite.cardstore;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CardstoreApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	void contextLoads() {
		Long long1 = jdbcTemplate.queryForObject("select count(*) from t_emp", Long.class);
		log.info("總資料數:{}筆資料",long1);
	}
	
	

}
