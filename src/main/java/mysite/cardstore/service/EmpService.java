package mysite.cardstore.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.admin.pojo.Emp;


@Service
public interface EmpService extends IService<Emp>{
		
	Emp getEmpById(Integer empId);

	Boolean updateEmp(Emp emp); 
	
	IPage<Emp> getPage(int currentPage,int pageSize);
	
	IPage<Emp> getPage(int currentPage,int pageSize,Emp emp);
}
