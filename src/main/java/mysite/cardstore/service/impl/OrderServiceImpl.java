package mysite.cardstore.service.impl;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.CustomException;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.mapper.OrderMapper;
import mysite.cardstore.mapper.ProductMapper;
import mysite.cardstore.param.OrderParam;
import mysite.cardstore.param.OrdertoProductParam;
import mysite.cardstore.pojo.Order;
import mysite.cardstore.pojo.Product;
import mysite.cardstore.service.OrderService;
import mysite.cardstore.vo.CartVo;
import mysite.cardstore.vo.OrderVo;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	ApplicationContext applicationContext;
	/**
	 * 產生訂單
	 * 1.將購物車資料轉為訂單資料
	 * 2.批量新增訂單資料
	 * 3.修改商品庫存與販售數量
	 * 4.刪除相應購物車資料
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R saveOrder(OrderParam orderParam) {
		//1.準備購物車資料
		//購物車id集合 準備批量刪除
		List<Integer> cartIds = new ArrayList<>();
		//購買的商品ID與數量集合
		List<OrdertoProductParam> productList = new ArrayList<>();
		List<Order> orderList = new ArrayList<>();
		//2.生成訂單資料
		Integer userId = orderParam.getUserId();
		Long orderNum =  System.currentTimeMillis();
		for (CartVo cartVo : orderParam.getProductIds()) {
			cartIds.add(cartVo.getCartId());//要刪除的購物車id
			OrdertoProductParam ordertoProductParam = new OrdertoProductParam();
			ordertoProductParam.setNumber(cartVo.getNumber());
			ordertoProductParam.setProductId(cartVo.getProductId());
			productList.add(ordertoProductParam);//要修改的商品資料
			Order order = new Order();
			order.setOrderNum(orderNum);
			order.setOrderTime(LocalDateTime.now());
			order.setUserId(userId);
			order.setProductId(cartVo.getProductId());
			order.setProductNum(cartVo.getNumber());
			order.setProductPrice(cartVo.getPrice());
			order.setAddressId(orderParam.getAddressId());
			orderList.add(order);
			if (cartVo.getNumber()>cartVo.getStock()) {
				throw new CustomException("商品庫存不足");
			}
		}
		//批量新增訂單
		this.saveBatch(orderList);
//		orderMapper.batchSaveOrder(orderList);
		//刪除相應購物車資料
		//(exchange,routing-key,傳輸資料內容)
		rabbitTemplate.convertAndSend("order.exchange","clear.cart",cartIds);
		//修改商品庫存與販售數量
		rabbitTemplate.convertAndSend("order.exchange","update.number",productList);
		R r = R.success("購買成功",orderList);
		log.info("OrderServiceImpl.saveOrder業務結束,結果:{}",r);
		return r;
	}
	/**
	 * 分組查詢訂單資料
	 * 1.使用者id查詢該使用者的所有訂單
	 * 2.利用orderId進行訂單分組 stream流
	 * 3.查詢訂單內的商品集合 組成map productId為key product為value
	 * 4.封裝返回的OrderVo
	 */
	@Override
	//@NotNull
	public R getOrder(Integer userId) {
		//查詢使用者訂單
		QueryWrapper<Order> query = new QueryWrapper<>();
		query.eq("user_id", userId);
		List<Order> list = list(query);
		//分組 訂單編號OrderNum為key OrderList為value
		Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderNum));
		//查詢訂單內商品
		List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());
//		ProductListParam productListParam = new ProductListParam();
//		productListParam.setProductIds(productIds);
		List<Product> productList = productMapper.selectBatchIds(productIds);
		Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, product->product));
		//結果封裝OrderVo
		List<List<OrderVo>> result = new ArrayList<>();
		//遍歷訂單集合
		for (List<Order> orders : orderMap.values()) {
			//封裝每一個訂單
			List<OrderVo> orderVos = new ArrayList<>();
			for (Order order : orders) {
				OrderVo orderVo = new OrderVo();
				BeanUtils.copyProperties(order, orderVo);
				Product product = productMap.get(order.getProductId());
				orderVo.setProductName(product.getName());
				orderVo.setProductPicture(product.getImage());
				//List<OrderVo>
				orderVos.add(orderVo);
			}
			result.add(orderVos);
		}
		R r = R.success("訂單資料查詢成功",result);
		log.info("OrderServiceImpl.getOrder業務結束,結果:{}",r);
		return r;
	}

}
