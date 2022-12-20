package mysite.cardstore.param;


import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserCheckParam {
	
	//不能為unll或""
	@NotBlank
	private String userAccount;
}
