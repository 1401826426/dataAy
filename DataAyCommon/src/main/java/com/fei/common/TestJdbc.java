package com.fei.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;






public class TestJdbc {

	static {
		try {
			TestJdbc.class.getClassLoader().loadClass("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://120.79.54.108:3306/data_ay?useUnicode=true&characterEncoding=utf8&useSSL=false";
		String user = "root";
		String password = "qwert";
		Connection connection = DriverManager.getConnection(url, user, password) ;
		
		Statement statement = connection.createStatement() ; 
		ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()") ;
		while(rs.next()){
			System.err.println(rs.getString(1)) ; 
		}
		
		String sql = "insert  user(`name`,`password`) values(?,?)" ; 
		PreparedStatement preparedStatement = connection.prepareStatement(sql) ; 
		preparedStatement.setString(1, "sdkf");
		preparedStatement.setString(2, "ssadmqwf");
		preparedStatement.execute() ; 
	}

}
