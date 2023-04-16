package mysite.cardstore.service.impl;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import mysite.cardstore.controller.utils.CustomException;
import mysite.cardstore.mapper.CategoryMapper;
import mysite.cardstore.pojo.Category;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.CategoryService;
import mysite.cardstore.service.ProductService;

@Transactional
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	ProductService productService;

	@Override
	@Transactional
	public Boolean saveCategory(HttpServletRequest request, Category category) {
		category.setCreateTime(LocalDateTime.now());
		category.setUpdateTime(LocalDateTime.now());
		Object userId = request.getSession().getAttribute("loginUser");
		category.setUpdateUser((Integer) userId);
		return categoryMapper.insert(category)>0;
	}

	@Override
	public void removeCategory(Integer categoryId) {
		// 刪除分類時要先判斷有無關聯的產品
		QueryWrapper<Product> query = new QueryWrapper<>();
		query.eq("category", categoryId);
		long count = productService.count(query);
		if (count > 0) {
			//有關聯產品,拋出異常
			throw new CustomException("此分類尚有所屬商品存在，無法進行刪除");
		}

		//刪除
		removeById(categoryId);
	}

	

	


}
