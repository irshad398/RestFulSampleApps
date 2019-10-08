package com.irshad.userportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.irshad.userportal.model.User;

public class UserDAO extends BaseDAO{

	 
	public UserDAO() {
	
	}
	public UserDAO(Connection connection) {
		super(connection);
	}

	public void createUser(User user)
	{
		String sql = "INSERT INTO users(id,name) values(?,?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getName());
			int count = stmt.executeUpdate();
			if(count>0)
				System.out.println("Success");
			else {}
			//throw error
		} catch (SQLException e) {
			System.out.println("SQLException in createUser");
//			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
	}
}
