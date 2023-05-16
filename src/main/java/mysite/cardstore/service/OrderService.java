package mysite.cardstore.service;

import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.OrderParam;
import mysite.cardstore.pojo.Order;

public interface OrderService extends IService<Order> {

	R saveOrder(OrderParam orderParam);

}
