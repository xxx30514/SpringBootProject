package mysite.cardstore.service;

import org.springframework.stereotype.Service;

import mysite.cardstore.admin.pojo.Emp;


@Service
public interface EmpService {
		
	public Emp getEmpById(Integer empId); 
}
