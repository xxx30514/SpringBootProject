package mysite.cardstore.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.CommonController;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.mapper.EmpMapper;
import mysite.cardstore.pojo.Emp;
import mysite.cardstore.service.EmpService;

@Transactional
@Service
@Slf4j
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService{
	@Value("${cardstore.path}")
	private String basePath;
	
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
		lqw.like(!emp.getEmpName().isEmpty(),Emp::getEmpName,emp.getEmpName());
		lqw.like(emp.getEmpAge()!=null,Emp::getEmpAge,emp.getEmpAge());
		lqw.like(!emp.getEmpGender().isEmpty(),Emp::getEmpGender,emp.getEmpGender());
		IPage<Emp> page =new Page<Emp>(currentPage,pageSize);
		empMapper.selectPage(page, lqw);
		return page;
	}


	@Override
	public Boolean saveEmp(Emp emp, MultipartFile headerImg) {
		log.info(headerImg.toString());
		//獲取上傳的檔案名稱
		String originalFilename = headerImg.getOriginalFilename();
		if (!headerImg.isEmpty()) {	
			//獲取上傳的檔案的後綴名 ex. jpg
			String typeName =  originalFilename.substring(originalFilename.lastIndexOf("."));
			//獲取UUID
			String uuid = UUID.randomUUID().toString();
			//拼接新檔名避免重複檔名問題
			originalFilename = uuid + typeName;
		}
		//創建目錄
		File file = new File(basePath);
		if (!file.exists()) {
			//若無對應目錄則新建指定目錄
			file.mkdirs();
		}
		try {
			if (!headerImg.isEmpty()) {				
				headerImg.transferTo(new File(basePath+originalFilename));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empMapper.insert(emp)>0;
	}


	@Override
	public Result upload(MultipartFile headerImg) {
		log.info(headerImg.toString());
		//獲取上傳的檔案名稱
		String originalFilename = headerImg.getOriginalFilename();
		if (!headerImg.isEmpty()) {	
			//獲取上傳的檔案的後綴名 ex. jpg
			String typeName =  originalFilename.substring(originalFilename.lastIndexOf("."));
			//獲取UUID
			String uuid = UUID.randomUUID().toString();
			//拼接新檔名避免重複檔名問題
			originalFilename = uuid + typeName;
		}
		//創建目錄
		File file = new File(basePath);
		if (!file.exists()) {
			//若無對應目錄則新建指定目錄
			file.mkdirs();
		}
		try {
			if (!headerImg.isEmpty()) {				
				headerImg.transferTo(new File(basePath+originalFilename));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Result(true,originalFilename);
	}

	

	


}
