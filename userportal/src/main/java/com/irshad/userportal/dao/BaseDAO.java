package com.irshad.userportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.irshad.userportal.service.UserService;

public class BaseDAO {
	private static final Logger log = Logger.getLogger(BaseDAO.class);
	
	private Connection connection;

	public BaseDAO() {
	
	}
	public BaseDAO(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				log.error("Exception in close ",e );
			}
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				log.error("Exception in close ", e);
			}
		}
	}

	public void close(PreparedStatement stmt, ResultSet rs) {
		close(stmt);
		close(rs);

	}
}
