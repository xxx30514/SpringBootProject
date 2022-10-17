package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mysite.cardstore.admin.pojo.Emp;

@Mapper
public interface Emp2Mapper extends BaseMapper<Emp> {
	
}
