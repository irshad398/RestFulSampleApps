package com.irshad.userportal.bo;

import java.sql.Connection;

import com.irshad.userportal.dao.UserDAO;
import com.irshad.userportal.model.User;

public class UserBO extends BaseBO {
	public UserBO(Connection connection) {
		super(connection);
	}

	public void createUser(User user)
	{
		UserDAO userDAO = new UserDAO(getConnection());
		userDAO.createUser(user);
	}
}
