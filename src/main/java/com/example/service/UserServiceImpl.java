package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.utils.CodeUtils;
import com.example.utils.MailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean register(User user) {
		// 获取User
		String code = CodeUtils.generateUniqueCode();
		user.setCode(code);
		user.setState(0);
		
		if (userDao.existByUserName(user.getUsername())) return false;
		
		// 调用持久层保存数据，如果成功，发送一封邮件
		if(userDao.save(user)){
	      new Thread(new MailUtils(user.getEmail(), code)).start();;
	      return true;
	    }
		return false;
	}

	@Override
	public boolean active(String code) {
		User user = userDao.queryByCode(code);
		if (null != user) {
			user.setState(1);
			user.setCode(null);
			userDao.update(user);
			return true;
		}
		return false;
	}

}
