package mysite.cardstore.service;



import com.baomidou.mybatisplus.extension.service.IService;

import mysite.cardstore.controller.utils.R;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.param.UserCheckParam;
import mysite.cardstore.param.UserLoginParam;
import mysite.cardstore.pojo.User;

public interface UserService extends IService<User>{
	
	Boolean saveUser(User user); 
	
	Boolean updateUser(User user);
	
	R check(UserCheckParam userCheckParam);

	Result register(User user);

	Result login(UserLoginParam userLoginParam);

}
