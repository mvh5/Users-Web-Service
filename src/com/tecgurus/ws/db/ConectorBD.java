package com.tecgurus.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConectorBD {
	
	public static void main(String[] args) {
		Connection conn = ConectorBD.getConexionBD();
		if (conn != null) {
			
			ConectorBD.close(conn, null);
			System.out.println("Connection is alive");
		}
	}
	
	public static Connection getConexionBD() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/ws_tecgurus";
			String user = "root";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url,user,password);
			
			return conn;
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps) {
		if (conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
