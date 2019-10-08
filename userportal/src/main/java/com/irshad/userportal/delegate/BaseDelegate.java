package com.irshad.userportal.delegate;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BaseDelegate {

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
				System.out.println("DataSource  looking up URL " + "java:jboss/datasources/newCheckoutMySqlDS");
				InitialContext aInitialContext = new InitialContext();
				dbSource = (DataSource) aInitialContext.lookup("java:jboss/datasources/newCheckoutMySqlDS");

				System.out.println("DataSource dbSource was null and was successfully setup by looking up URL "
						+ "java:jboss/datasources/newCheckoutMySqlDS");
			}
		} catch (NamingException e) {
			System.out.println("NamingException in initialize ");
		} catch (Exception e) {
			System.out.println("Exception in initialize ");
		}
		
		dbCon = dbSource.getConnection();
		dbCon.setAutoCommit(false);
		return dbCon;
	}
}
