package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.Accountpage;
import pageobjects.Landingpage;
import pageobjects.loginpage;
import resources.Base;

public class LoginTest extends Base {
	WebDriver driver;

	@Test(dataProvider="getLogindata")
	public void login(String email,String password,String expectedReuslt) throws IOException, InterruptedException {

		driver = intializeDriver();
		driver.get(prop.getProperty("url"));

		Landingpage landingpage = new Landingpage(driver);
		landingpage.myAccountDropdown().click();
		landingpage.loginOption().click();

		Thread.sleep(3000);

		loginpage Loginpage = new loginpage(driver);
		Loginpage.emailaddressfield().sendKeys(email);
		Loginpage.passwordfield().sendKeys(password);
		Loginpage.LoginButton().click();

		Accountpage accountpage = new Accountpage(driver);
		
		String actualResult = null;
		try {
			if(accountpage.edityourAccountInformationOption().isDisplayed()) {
			 actualResult ="Sucessfull";
			}
			
		}catch(Exception e) {
			actualResult = "Failure";
		}	
		
		Assert.assertEquals(actualResult,expectedReuslt);
		
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@DataProvider
	public Object[][] getLogindata() {
		Object[][]data = {{"shrikoli777@gmail.com","99222580Xyz@5","Successfull"},{"shri@test.com","1234","Failure"}};
		return data;
	}
}

	
	


