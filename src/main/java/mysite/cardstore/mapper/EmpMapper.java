package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mysite.cardstore.admin.pojo.Emp;

@Mapper
public interface EmpMapper {
	
	Emp getEmp(@Param("empId") Integer empId);
}
