package com.example.service;

import com.example.model.User;

public interface UserService {
	public boolean register(User user);
	public boolean active(String code);
}
