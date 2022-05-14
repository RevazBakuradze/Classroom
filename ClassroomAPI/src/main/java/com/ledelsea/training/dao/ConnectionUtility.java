package com.ledelsea.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionUtility {
	private static ConnectionUtility connectionUtility = null;
	
	private ConnectionUtility() {
		
	}

	private DataSource dataSource;

	public Connection getDSConnection() throws SQLException {
		if (dataSource == null) {
			try {
				dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MyMTSDB");
			} catch (NamingException e) {
				throw new IllegalStateException("jdbc/MyMTSDB is missing in JNDI!", e);
			}
		}
		
		return dataSource.getConnection();
	}
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom", "root", "CocoPowder");
		} catch (Exception e) {
			throw new IllegalStateException("Exception while creating exception ...", e);
		}
		
		return con;
	}
	public static ConnectionUtility getInstance() {
		
		if(connectionUtility == null) {
			connectionUtility = new ConnectionUtility();
		}
		return connectionUtility;
		
	}
	
	public void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
