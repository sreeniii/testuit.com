package com.testuit.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testuit.app.dao.IUserDao;
import com.testuit.app.model.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements IUserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public User findByUserName(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User> users = _findByCriteria(criteria);
		if(users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
