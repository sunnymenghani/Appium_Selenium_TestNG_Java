package com.mobile.AppiumMobileAutomation;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginTest {
		private String USERNAME = "test@gmail.com";
		private String PASSWORD = "Test@1234";
		private String successMessage = "Password Send to register email";
		private String otpString = "1234";
		private String enterNewPasswordString = "NewPassCode@1234";
		  
		private AppiumDriver<MobileElement> driver;
	    private LoginPage loginPage;
	    private ForgotPasswordPage forgotPasswordPage;

	    @BeforeClass
	    public void setUp() throws Exception {
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("deviceName", "Your_Device_Name");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("appPackage", "com.example.app");
	        capabilities.setCapability("appActivity", ".MainActivity");
	        capabilities.setCapability("automationName", "UiAutomator2");

	        URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
	        driver = new AndroidDriver<>(appiumServerURL, capabilities);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        loginPage = new LoginPage(driver);
	    }

	    @Test(priority = 1)
	    public void testLogin() {
	        loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        loginPage.clickLoginButton();
	        Assert.assertFalse(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.captchaScreen().isDisplayed());
	    }
	    
//	    @Test(priority = 2)
//	    public void testLoginFailureMessage() {
//	        loginPage.enterUsername(USERNAME +"com");
//	        loginPage.enterPassword(PASSWORD + "test");
//	        loginPage.clickLoginButton();
//	        Assert.assertTrue(loginPage.clickLoginButton().isDisplayed());
//	        Assert.assertTrue(loginPage.loginFailureMessage().isDisplayed());
//	               
//	    }
	    
	    @Test(priority = 2)
	    public void testInvalidLoginFailure() {
	        loginPage.enterUsername(USERNAME +"com");
	        loginPage.enterPassword(PASSWORD + "test");
	        loginPage.clickLoginButton();
	        Assert.assertTrue(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.loginFailureMessage().isDisplayed());
	        
	        String username = loginPage.usernameField.getText();
	        Assert.assertEquals(username,USERNAME);
	        
//	        String password = loginPage.passwordField.getText();
//	        Assert.assertEquals(password,PASSWORD);
	        
	    }
	    
	    @Test(priority = 3)
	    public void testLoginButtonStatusWithoutCredentials() {
	        Assert.assertFalse(loginPage.clickLoginButton().isEnabled());
	        
	    }
	    
	    @Test(priority = 4)
	    public void testLoginButtonStatusWithCredentials() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        Assert.assertTrue(loginPage.clickLoginButton().isEnabled());
	        
	    }
	    
	    @Test(priority = 5)
	    public void clickForgotPassword() {
	    	loginPage.clickForgotPassword();
	    	Assert.assertFalse(loginPage.clickForgotPassword().isDisplayed());
	    	Assert.assertTrue(loginPage.resetPasswordScreen().isDisplayed());
	    }
	    
	    @Test(priority = 6)
	    public void resetNewPassword() {
	    	loginPage.clickForgotPassword();
	    	Assert.assertFalse(loginPage.clickForgotPassword().isDisplayed());
	    	Assert.assertTrue(forgotPasswordPage.resetPasswordScreen().isDisplayed());
	    	
	    	forgotPasswordPage.enterEmailToResetPassword(USERNAME);
	    	forgotPasswordPage.recoverPasswordButton.click();
	    	
	    	String passwordSendMessageString = forgotPasswordPage.
	    			resetPasswordMailSentMessage.getText();
	    	Assert.assertEquals(passwordSendMessageString,successMessage);
	    	
	    	forgotPasswordPage.enterOtpToResetPassword(otpString);
	    	forgotPasswordPage.submitOtpButton();
	    	
	    	forgotPasswordPage.enterNewPassword(enterNewPasswordString);
	    	forgotPasswordPage.submitNewPasswordButton();
	    	
	    	Assert.assertFalse(loginPage.clickLoginButton().isDisplayed());
	    	
	    }
	    
	    @Test(priority = 7)
	    public void loginWithOldPassword() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        loginPage.clickLoginButton();
	        Assert.assertTrue(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.loginFailureMessage().isDisplayed());
	    }
	    
	    @Test(priority = 8)
	    public void loginWithNewPassword() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(enterNewPasswordString);
	        loginPage.clickLoginButton();
	        Assert.assertFalse(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.captchaScreen().isDisplayed());
	    }
	    
	    @AfterTest
	    public void resetApp() {
	    	driver.resetApp();
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }


}
