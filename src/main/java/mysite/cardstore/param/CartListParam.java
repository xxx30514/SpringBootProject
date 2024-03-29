package mysite.cardstore.param;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CartListParam {

	@NotNull
	private Integer userId;
}
