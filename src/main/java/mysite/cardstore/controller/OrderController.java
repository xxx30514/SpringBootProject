package mysite.cardstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.param.CartListParam;
import mysite.cardstore.param.OrderParam;
import mysite.cardstore.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public R save(@RequestBody OrderParam orderParam) {

		return orderService.saveOrder(orderParam);
	}
	@PostMapping("/list")
	public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("訂單查詢失敗，請稍後再試");
		}
		return orderService.getOrder(cartListParam.getUserId());
	}
	@GetMapping("{userId}")
	public R orderList(@PathVariable Integer userId) {
		return orderService.getOrder(userId);
	}
}
