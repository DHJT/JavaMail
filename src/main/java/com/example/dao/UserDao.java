package com.example.dao;

import com.example.model.User;

public interface UserDao {
	boolean save(User user);
	boolean update(User user);
	User queryByCode(String code); // 根据激活码寻找User
	boolean existByUserName(String username);
}
