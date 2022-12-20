package mysite.cardstore.service.impl;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mysite.cardstore.mapper.ProductMapper;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService{
	
	@Autowired
	ProductMapper productMapper;

	@Override
	public Boolean saveProduct(HttpServletRequest request, Product product) {
		product.setCreateTime(LocalDateTime.now());
		product.setUpdateTime(LocalDateTime.now());
		Object userId = request.getSession().getAttribute("loginUser");
		product.setUpdateUser((Integer) userId);
		
		return productMapper.insert(product)>0;
	}

	

	

	


}
