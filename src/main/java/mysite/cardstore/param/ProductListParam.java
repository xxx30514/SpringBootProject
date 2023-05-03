package mysite.cardstore.param;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ProductListParam {
	@NotEmpty
	private List<Integer> productIds;
}
