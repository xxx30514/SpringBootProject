package mysite.cardstore.service;



import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.pojo.Category;


public interface CategoryService extends IService<Category>{

	Boolean saveCategory(HttpServletRequest request,Category category);

	void removeCategory(Integer categoryId);

}
