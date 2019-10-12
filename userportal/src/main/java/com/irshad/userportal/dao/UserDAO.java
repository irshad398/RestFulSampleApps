package com.irshad.userportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.irshad.userportal.model.User;
import com.irshad.userportal.service.UserService;

public class UserDAO extends BaseDAO{
	private static final Logger log = Logger.getLogger(UserDAO.class);
	
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
				log.info("Success:: Created the user!");
			else {}
			//throw error
		} catch (SQLException e) {
			log.error("SQLException in createUser ",e);
//			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
	}
}
