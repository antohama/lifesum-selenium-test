package com.selenium.fw;

public class NavigationHelper extends WebDriverHelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		driver.get(manager.baseURL);
	}

}
