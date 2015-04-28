package com.selenium.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

	public WebDriver driver;
	public String baseURL;

	private NavigationHelper navigationHelper;
	private SignupHelper userHelper;
	protected Properties properties;

	public ApplicationManager(Properties properties) {
		this.properties = properties;
		String browser = properties.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/home/antohama/selenium_test_suite/selenium-2.45.0/libs/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		baseURL = properties.getProperty("baseUrl"); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void stop() {
		driver.quit();
	}

	public NavigationHelper navigateTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public SignupHelper getSignupHelper() {
		if (userHelper == null) {
			userHelper = new SignupHelper(this);
		}
		return userHelper;
	}

}
