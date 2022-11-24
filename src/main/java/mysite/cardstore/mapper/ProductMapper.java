package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import mysite.cardstore.model.Product;

@Mapper
public interface ProductMapper extends  BaseMapper<Product> {
	
	
}
