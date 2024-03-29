package mysite.cardstore.param;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import mysite.cardstore.vo.CartVo;
@Data
public class OrderParam implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private Integer addressId;
	private List<CartVo> productIds;
}
