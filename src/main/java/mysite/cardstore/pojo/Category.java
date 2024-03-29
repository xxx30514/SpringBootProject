package mysite.cardstore.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName("t_category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableId
	private Integer categoryId;
	private Integer type;
	private String name;
	private Integer sort;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer updateUser;



}
