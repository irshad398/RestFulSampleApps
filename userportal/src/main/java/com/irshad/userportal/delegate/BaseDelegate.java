package com.irshad.userportal.delegate;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.irshad.userportal.service.UserService;


public class BaseDelegate {
	private static final Logger log = Logger.getLogger(BaseDelegate.class);
	
	private Connection connection;
	private static DataSource dbSource = null;


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected void endDBTransaction(Connection connection) {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void endDBTransaction(Connection connection, boolean isRollback) {

		if (isRollback) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			endDBTransaction(connection);
		}

	}

	protected Connection startDBTransaction() {
		try {
			if (connection == null || connection.isClosed())
				connection =getConnectionIfNull();

			connection.setAutoCommit(false);
		} catch (SQLException e) {
//			log.logError("SQLException in startDBTransaction " + e.getMessage(), e);
			e.printStackTrace();
		}
		return connection;

	}

	protected Connection getConnectionIfNull() throws SQLException
	{
		Connection dbCon;
		try {
			if (dbSource == null) {
				log.info("DataSource  looking up URL " + "java:/mytestjndi");
				InitialContext aInitialContext = new InitialContext();
				dbSource = (DataSource) aInitialContext.lookup("java:/mytestjndi");

				log.debug("DataSource dbSource was null and was successfully setup by looking up URL "
						+ "java:/mytestjndi");
			}
		} catch (NamingException e) {
			log.error("NamingException in initialize ", e);
		} catch (Exception e) {
			log.error("Exception in initialize ", e);
		}
		
		dbCon = dbSource.getConnection();
		dbCon.setAutoCommit(false);
		return dbCon;
	}
}
