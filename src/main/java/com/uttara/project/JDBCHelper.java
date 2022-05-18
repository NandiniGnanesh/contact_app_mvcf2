package com.uttara.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCHelper {

	public static void close(ResultSet rs) {
		
		try {
			if( rs != null ) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection con) {
		
		try {
			if( con != null ) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void close(Statement stmt) {
	
		try {
			if( stmt != null ) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static Connection getConnection() {
		
		try {
			
			Class.forName( Constants.DNAME );
			Connection con = DriverManager.getConnection(Constants.URL , Constants.UID , Constants.PASSWORD);
			System.out.println("Established a connection , con = " +con);
			return con;
			
		} catch (Exception e) {
			 e.printStackTrace();
			 return null;
		}
	}
}

