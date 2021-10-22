package ca.freightera.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.freightera.automation.base.TestBase;

public class SignUpPageForShipper extends TestBase {
	// page factory or object repository

		@FindBy(id = "SignInFirstName")
		WebElement userFirstName;

		@FindBy(id = "SignInLastName")
		WebElement userLastName;
		
		@FindBy(id = "SignInCompany")
		WebElement signInCompany;
		
		@FindBy(id = "CompanyWebsite")
		WebElement companyWebSite;
		
		@FindBy(id = "SignInEmail")
		WebElement signInEmail;
		
		@FindBy(id = "SignInPhone")
		WebElement signInPhone;
		

		@FindBy(xpath = "//button[@type = 'button' and contains(@class,'sigin-modal-btn')]")
		WebElement signUpButton;

		// Initializing page objects
		public SignUpPageForShipper() {
			PageFactory.initElements(driver, this);
		}

		// Actions
		
		public String validateLoginPageTitle() {
			return driver.getTitle();
		}

		public boolean validateLoginLogo() {
			return pageLogo.isDisplayed();
		}


		public boolean isUserFirstNameFieldDisplayedAndEditable() {
			return isFieldDisplayedAndEditable(userFirstName);
		}

		public boolean isUserLastNameFieldDisplayedAndEditable() {
			return isFieldDisplayedAndEditable(userLastName);
		}
//TODO can do same check for other fields if required
		
		public void SignUp(String ufn, String uln, String cn, String cw, String be, String phn) {
			userFirstName.sendKeys(ufn);
			userLastName.sendKeys(uln);
			signInCompany.sendKeys(cn);
			companyWebSite.sendKeys(cw);
			signInEmail.sendKeys(be);
			signInPhone.sendKeys(phn);
			
			signUpButton.click();
		}

}
