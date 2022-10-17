package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import mysite.cardstore.admin.pojo.Emp;

@Mapper
public interface EmpMapper extends  BaseMapper<Emp> {
	
	Emp getEmp(@Param("empId") Integer empId);
}
