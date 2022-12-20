package mysite.cardstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


import mysite.cardstore.controller.utils.R;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.pojo.Category;
import mysite.cardstore.service.CategoryService;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public Result save(HttpServletRequest request,@RequestBody Category category) {
		return new Result(true,categoryService.saveCategory(request, category));
	}
	
	@DeleteMapping("{categoryId}")
	public Result delete(@PathVariable("categoryId") Integer categoryId) {
		categoryService.removeCategory(categoryId);
		return new Result(true);
	}
	
	@GetMapping("/list")
	public Result list(Category category){
		LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
		queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
		List<Category> list = categoryService.list(queryWrapper);	
		return new Result(true,list);
	}

}
