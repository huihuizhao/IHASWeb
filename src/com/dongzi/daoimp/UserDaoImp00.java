package com.dongzi.daoimp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dongzi.db.GetConn;

public class UserDaoImp00 {
	public boolean login(String phonenumber,String date,String imagePath,String voicePath,String videoPath) 
	{
		boolean b = false;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		  String sql="insert into info (phonenumber,date,imagepath,voicepath,videopath) values (?,?,?,?,?)";  
	        try {  
	            PreparedStatement preStmt=conn.prepareStatement(sql);  
//	            preStmt.setString(1, "李四");  
//	            preStmt.setString(2, "123");  
	            preStmt.setString(1, phonenumber);  
	            preStmt.setString(2, date);  
	            preStmt.setString(3, imagePath);  
	            preStmt.setString(4, voicePath);  
	            preStmt.setString(5, videoPath);  
	              
	            preStmt.executeUpdate();  
	              
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
		
		
//		try {
//			PreparedStatement ps=conn.prepareStatement("select * from usermsg where username=? and password=?");
//			ps.setString(1,username);
//			ps.setString(2,password);
//			rs=ps.executeQuery();
//			if (rs.next())
//			{
//				b=true;
//			}
//			else
//			{
//				b=false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return b;
	}

}
