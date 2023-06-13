package mysite.cardstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@MapperScan(basePackages = "mysite.cardstore.mapper")
//掃描此package的所有interface編譯後產生相應實現類 可以不用每個Mapper都要加上@Mapper註解
@SpringBootApplication
@EnableTransactionManagement
public class CardstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardstoreApplication.class, args);
	}

}
