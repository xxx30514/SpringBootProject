package mysite.cardstore.param;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductIdParam {

	@NotNull
	private Integer productId;
}
