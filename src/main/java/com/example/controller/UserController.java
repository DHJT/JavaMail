package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="register")
	public String register() {
		return "register";
	}
	
	@PostMapping(value="doRegister")
	public @ResponseBody String doRegister(@Valid User user, BindingResult result) {
		// 检查合法性
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
			return "注册失败 " + list.get(0).getDefaultMessage();
		}
		// 调用服务
		if (userService.register(user)) {
			return "注册成功,请激活";
		} else {
			return "注册失败，请检查您的信息";
		}
	}
	
	// TODO: active controller
	@GetMapping(value="active")
	public @ResponseBody String active(@RequestParam(value="code", required=true)String code) {
		if (userService.active(code)) {
			return "激活成功";
		}
		return "激活失败";
	}
}
