package mysite.cardstore.param;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginParam {
	
	@NotBlank //不能為unll或""
	private String userAccount;
	@NotBlank 
	private String userPassword;
}
