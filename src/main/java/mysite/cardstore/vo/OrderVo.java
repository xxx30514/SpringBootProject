package mysite.cardstore.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mysite.cardstore.pojo.Order;

@JsonIgnoreProperties(ignoreUnknown = true) //忽略不存在的欄位
@Data
@EqualsAndHashCode(callSuper = true) //繼承
public class OrderVo extends Order {

	private static final long serialVersionUID = 1L;

	private String productName;
	private String productPicture;
}
