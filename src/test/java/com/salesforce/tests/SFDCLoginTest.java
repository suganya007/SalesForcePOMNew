package com.salesforce.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;
import com.salesforce.homepages.HomePage;
import com.salesforce.loginpages.LoginPage;
import com.salesforce.utility.CommonUtilities;

public class SFDCLoginTest extends BaseTest
{
	
	@Test
	public static void loginErrorMsg() throws IOException
	{	
		String expected = CommonUtilities.getApplicationProperty("loginerror");
		LoginPage login=new LoginPage(driver);
		String userName=CommonUtilities.getApplicationProperty("username"); 
		String passwrd=CommonUtilities.getApplicationProperty("nopassword");
		login.login(userName, passwrd);
		String actual = login.GetText();
		Assert.assertEquals(actual,expected);		
	}
	
	@Test
	public static void checkRememberMe() throws InterruptedException
	{
		LoginPage login=new LoginPage(driver);
		String userName=CommonUtilities.getApplicationProperty("username"); 
		String passwrd=CommonUtilities.getApplicationProperty("password");
		login.chkRemember();
		login.login(userName, passwrd);
		HomePage home = new HomePage(driver);
		home.logout();
		String expected = CommonUtilities.getApplicationProperty("username"); 
		String actual = login.GetID();
		Assert.assertEquals(actual,expected);	
	}
	
	@Test
	public static void forgotPasswd()
	{
		LoginPage login=new LoginPage(driver);
		String userName=CommonUtilities.getApplicationProperty("username"); 
		login.forgotPassword();
		login.enterUsernameForgotPW(userName);
		login.continueButton();
		String expected = CommonUtilities.getApplicationProperty("forgotpw");
		String actual = login.GetTextForgotPW();
		Assert.assertEquals(actual,expected);	
	}
	
	@Test
	public static void validateErrorMsg() throws IOException
	{	
		String expected = CommonUtilities.getApplicationProperty("errormsg");
		LoginPage login=new LoginPage(driver);
		String userName=CommonUtilities.getApplicationProperty("wrongusername"); 
		String passwrd=CommonUtilities.getApplicationProperty("wrongpassword");
		login.login(userName, passwrd);
		String actual = login.GetText();
		Assert.assertEquals(actual,expected);		
	}

}