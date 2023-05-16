package mysite.cardstore.param;

import java.io.Serializable;

import lombok.Data;
@Data
public class OrdertoProductParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer number;
}
