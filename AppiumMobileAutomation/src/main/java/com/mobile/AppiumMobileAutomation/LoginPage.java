package com.mobile.AppiumMobileAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage {
	private AppiumDriver driver;
    private WebDriverWait wait;

    @AndroidFindBy(id = "com.example.app:id/usernameEditText")
    public MobileElement usernameField;

    @AndroidFindBy(id = "com.example.app:id/passwordEditText")
    public MobileElement passwordField;

    @AndroidFindBy(id = "com.example.app:id/loginButton")
    public MobileElement loginButton;

    @AndroidFindBy(id = "com.example.app:id/loginButton")
    public MobileElement loginButtonTest;
    
    @AndroidFindBy(id = "com.example.app:id/enterCaptchaText")
    public MobileElement captchaElement;
    
    @AndroidFindBy(id = "com.example.app:id/loginFailMessage")
    public MobileElement loginFailMessage;

    @AndroidFindBy(id = "com.example.app:id/forgotPassword")
    public MobileElement forgotPasswordButton;
    
    @AndroidFindBy(id = "com.example.app:id/emailEditText")
    public MobileElement enterEmailToRecoverPassword;
    
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    /*
     * This method will allow to enter email id or username for login
     */
    public MobileElement enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
		return usernameField;
    }

    /*
     * This method will allow to enter Password after entering email id for login
     */
    public MobileElement enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
		return passwordField;
    }

    /*
     * This method will allow to click login button
     */
    public MobileElement clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
		return loginButton;
    }
    
    /*
     * This method will allow check catcha Screen and click on it
     */
    public MobileElement captchaScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(captchaElement));
        captchaElement.click();
		return captchaElement;
    }
    
    /*
     * This will show error message when user try to login with invalid credentials
     */
    public MobileElement loginFailureMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginFailMessage));
		return loginFailMessage;
    }
    
    /*
     * This will allow to click "Forgot Password" button to reset new password
     */
    public MobileElement clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));
		return forgotPasswordButton;
    }
    
    /*
     * This method will check element to check email id field on reset password screen
     */
    public MobileElement resetPasswordScreen() {
    	return enterEmailToRecoverPassword;
    }
    
    /*
     * This method will allow to enter email id to reset password
     */
    public MobileElement enterEmailToResetPassword(String email) {
    	wait.until(ExpectedConditions.visibilityOf(enterEmailToRecoverPassword));
    	enterEmailToRecoverPassword.sendKeys(email);
    	return enterEmailToRecoverPassword;
    }
}


