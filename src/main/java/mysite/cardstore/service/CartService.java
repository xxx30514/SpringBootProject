package mysite.cardstore.service;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.pojo.Cart;

public interface CartService extends IService<Cart> {

	R saveCart(CartSaveParam cartSaveParam);
	
}
