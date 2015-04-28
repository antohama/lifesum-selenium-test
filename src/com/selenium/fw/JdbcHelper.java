package com.selenium.fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.selenium.testdata.UserData;
import com.selenium.testdata.UserData.Gender;
import com.selenium.testdata.UserData.Region;

public class JdbcHelper {

	private Connection conn;
	
	public JdbcHelper(ApplicationManager app, String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<UserData> listUsers() {
	  List<UserData> users = new ArrayList<UserData>();
		Statement st = null;
		ResultSet res = null;
		
		try {
			st = conn.createStatement();
			res = st.executeQuery("SELECT region, gender, year, month, day, height, weight FROM Users JOIN");
			while (res.next()) {
				UserData user = new UserData().setRegion(Region.valueOf(res.getString(1)))
											  .setGender(Gender.valueOf(res.getString(2)))
											  .setYear(Integer.parseInt(res.getString(3)))
											  .setMonth(Integer.parseInt(res.getString(4)))
											  .setDay(Integer.parseInt(res.getString(5)))
											  .setHeight(Integer.parseInt(res.getString(6)))
											  .setWeight(Integer.parseInt(res.getString(7)));
											  
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
	}
}
