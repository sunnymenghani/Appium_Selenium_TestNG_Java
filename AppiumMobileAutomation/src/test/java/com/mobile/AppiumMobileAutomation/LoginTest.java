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
	        forgotPasswordPage = new ForgotPasswordPage(driver);
	        
	    }

	    @Test(description = "Verify that a user with valid credentials can successfully log in and access the system.")
	    public void testLogin() {
	        loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        Assert.assertTrue(loginPage.captchaScreen().isDisplayed());
	        loginPage.clickLoginButton();
	        Assert.assertFalse(loginPage.clickLoginButton().isDisplayed());
	    }
	    
	    
	    @Test(description = "Validate that an error message is displayed when attempting to log in with invalid credentials & entered username is there.")
	    public void testInvalidLoginFailure() {
	        loginPage.enterUsername(USERNAME +"com");
	        loginPage.enterPassword(PASSWORD + "test");
	        loginPage.clickLoginButton();
	        Assert.assertTrue(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.loginFailureMessage().isDisplayed());
	        
	        String username = loginPage.usernameField.getText();
	        Assert.assertEquals(username,USERNAME);
	        
	        
	    }
	    
	    @Test(description = "Test that the 'Login' button is disabled when both username and password fields are empty")
	    public void testLoginButtonStatusWithoutCredentials() {
	        Assert.assertFalse(loginPage.clickLoginButton().isEnabled());
	        
	    }
	    
	    
	    @Test(description = "Confirm that the 'Login' button is enabled only when both username and password fields are filled with valid input.")
	    public void testLoginButtonStatusWithCredentials() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        Assert.assertTrue(loginPage.clickLoginButton().isEnabled());
	        
	    }
	    
	    @Test(description = "Verify that clicking the 'Forgot Password' link redirects the user to the password recovery page.")
	    public void clickForgotPassword() {
	    	loginPage.clickForgotPassword();
	    	Assert.assertFalse(loginPage.clickForgotPassword().isDisplayed());
	    	Assert.assertTrue(loginPage.resetPasswordScreen().isDisplayed());
	    }
	    
	    @Test(description = "Validate the password recovery functionality")
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
	    
	    @Test(description = "After reseting new password try to login with old password")
	    public void loginWithOldPassword() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(PASSWORD);
	        loginPage.clickLoginButton();
	        Assert.assertTrue(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.loginFailureMessage().isDisplayed());
	    }
	    
	    @Test(description = "Login with new password after reseting it")
	    public void loginWithNewPassword() {
	    	loginPage.enterUsername(USERNAME);
	        loginPage.enterPassword(enterNewPasswordString);
	        loginPage.clickLoginButton();
	        Assert.assertFalse(loginPage.clickLoginButton().isDisplayed());
	        Assert.assertTrue(loginPage.captchaScreen().isDisplayed());
	    }
	    
	    @AfterTest(description = "It will reset the app to default after executing the test")
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
