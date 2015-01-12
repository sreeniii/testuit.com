package com.testuit.app.dao;

import com.testuit.app.model.User;

public interface IUserDao extends IGenericDao<User, Long> {

	public User findByUserName(String username);
}
