package mysite.cardstore.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.mapper.ProductMapper;
import mysite.cardstore.param.OrdertoProductParam;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.ProductService;

@Slf4j
@Transactional
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public Boolean saveProduct(HttpServletRequest request, Product product) {
		product.setCreateTime(LocalDateTime.now());
		product.setUpdateTime(LocalDateTime.now());
		Object userId = request.getSession().getAttribute("loginUser");
		product.setUpdateUser((Integer) userId);

		return productMapper.insert(product) > 0;
	}

	@Override
	public R detail(Integer productId) {
		Product product = productMapper.selectById(productId);
		if (product == null) {
			return R.fail("查無該商品");
		}
		R result = R.success(product);
		log.info("ProductServiceImpl.detail,結果:{}", result);

		return result;
	}

	/**
	 * 修改商品庫存與增加銷售數量
	 * 
	 * @param productList
	 */
	@Override
	public void updateNum(List<OrdertoProductParam> productList) {
		// 將productList集合轉成map productId為key OrdertoProductParam(商品id與數量)為value
		// key : OrdertoProductParam::getProductId 方法引用
		// value : param-> param or v->v 將原來的物件(List<OrdertoProductParam>)作為map的Value
		Map<Integer, OrdertoProductParam> map = productList.stream()
				.collect(Collectors.toMap(OrdertoProductParam::getProductId, param -> param));
		// 獲取商品id集合
		Set<Integer> productIds = map.keySet();
		// 查詢集合對應商品資訊
		List<Product> productDataList = productMapper.selectBatchIds(productIds);
		// 修改商品資訊 -庫存 +銷量
		for (Product product : productDataList) {
			Integer number = map.get(product.getProductId()).getNumber();
			product.setStock(product.getStock() - number);//-庫存
			product.setSaleCount(product.getSaleCount() + number);//+銷量
		}
		// 批量更新資訊
		this.updateBatchById(productDataList);
		log.info("ProductServiceImpl.updateNum業務結束,結果:{}",productDataList);
	}

//	@Override
//	public Product cartDetail(Integer productId) {
//		Product product = productMapper.selectById(productId);
//		log.info("ProductServiceImpl.cartDetail,結果:{}",product);
//		return product;
//	}
//
//	@Override
//	public List<Product> cartList(List<Integer> productIds) {
//		QueryWrapper<Product> query = new QueryWrapper<>();
//		query.in("product_id", productIds);
//		List<Product> productList = productMapper.selectList(query);
//		log.info("ProductServiceImpl.cartList,結果:{}",productList);
//		return productList;
//	}

}
