package mysite.cardstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mysite.cardstore.admin.pojo.Emp;
import mysite.cardstore.mapper.EmpMapper;

@Service
public class EmpService {
	@Autowired
	EmpMapper empMapper;
	
	public Emp getEmpById(Integer empId) {
		return empMapper.getEmp(empId);
	}
}
