package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.example.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean save(User user) {
		return userRepository.save(user) != null;
	}

	@Override
	public boolean update(User user) {
		userRepository.saveAndFlush(user);
		return true;
	}

	@Override
	public User queryByCode(String code) {
		User user = userRepository.findByCode(code);
		return user;
	}

	@Override
	public boolean existByUserName(String username) {
		return userRepository.findByUsername(username) != null;
	}

}
