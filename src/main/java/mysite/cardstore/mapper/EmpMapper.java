package mysite.cardstore.mapper;

import org.apache.ibatis.annotations.Mapper;

import mysite.cardstore.admin.pojo.Emp;

@Mapper
public interface EmpMapper {
	
	public Emp getEmp(Integer empId);
}
