package mysite.cardstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.model.Category;
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

}
