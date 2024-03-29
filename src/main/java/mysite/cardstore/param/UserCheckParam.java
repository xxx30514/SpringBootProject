package mysite.cardstore.param;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserCheckParam {

	//不能為unll或""
	@NotBlank
	private String userAccount;
}
