package mysite.cardstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import mysite.cardstore.admin.pojo.Emp;
import mysite.cardstore.mapper.EmpMapper;
import mysite.cardstore.service.EmpService;
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService{
	
	@Autowired
	EmpMapper empMapper;
	
	
	@Override
	public Emp getEmpById(Integer empId) {
		
		return empMapper.getEmp(empId);
	}

	


}
