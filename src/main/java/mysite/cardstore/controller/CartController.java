package mysite.cardstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartListParam;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.pojo.Cart;
import mysite.cardstore.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/save")
	public R save(@RequestBody @Validated CartSaveParam cartSaveParam,BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("加入購物車失敗，請重新嘗試");
		}
		return cartService.saveCart(cartSaveParam);
	}
	
	@PostMapping("/list")
	public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("購物車資料查詢失敗，請重新嘗試");
		}
		return cartService.cartList(cartListParam.getUserId());
	}
	
	@GetMapping("{userId}")
	public R CartList(@PathVariable Integer userId) {
		return cartService.cartList(userId);
	}
	
	@PutMapping("/update")
	public R update(@RequestBody Cart cart) {
		return cartService.updateCart(cart);
	}
	
	@DeleteMapping("{cartId}")
	public R delete(@PathVariable Integer cartId) {
		cartService.removeById(cartId);
		return R.success("購物車刪除成功");
	}
}
