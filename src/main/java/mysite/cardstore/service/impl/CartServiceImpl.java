package mysite.cardstore.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.mapper.CartMapper;
import mysite.cardstore.mapper.ProductMapper;
import mysite.cardstore.param.CartSaveParam;
import mysite.cardstore.param.ProductIdParam;
import mysite.cardstore.pojo.Cart;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.CartService;

import mysite.cardstore.vo.CartVo;

@Transactional
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductMapper productMapper;
	

	@Override
	public R saveCart(@NotNull CartSaveParam cartSaveParam) {
		// 1.查詢商品資料
		ProductIdParam productIdParam = new ProductIdParam();
		productIdParam.setProductId(cartSaveParam.getProductId());
		//Product product = productController.cartDetail(productIdParam);
		//Product product = productService.cartDetail(productIdParam.getProductId());
		Product product = productMapper.selectById(productIdParam.getProductId());
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

	@Override
	public R cartList(@NotNull Integer userId) {
		// 1.使用者id查詢購物車資料 
		QueryWrapper<Cart> query = new QueryWrapper<>();
		query.eq("user_id", userId);
		List<Cart> cartList = cartMapper.selectList(query);
		//2.判斷是否存在 不存在返回空集合
		if (cartList == null || cartList.size()==0) {
			cartList = new ArrayList<>();
			return R.success("尚未加入任何商品",cartList);
		}
		//3.獲取商品id集合 調用商品查詢服務
		List<Integer> productIds = new ArrayList<>();
		for (Cart cart : cartList) {
			productIds.add(cart.getProductId());
		}
		//List<Product> productList = productService.cartList(productIds);
		QueryWrapper<Product> productQuery = new QueryWrapper<>();
		productQuery.in("product_id", productIds);
		List<Product> selectList = productMapper.selectList(productQuery);
		//商品map集合 key=productId value=Product實體類
		//Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, p->p));
		Map<Integer, Product> collect = selectList.stream().collect(Collectors.toMap(Product::getProductId, p->p));
		//4.封裝vo資料 
		List<CartVo> cartVoList = new ArrayList<>();
		for (Cart cart : cartList) {
			//CartVo cartVo = new CartVo(productMap.get(cart.getProductId()),cart);
			CartVo cartVo = new CartVo(collect.get(cart.getProductId()),cart);
			cartVoList.add(cartVo);
		}
		R result = R.success("購物車資料查詢成功", cartVoList);
		log.info("CartServiceImpl.cartList業務結束,結果:{}",result);
		return result;
	}

	@Override
	public R updateCart(Cart cart) {
		// 1.查詢商品資料
		ProductIdParam productIdParam = new ProductIdParam();
		productIdParam.setProductId(cart.getProductId());
		Product product = productMapper.selectById(productIdParam.getProductId());
		// 2.判斷庫存量 (最大數量不可超過庫存量)
		if (cart.getNumber()>product.getStock()) {
			return R.fail("商品庫存不足，無法修改數量");
		}
		// 3.修改數量
		QueryWrapper<Cart> query = new QueryWrapper<>();
		query.eq("user_id", cart.getUserId());
		query.eq("product_id", cart.getProductId());
		Cart newCart = cartMapper.selectOne(query);
		newCart.setNumber(cart.getNumber());
		cartMapper.updateById(newCart);
		return R.success("購物車數量修改成功");
	}

}
