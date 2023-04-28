package mysite.cardstore.param;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CartSaveParam {
	@NotNull
	private Integer productId;
	@NotNull
	private Integer userId;
}
