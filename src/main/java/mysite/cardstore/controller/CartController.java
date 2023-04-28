package mysite.cardstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/save")
	public R save(@RequestBody @Validated CartSaveParam cartSaveParam,BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("加入購物車失敗，請重新嘗試");
		}
		return cartService.saveCart(cartSaveParam);
	}
}
