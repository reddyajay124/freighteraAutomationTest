package ca.freightera.automation.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ca.freightera.automation.base.TestBase;
import ca.freightera.automation.pages.HomePage;
import ca.freightera.automation.pages.LoginPage;
import ca.freightera.automation.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		initialization(browser);
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		 homePage= loginPage.loginAndContinue(prop.getProperty("userName"), prop.getProperty("password"));

		}

	@Test
	public void verifyHomePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, prop.getProperty("homePageTitle"));
	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
