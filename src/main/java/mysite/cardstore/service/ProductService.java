package mysite.cardstore.service;



import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.model.Product;


public interface ProductService extends IService<Product>{
	
	Boolean saveProduct(HttpServletRequest request,Product product); 
    
	
	
}
