package mysite.cardstore.service;


import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.pojo.Emp;



public interface EmpService extends IService<Emp>{

	Emp getEmpById(Integer empId);

	Boolean updateEmp(Emp emp);

	IPage<Emp> getPage(int currentPage,int pageSize);

	IPage<Emp> getPage(int currentPage,int pageSize,Emp emp);

	Boolean saveEmp(Emp emp,MultipartFile headerImg);

	Result upload(MultipartFile headerImg);

	Emp getEmpJson(String emp,MultipartFile file);

}
