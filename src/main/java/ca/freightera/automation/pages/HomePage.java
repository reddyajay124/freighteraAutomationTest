package ca.freightera.automation.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import ca.freightera.automation.base.TestBase;

public class HomePage extends TestBase {
	
	//Check homepage after user logins
	@FindBy(xpath = "//div[@class='']")
	WebElement checkHomePageFields1;
	@FindBy(xpath = "//img[@class='']")
	WebElement checkHomePageFields2;

	Logger logger = Logger.getLogger(this.getClass());
	//ToDo log4j
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}



	/* checks all inventory image links */
	public boolean isAllInventoryImagesClickable () throws IOException {
		boolean isDisplayed = true;
		List<WebElement> list = driver.findElements(By.xpath("//img[@class='img']"));
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getAttribute("src"));
			HttpURLConnection connection = (HttpURLConnection) new URL(list.get(i).getAttribute("src"))
					.openConnection();
			connection.connect();
			String message = connection.getResponseMessage();
			connection.disconnect();
			//ToDO use log4j
			
			logger.debug(list.get(i).getAttribute("src") + "----->>" + message + "response code is "
					+ connection.getResponseCode());
			if (!message.equalsIgnoreCase("Not Found")) {
				continue;
			} else {
				isDisplayed = false;
				break;
			}
		}
		return isDisplayed;

	}

	// find all broken links in home page
	public void findAllBrokenLinks() throws MalformedURLException, IOException {

		List<WebElement> list = driver.findElements(By.tagName("a")); //find by tagName 'a'
		list.addAll(driver.findElements(By.tagName("img"))); //find by tagName 'img'

		logger.info("list of all a and img " + list.size());
		List<WebElement> activeList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getAttribute("href"));
			if (list.get(i).getAttribute("href") != null
					&& !(list.get(i).getAttribute("href").contains("javascript"))) {
				activeList.add(list.get(i)); // get all active list
			}
		}

		logger.info("list of active href's" + activeList.size());

		for (int j = 0; j < activeList.size(); j++) {
			HttpURLConnection connection = (HttpURLConnection) new URL(activeList.get(j).getAttribute("href"))
					.openConnection();
			connection.connect();
			String message = connection.getResponseMessage(); // or check connection.getResponseCode() 
			connection.disconnect();
			logger.info(activeList.get(j).getAttribute("href") + "----->>" + message); 
		}

	}

}
