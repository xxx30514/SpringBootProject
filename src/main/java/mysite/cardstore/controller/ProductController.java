package mysite.cardstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.ProductIdParam;
import mysite.cardstore.param.ProductListParam;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/detail")
	public R detail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result) {
		
		if (result.hasErrors()) {
			return R.fail("查詢商品資訊失敗，請重新查詢");
		}
	
		return productService.detail(productIdParam.getProductId());
	}
	@GetMapping("/detail/{productId}")
	public R detail(@PathVariable Integer productId) {
	
		return productService.detail(productId);
	}
//	
//	@PostMapping("/cart/detail")
//	public Product cartDetail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result) {
//		
//		if (result.hasErrors()) {
//			return null;
//		}
//		
//		R detail = productService.detail(productIdParam.getProductId());
//		Product product = (Product) detail.getData();
//		return product;
//	}

//	@PostMapping("/cart/detail2")
//	public Product cartDetail(@RequestBody ProductIdParam productIdParam) {
//		R detail = productService.detail(productIdParam.getProductId());
//		Product product = (Product) detail.getData();
//		return product;
//	}
	
//	@PostMapping("/cart/list")
//	public List<Product> cartList(@RequestBody @Validated ProductListParam productListParam,BindingResult result){
//		if (result.hasErrors()) {
//			return new ArrayList<Product>();
//		}
//		return productService.cartList(productListParam.getProductIds());
//	}
//	
	
}
