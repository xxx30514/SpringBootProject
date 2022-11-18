package mysite.cardstore.service;



import com.baomidou.mybatisplus.extension.service.IService;


import mysite.cardstore.model.User;

public interface UserService extends IService<User>{
	
	Boolean saveUser(User user); 
	
	Boolean updateUser(User user);

}
