package mysite.cardstore.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.mapper.OrderMapper;
import mysite.cardstore.param.OrderParam;
import mysite.cardstore.param.OrdertoProductParam;
import mysite.cardstore.pojo.Order;
import mysite.cardstore.service.OrderService;
import mysite.cardstore.vo.CartVo;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	/**
	 * 產生訂單
	 * 1.將購物車資料轉為訂單資料
	 * 2.批量新增訂單資料
	 * 3.修改商品庫存與販售數量
	 * 4.刪除相應購物車資料
	 */
	@Transactional
	@Override
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
			orderList.add(order);
		}
		//批量新增訂單
		saveBatch(orderList);
		//刪除相應購物車資料
		//(exchange,routing-key,傳輸資料內容)
		rabbitTemplate.convertAndSend("exchange.topic","clear.cart",cartIds);
		//修改商品庫存與販售數量
		rabbitTemplate.convertAndSend("exchange.topic","update.number",productList);
		return null;
	}
}
