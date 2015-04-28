package com.selenium.tests;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.*;

import com.selenium.fw.ApplicationManager;
import com.selenium.testdata.UserData;

public class TestBase {
	
	protected ApplicationManager app;

	@BeforeTest
	public void setUp() throws IOException {
		Properties prop = new Properties();
		prop.load(new FileReader(new File("application.properties")));
		app = new ApplicationManager(prop);
	}
	
	@AfterTest
	public void tearDown() {
		app.stop();
	}

	protected static List<Object[]> wrapUserDataForProvider(List<UserData> users) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (UserData user : users) {
			list.add(new Object[]{user});
		}
		return list;	
	}

	

}
