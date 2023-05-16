package mysite.cardstore.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import mysite.cardstore.pojo.Cart;
import mysite.cardstore.pojo.Product;

@JsonIgnoreProperties(ignoreUnknown = true) //忽略不存在的欄位
@Data
@NoArgsConstructor
public class CartVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cartId;// 購物車id
	private Integer userId;
	private Integer productId; // 商品id
	private String productName; // 商品名稱
	private String productDescription; // 商品描述
	private String productImage; // 商品圖片
	private Integer number; // 購買數量
	private BigDecimal amount;
	private BigDecimal price; // 商品價格
	private Integer stock; // 商品庫存量

	public CartVo(Product product, Cart cart) {
		this.cartId = cart.getCartId();
		this.productId = product.getProductId();
		this.productName = product.getName();
		this.productImage = product.getImage();
		this.price = product.getPrice();
		this.number = cart.getNumber();
		this.stock = product.getStock();
		this.productDescription = product.getDescription();
	}
}
