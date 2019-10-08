package com.irshad.userportal.delegate;

import java.sql.Connection;

import com.irshad.userportal.bo.UserBO;
import com.irshad.userportal.model.User;

public class UserDelegate extends BaseDelegate {

	public void createUser(User user)
	{
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			UserBO userBO = new UserBO(getConnection());
			userBO.createUser(user);
		} catch (Exception e) {
			rollBack = true;
		}finally {
			endDBTransaction(connection, rollBack);
		}
	}
}
