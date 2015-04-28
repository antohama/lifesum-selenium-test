package com.selenium.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.testdata.UserData;

public class SignupHelper extends WebDriverHelperBase {

	private UserData user;

	public SignupHelper(ApplicationManager manager) {
		super(manager);
	}

	public SignupHelper setupNewUser(UserData user) {

		this.user = user;
		return this;
	}

	public SignupHelper startUserCreationWizard() {
		manager.navigateTo().mainPage();
		/*
		 * Dimension windowSize = driver.manage().window().getSize(); int height
		 * = windowSize.height; int width = windowSize.width;
		 * 
		 * 
		 * while ( driver.findElements(By.linkText("SIGN UP")).size() == 0 ) {
		 * driver.manage().window().setSize(new Dimension(width++, height)); }
		 */

		clickOnElement(By.linkText("SIGN UP"));

		// WebElement wizardForm =
		new WebDriverWait(driver, 3).until(ExpectedConditions
				.visibilityOfElementLocated(By
						.xpath("//*[@id='signup-modal']/div")));
		// Assert.assertTrue(driver.findElement(By.xpath("//*[@id='signup-modal']/div")).isDisplayed());

		return this;
	}

	public SignupHelper signupFormGotoNext() {
		clickOnElement(By.cssSelector(".next-step"));
		return this;
	}

	public SignupHelper setMeasurementUnitsSystem() {
		clickOnElement(By.xpath(getRegionLocator(user)));
		sleep(2);

		return this;
	}

	public SignupHelper setUserGender() {
		clickOnElement(By.xpath(getGenderLocator(user)));

		return this;
	}

	public SignupHelper setUserDateOfBirth() {

		WebElement dropDown = driver.findElement(By.id("year"));
		dropDown.click();
		new Select(dropDown).selectByValue(user.getYear());
		dropDown.click();

		dropDown = driver.findElement(By.id("date"));
		dropDown.click();
		new Select(dropDown).selectByValue(user.getDay());
		dropDown.click();

		dropDown = driver.findElement(By.id("month"));
		dropDown.click();
		new Select(dropDown).selectByValue(user.getMonth());
		dropDown.click();

		return this;
	}

	public SignupHelper setUserHeightAndWeight() {
		List<WebElement> heightInputs = driver
				.findElements(By
						.cssSelector("div.allMetrix[style*='display: block;']>div>input.height"));
		for (WebElement heightInput : heightInputs) {
			heightInput.sendKeys(String.valueOf(user.getHeight()));
		}

		List<WebElement> weightInputs = driver
				.findElements(By
						.cssSelector("div.allMetrix[style*='display: block;']>div>input.weight"));
		for (WebElement weightInput : weightInputs) {
			weightInput.sendKeys(String.valueOf(user.getWeight()));
		}

		return this;
	}

	public SignupHelper setUserActivityLevel() {
		clickOnElement(By
				.xpath("//div[contains(@class, 'activity-level')]/ul/li["
						+ user.getActivity() + "]"));
		return this;
	}

	public SignupHelper setGoalWeight() {
		typeInElement(
				By.cssSelector("div.all_looseWeight[style*='display: block;']>p>input.looseWeight"),
				user.getGoalWeight());
		return this;
	}

	public SignupHelper setLooseRate() {
		clickOnElement(By
				.xpath("//div[contains(@class, 'all_looseWeight')][@style='display: block;']/ul/li["
						+ user.getLooseRate() + "]"));
		return this;
	}

	public String getErrorMessage() {
		String message = driver
				.findElement(By.cssSelector("div.signup-errors")).getText();
		return message;
	}

	public SignupHelper setUserName() {
		typeInElement(By.id("signup_email"),
				manager.properties.getProperty("login"));
		return this;
	}

	public SignupHelper setPassword() {
		typeInElement(By.id("signup_password"),
				manager.properties.getProperty("password"));
		return this;

	}

	public SignupHelper agreeTerms() {
		clickOnElement(By.cssSelector("input.termsOfService"));
		return this;
	}

	public SignupHelper submit() {
		clickOnElement(By.id("postSignup"));
		return this;
	}

	// ---------------------------- H E L P E R S ----------------------------

	private String getGenderLocator(UserData user) {
		String genderLocator = "";

		switch (user.getGender()) {
		case Male:
			genderLocator = "//li[@data-id='male']";
			break;

		case Female:
			genderLocator = "//li[@data-id='female']";
			break;
		}

		return genderLocator;
	}

	private String getRegionLocator(UserData user) {
		String regionLocator = "";

		switch (user.getRegion()) {
		case US:
			regionLocator = "//li[@data-id='us-system']";
			break;

		case EU:
			regionLocator = "//li[@data-id='eu-system']";
			break;

		case IMP:
			regionLocator = "//li[@data-id='imperial-system']";
			break;

		case AUS:
			regionLocator = "//li[@data-id='australian-system']";
			break;
		}

		return regionLocator;
	}
}
