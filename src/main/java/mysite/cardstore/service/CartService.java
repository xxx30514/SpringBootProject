package mysite.cardstore.service;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.pojo.Cart;

public interface CartService extends IService<Cart> {
	/**
	 * 新增商品至購物車
	 * @param cartSaveParam
	 * @return
	 */
	R saveCart(@NotNull CartSaveParam cartSaveParam);
	/**
	 * 用userId查詢購物車內容
	 * @param userId
	 * @return
	 */
	R cartList(@NotNull Integer userId);
	/**
	 * 購物車內商品數量增減
	 * @param cart
	 * @return
	 */
	R updateCart(Cart cart);
	
}
