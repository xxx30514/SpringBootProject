package mysite.cardstore.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName("t_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
	private String image;
	private Integer saleCount;
	private Integer status;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer updateUser;
}
