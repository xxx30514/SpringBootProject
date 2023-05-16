package mysite.cardstore.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.pojo.Product;


public interface ProductService extends IService<Product>{
	
	Boolean saveProduct(HttpServletRequest request,Product product);

	R detail(Integer productId); 
	
//	Product cartDetail(Integer productId);
//	
//	List<Product> cartList(List<Integer> productIds);
	
	
	
	
    
	
	
}
