package mysite.cardstore.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableId
	private Integer cartId;
	private Integer userId;
	private String productName;
	private Integer productId;
	private String productImage;
	private Integer number;
	private BigDecimal amount;
	private LocalDateTime createTime;

}
