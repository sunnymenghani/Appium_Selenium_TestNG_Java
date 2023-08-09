package com.mobile.AppiumMobileAutomation;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ForgotPasswordPage {

	private AppiumDriver driver;
    private WebDriverWait wait;

    @AndroidFindBy(id = "com.example.app:id/emailEditText")
    public MobileElement enterEmailToRecoverPassword;
    
    @AndroidFindBy(id = "com.example.app:id/recoverPasswordButton")
    public MobileElement recoverPasswordButton;
    
    @AndroidFindBy(id = "com.example.app:id/loginFailMessage")
    public MobileElement resetPasswordMailSentMessage;
    
    @AndroidFindBy(id = "com.example.app:id/otpEditText")
    public MobileElement enterOTP;
    
    @AndroidFindBy(id = "com.example.app:id/submitOtpButton")
    public MobileElement submitOTP;
    
    @AndroidFindBy(id = "com.example.app:id/newPasswordEditText")
    public MobileElement enterNewPasswordElement;
    
    @AndroidFindBy(id = "com.example.app:id/submitNewPasswordButton")
    public MobileElement setNewPasswordElement;
    
    public ForgotPasswordPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
    
    /*
     * This method will return element for resetPasswordScreen
     */
    public MobileElement resetPasswordScreen() {
    	return enterEmailToRecoverPassword;
    }
    
    /*
     * This method will allow to enter email id to get reset password
     */
    public MobileElement enterEmailToResetPassword(String email) {
    	wait.until(ExpectedConditions.visibilityOf(enterEmailToRecoverPassword));
    	enterEmailToRecoverPassword.sendKeys(email);
    	return enterEmailToRecoverPassword;
    }
    
    /*
     * This method will allow to enter the OTP before creating new password
     */
    public MobileElement enterOtpToResetPassword(String otp) {
    	wait.until(ExpectedConditions.visibilityOf(enterOTP));
    	enterOTP.sendKeys(otp);
    	return enterOTP;
    }
    
    /*
     * This method will allow to submit once enter OTP to create new password
     */
    public MobileElement submitOtpButton() {
    	wait.until(ExpectedConditions.visibilityOf(submitOTP));
    	submitOTP.click();
    	return submitOTP;
    }

    /*
     * This method will allow to enter the new password for creating new password
     */
    public MobileElement enterNewPassword(String newPassword) {
    	wait.until(ExpectedConditions.visibilityOf(enterNewPasswordElement));
    	enterNewPasswordElement.sendKeys(newPassword);
    	return enterNewPasswordElement;
    }
    
    /*
     * This method will submit the new password
     */
    public MobileElement submitNewPasswordButton() {
    	wait.until(ExpectedConditions.visibilityOf(setNewPasswordElement));
    	setNewPasswordElement.click();
    	return setNewPasswordElement;
    }
    
}
