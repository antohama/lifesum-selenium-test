package com.selenium.tests;

import static com.selenium.testdata.UserDataGenerator.loadUsersFromXML;

import java.io.IOException;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.testdata.UserData;


public class CreateAccountTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> userDataFromFile() throws IOException {
		return wrapUserDataForProvider(loadUsersFromXML("users.xml")).iterator();
		
/*		List<Object[]> users = new ArrayList<Object[]>();
		Random rnd = new Random();
		
		UserData user = new UserData();
		users.add(new Object[]{user});
		
		return users.iterator();*/
	}
	
	@Test
	public void testErrorMessages(){
		app.getSignupHelper().setupNewUser(new UserData())
							 .startUserCreationWizard()
							 .signupFormGotoNext();
		Assert.assertEquals(app.getSignupHelper().getErrorMessage(), "You need to select a unit system");
	
		app.getSignupHelper().setMeasurementUnitsSystem()
							 .setUserDateOfBirth()
							 .setUserHeightAndWeight()
							 .signupFormGotoNext();
		Assert.assertEquals(app.getSignupHelper().getErrorMessage(), "Please fill in the form");
		
	}
	
	@Test (dataProvider = "userDataFromFile")
	public void signupFormCompleted(UserData user){
		
		app.getSignupHelper().setupNewUser(user)
							 .startUserCreationWizard()
							 .setMeasurementUnitsSystem()
							 .setUserGender()
							 .setUserDateOfBirth()
							 .setUserHeightAndWeight()
							 .signupFormGotoNext()
							 .setUserActivityLevel()
							 .setGoalWeight()
							 .setLooseRate()
							 .signupFormGotoNext()
							 .setUserName()
							 .setPassword()
							 .agreeTerms()
							 .submit();
	}
	
	/*@Test
	public void signupFormStepNumber1(){
		String stepNumber = driver.findElement(By.xpath("//div[@class='signup-button-sections']/p")).getText();
			Assert.assertEquals("Step 1 of 4", stepNumber);
		} 	
	
	@Test
	public void signupFormStepNumber2(){
		String stepNumber = driver.findElement(By.xpath("//div[@class='signup-button-sections']/p")).getText();
		Assert.assertEquals("Step 2 of 4", stepNumber);
		
	} */
}
