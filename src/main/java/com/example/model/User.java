package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    @NotEmpty(message="用户名不能为空")
    private String username;
    
	@Column(nullable = false)
	@NotEmpty(message="密码不能为空")
	@Length(min=6, message="密码长度不能少于6位")
    private String password;
    
	@Email
    @Column(nullable = false)
    @NotEmpty(message="邮箱不能为空")
    private String email;
    
    @Column(nullable = false)
    private int state;
    
    @Column
    private String code;
      
    public User() {}  

    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", state="
				+ state + ", code=" + code + "]";
	}
}  
