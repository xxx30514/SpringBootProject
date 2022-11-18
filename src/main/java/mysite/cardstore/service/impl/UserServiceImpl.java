package mysite.cardstore.service.impl;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mysite.cardstore.mapper.UserMapper;
import mysite.cardstore.model.User;
import mysite.cardstore.service.UserService;
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public Boolean saveUser(User user) {
		user.setUserCreatedate(LocalDateTime.now());
		user.setUserUpdatedate(LocalDateTime.now());
		return userMapper.insert(user)>0;
	}

	@Override
	public Boolean updateUser(User user) {
		user.setUserUpdatedate(LocalDateTime.now());
		return userMapper.updateById(user)>0;
	}
	

	


}
