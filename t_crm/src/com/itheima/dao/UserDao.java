package com.itheima.dao;

import com.itheima.bean.User;

public interface UserDao {
	void save(User user);

	User login(User user);
}
