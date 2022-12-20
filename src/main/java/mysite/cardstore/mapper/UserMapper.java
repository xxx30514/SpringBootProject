package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import mysite.cardstore.pojo.User;

@Mapper
public interface UserMapper extends  BaseMapper<User> {
	
	
}
