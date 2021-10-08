package com.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//SQLite 데이터베이 파일에 연결
			String dbFile = "todolist.db";
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			TodoMain.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}

}