package mysite.cardstore.service;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.OrderParam;
import mysite.cardstore.pojo.Order;

public interface OrderService extends IService<Order> {
	/**
	 * 產生訂單
	 * @param orderParam
	 * @return
	 */
	R saveOrder(OrderParam orderParam);
	/**
	 * 分組查詢訂單
	 * @param userId
	 * @return
	 */
	R getOrder(@NotNull Integer userId);

}
