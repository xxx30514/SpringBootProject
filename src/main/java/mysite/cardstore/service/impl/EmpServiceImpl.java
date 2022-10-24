package mysite.cardstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


	@Override
	public Boolean updateEmp(Emp emp) {
		
		return empMapper.updateById(emp)>0;
	}


	@Override
	public IPage<Emp> getPage(int currentPage, int pageSize) {
		IPage<Emp> page =new Page<Emp>(currentPage,pageSize);
		empMapper.selectPage(page, null);
		return page;
	}
	

	


}
