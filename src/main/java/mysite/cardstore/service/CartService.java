package mysite.cardstore.service;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.pojo.Cart;

public interface CartService extends IService<Cart> {

	R saveCart(@NotNull CartSaveParam cartSaveParam);

	R cartList(@NotNull Integer userId);
	
}
