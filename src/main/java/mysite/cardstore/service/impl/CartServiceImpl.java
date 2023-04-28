package mysite.cardstore.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.ProductController;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.mapper.CartMapper;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.param.ProductIdParam;
import mysite.cardstore.pojo.Cart;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.CartService;
import mysite.cardstore.service.ProductService;
import mysite.cardstore.vo.CartVo;

@Transactional
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

	@Autowired
	CartMapper cartMapper;
	@Autowired
	ProductController productController;
	@Autowired
	ProductService productService;

	@Override
	public R saveCart(CartSaveParam cartSaveParam) {
		// 1.查詢商品資料
		ProductIdParam productIdParam = new ProductIdParam();
		productIdParam.setProductId(cartSaveParam.getProductId());
		//Product product = productController.cartDetail(productIdParam);
		Product product = productService.cartDetail(productIdParam.getProductId());
		if (product == null) {
			return R.fail("此商品已下架，無法加入購物車");
		}
		// 2.檢查庫存量
		if (product.getStock() == 0) {
			R result = R.success("商品庫存不足，無法加入購物車");
			result.setCode("003");
			log.info("CartServiceImpl.saveCart業務結束,結果:{}",result);
			return result;
		}
		// 3.檢查是否加入過購物車
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", cartSaveParam.getUserId());
		queryWrapper.eq("product_id", cartSaveParam.getProductId());
		Cart cart = cartMapper.selectOne(queryWrapper);
		if (cart != null && cart.getNumber()<product.getStock()) {
			//購物車已存在該商品
			//原有數量+1
			cart.setNumber(cart.getNumber()+1);
			cartMapper.updateById(cart);
			//返回004提示
			R result = R.success("該商品已加入購物車，數量+1");
			result.setCode("004");
			log.info("CartServiceImpl.saveCart業務結束,結果:{}",result);
			return result;
		}
		// 4.加入購物車
		cart= new Cart();
		cart.setNumber(1);//第一次加入購物車 預設數量1
		cart.setUserId(cartSaveParam.getUserId());
		cart.setProductId(cartSaveParam.getProductId());
		cart.setCreateTime(LocalDateTime.now());
		int rows = cartMapper.insert(cart);
		log.info("CartServiceImpl.saveCart業務結束,結果:{}",rows);
		// 5.封裝並返回資料
		CartVo cartVo = new CartVo(product,cart);
		return R.success("加入購物車成功",cartVo);
	}

}
