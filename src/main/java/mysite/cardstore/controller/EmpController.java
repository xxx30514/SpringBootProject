package mysite.cardstore.controller;


import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.pojo.Emp;
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
		//if ("123".equals(emp.getEmpName())) throw new IOException(); 
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
	
//	@GetMapping("{currentPage}/{pageSize}")
//	public Result getIPage(@PathVariable int currentPage,@PathVariable int pageSize){
//		IPage<Emp> page = empService.getPage(currentPage, pageSize);
//		if (currentPage>page.getPages()) {
//			page = empService.getPage((int) page.getPages(), pageSize);
//		}
//		return new Result(true,page);
//	}
	@GetMapping("{currentPage}/{pageSize}")
	public Result getIPage(@PathVariable int currentPage,@PathVariable int pageSize,Emp emp){
		IPage<Emp> page = empService.getPage(currentPage, pageSize,emp);
		if (currentPage>page.getPages()) {
			page = empService.getPage((int) page.getPages(), pageSize,emp);
		}
		return new Result(true,page);
	}
	@PostMapping(path = "/file",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public Result savefile(@RequestPart(required = false) MultipartFile file,@RequestPart String emp) throws IOException{
		//if ("123".equals(emp.getEmpName())) throw new IOException(); 
		Object data = empService.upload(file).getData();
		Emp empJson =empService.getEmpJson(emp, file);
		empService.save(empJson);
		System.out.println(file);	
		System.out.println(data);
		return new Result(true,empJson);
		//null
	}
	@PostMapping(path = "/newfile",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public Result savefile2(@RequestPart(name = "file", required = false) MultipartFile file,@RequestPart("emp") Emp emp) throws IOException{
		//if ("123".equals(emp.getEmpName())) throw new IOException(); 
		if (file!=null) {		
			Object data = empService.upload(file).getData();
			System.out.println(data);
		}
		empService.save(emp);
		System.out.println(file);	
		return new Result(true,emp);
		//null
	}
	@GetMapping("/test")
	public ResponseEntity<Emp> testResponseEntity(@RequestBody Emp emp){
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
	
	
	
}
