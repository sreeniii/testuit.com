package com.testuit.app.service;

import org.springframework.security.core.userdetails.UserDetails;


public interface IUserService {

	public UserDetails loadUserByUsername(final String username);
}
