package mysite.cardstore.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import mysite.cardstore.admin.pojo.Emp;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.service.EmpService;

@RestController
@RequestMapping("/emps")
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping(produces = "application/json;charset=utf-8")
	public Result getAll(){
		return new Result(true,empService.list());
	}
	
	@PostMapping
	public Result save(@RequestBody Emp emp) throws IOException{
		if ("123".equals(emp.getEmpName())) throw new IOException(); 
		return new Result(true,empService.save(emp));
	}
	
	@PutMapping
	public Result update(@RequestBody Emp emp){
		return new Result(true,empService.updateEmp(emp));
	}
	
	@DeleteMapping("{empId}")
	public Result delete(@PathVariable("empId") Integer empId) {
		return new Result(true,empService.removeById(empId));
	}
	
	@GetMapping("{empId}")
	public Result getEmpById(@PathVariable("empId") Integer empId) {
		return new Result(true,empService.getById(empId));
	}
	
	@GetMapping("{currentPage}/{pageSize}")
	public Result getIPage(@PathVariable int currentPage,@PathVariable int pageSize){
		IPage<Emp> page = empService.getPage(currentPage, pageSize);
		if (currentPage>page.getPages()) {
			page = empService.getPage((int) page.getPages(), pageSize);
		}
		return new Result(true,page);
	}
}
