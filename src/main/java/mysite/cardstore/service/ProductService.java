package mysite.cardstore.service;



import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.pojo.Product;


public interface ProductService extends IService<Product>{
	
	Boolean saveProduct(HttpServletRequest request,Product product); 
    
	
	
}
