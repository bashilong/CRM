package com.itheima.bean;

public class User {
	private Long user_id;
	private String user_code;
	private String user_password;
	private String user_name;
	private char user_state;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public char getUser_state() {
		return user_state;
	}
	public void setUser_state(char user_state) {
		this.user_state = user_state;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_code=" + user_code + ", user_password=" + user_password
				+ ", user_name=" + user_name + ", user_state=" + user_state + "]";
	}
	
	
}