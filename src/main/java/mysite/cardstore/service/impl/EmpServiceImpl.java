package mysite.cardstore.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mysite.cardstore.mapper.EmpMapper;
import mysite.cardstore.pojo.Emp;
import mysite.cardstore.service.EmpService;

@Transactional
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


	@Override
	public IPage<Emp> getPage(int currentPage, int pageSize, Emp emp) {
		LambdaQueryWrapper<Emp> lqw =new LambdaQueryWrapper<Emp>();
		lqw.like(emp.getEmpName()!=null,Emp::getEmpName,emp.getEmpName());
		lqw.like(emp.getEmpAge()!=null,Emp::getEmpAge,emp.getEmpAge());
		lqw.like(Strings.isNotEmpty(emp.getEmpGender()),Emp::getEmpGender,emp.getEmpGender());
		IPage<Emp> page =new Page<Emp>(currentPage,pageSize);
		empMapper.selectPage(page, lqw);
		return page;
	}

	

	


}
