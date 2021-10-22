package ca.freightera.automation.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.beust.jcommander.Parameter;

import ca.freightera.automation.base.TestBase;
import ca.freightera.automation.pages.HomePage;
import ca.freightera.automation.pages.LoginPage;
import ca.freightera.automation.util.TestUtil;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homepage;
	TestUtil testUtil;
	String sheetName = "Sheet1";

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String borwser) {

		initialization(borwser);

		loginPage = new LoginPage();
	}

	@Test
	public void loginPageTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, prop.getProperty("logingPageTitle"));
	}

	@Test
	public void swagLogoImgTest() {
		boolean isDisplayed = loginPage.validateLoginLogo();
		Assert.assertTrue(isDisplayed, "Login page logo is not displayed");
	}


	@Test
	public void isFieldsDisplayed() {
		Assert.assertTrue(
				loginPage.isUserNameFieldDisplayedAndEditable() && loginPage.isPasswordFieldDisplayedAndEditable(),
				"UserName/Password field is neither displayed or editable");
	}

	@DataProvider
	public Object[][] getLoginTestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}

	
	// can use sheet to get data
	@Test(dataProvider = "getLoginTestData")
	public void validateTestData(String userName, String password) {
		loginPage.login(userName, password);
		if (loginPage.isCurrentPage()) {
			//TODO: we can check errors if username or password entered is not correct
			//verifyErrorMessage(userName, password);
			Assert.assertFalse(loginPage.isCurrentPage(),
					"Error while logging with userName " + userName + " and password " + password);
		}

	}

	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
