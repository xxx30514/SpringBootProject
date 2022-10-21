package mysite.cardstore.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.admin.pojo.Emp;


@Service
public interface EmpService extends IService<Emp>{
		
	Emp getEmpById(Integer empId); 
}
