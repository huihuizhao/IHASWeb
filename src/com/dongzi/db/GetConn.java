package com.dongzi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
	public Connection getConnection() 
	{ 
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/�˴���DB��","DB�ʺ�","DB����");
//			connection=DriverManager.getConnection("jdbc:mysql://218.249.137.198:3306/smarthealth","root","123456");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthealth","root","123456");
//			connection=DriverManager.getConnection("jdbc:mysql://192.168.1.101:3306/smarthealth","root","123456");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {	
			e.printStackTrace();
		}   	
		return connection;
	}
	public void closeconn(Connection connection)
	{  	 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
