package com.selenium.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class WebDriverHelperBase extends HelperBase {

	protected ApplicationManager manager;
	protected WebDriver driver;
	
	public WebDriverHelperBase (ApplicationManager manager) {
		super(manager);
		this.manager = manager;		
		this.driver = manager.driver;
	}

	protected void clickOnElement(By locator) {
		WebElement element = new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		System.out.println("Log: clicked on element " + element.getTagName() + ", located by " + locator.toString());
	}

	protected void typeInElement(By locator, String text) {
		WebElement element = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
		System.out.println("Log: typed " + text + " in element " + element.getTagName() + ", located by " + locator.toString());
	}

	protected void sleep(int seconds) {
		
		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			Assert.fail("Test execution has been interrupted. Reason: " + e.getMessage() );
		}
		
	}

}
