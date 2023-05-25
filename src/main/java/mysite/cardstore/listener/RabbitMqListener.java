package mysite.cardstore.listener;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mysite.cardstore.param.OrdertoProductParam;
import mysite.cardstore.service.CartService;
import mysite.cardstore.service.ProductService;

@Component
public class RabbitMqListener {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	//沒有在@exchange裡配置type = ExchangeTypes.TOPIC 預設為DIRECT
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(name = "clear.queue"), 
					exchange = @Exchange(value = "order.exchange"),
					key = "clear.cart"
					)
	)
	public void clear(List<Integer> cartIds) {
		cartService.removeByIds(cartIds);
	}
	
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(name = "update.queue"), 
					exchange = @Exchange(value = "order.exchange"),
					key = "update.number"
					)
	)
	public void update(List<OrdertoProductParam> productList) {
		productService.updateNum(productList);
	}
}
