package ca.freightera.automation.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.freightera.automation.base.TestBase;

public class LoginPage extends TestBase {

	// page factory or object repository

	@FindBy(id = "LoginFormEmail")
	WebElement userName;

	@FindBy(id = "LoginFormPassword")
	WebElement password;

	@FindBy(xpath = "//button[@type = 'button' and contains(@class,'trackingIDButton')]")
	WebElement loginButton;

	//check login page
	@FindBy(xpath = "//div[contains(@title,'Freightera')]")
	WebElement pageLogo;


	// Initializing page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateLoginLogo() {
		return pageLogo.isDisplayed();
	}


	public boolean isUserNameFieldDisplayedAndEditable() {
		return isFieldDisplayedAndEditable(userName);
	}

	public boolean isPasswordFieldDisplayedAndEditable() {
		return isFieldDisplayedAndEditable(password);
	}

	public void login(String un, String psw) {
		userName.sendKeys(un);
		password.sendKeys(psw);
		loginButton.click();
	}
	public HomePage loginAndContinue(String un, String psw) {
		userName.sendKeys(un);
		password.sendKeys(psw);
		loginButton.click();
		return new HomePage();
	}


	public boolean isCurrentPage() {
		return doesElementExistsById("user-name");
	}

}