package mysite.cardstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mysite.cardstore.pojo.Order;

@Mapper
public interface OrderMapper extends  BaseMapper<Order> {
	
	void batchSaveOrder(@Param("orders") List<Order> orders);
}
