package com.mojocare.test.shop;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ShopMojocareTest {
	private WebDriver driver;
	private WebElement Element, phoneNumber, buyNowButton, loginbutton;
	private JavascriptExecutor js;

	@BeforeClass
	@Parameters({ "browserName", "driverPath" })

	public void testLaunchApplication(String browserName, String driverPath) throws Exception {

		if (browserName.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("SAFARI")) {
			System.setProperty("webdriver.safari.driver", driverPath);
			driver = new SafariDriver();
		} else {
			throw new Exception(
					"Incorrect Browser\nChoose Browser from below list\n1.CHROME\n2.FIREFOX\n3.IE\n4.SAFARI");
		}

	}

	@Test(priority = 0)
	void testOpenWebsite() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://mojocare.com/");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;

	}

	@Test(priority = 1)
	public void testScrolldown() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		js.executeScript("window.scrollBy(0,1350)", "");
	}

	@Test(priority = 2)
	public void testAddItemToCart(WebDriver driver, JavascriptExecutor js) {
		Element = driver.findElement(By.xpath("//*[@id=\"hard and up\"]"));
		Element.click();
		System.out.println(Element.getText());

	}

	@Test(priority = 3)
	public void testBuyNow() throws InterruptedException {

		
		js.executeScript("window.scrollBy(0,1350)", "");
		buyNowButton = driver.findElement(By.xpath("//*[@id=\"gtm-buy-now\"]"));
		if (buyNowButton.isEnabled()) {
			buyNowButton.click();
			System.out.println("Pay Now Button is Clickable..");
		} else {
			System.out.println("Pay Now Button is Not Clickable..");
		}

	}

	@Test(priority = 4)
	public void testLogin() throws InterruptedException {

		loginbutton = driver.findElement(By.xpath("/html/body/div[3]/div/div/button"));
		loginbutton.click();

		phoneNumber = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div[3]/form/div/div[2]/input"));
		phoneNumber.sendKeys("8281163800");

	}

}
