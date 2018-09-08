package com.gmail.qa.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {

	private static int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
	private static int COMMAND_DEFAULT_TIMEOUT_SECONDS = 10;
	private static int WAIT_ELEMENT_TIMEOUT = 10;
	private static String SCREENSHOTS_NAME_TPL = "screenshots/scr";
	private WebDriver driver;

	private static Browser instance = null;


	private Browser(WebDriver driver) {
		this.driver = driver;
	}

	public static Browser getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = init();
	}

	private static Browser init() {
		System.setProperty("webdriver.chrome.driver", "/Users/vladimirnemtcev/Downloads/GmailTest 2/src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return new Browser(driver);
	}

	public void open(String url) {
		System.out.println("Going to URL: " + url);
		driver.get(url);
	}

	public static void kill() {
		if (instance != null) {
			try {
				instance.driver.quit();
			} finally {
				instance = null;
			}
		}
	}

	public void waitForElementPresent(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public void waitForElementEnabled(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementVisible(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void highlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
	}

	public void unHighlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
	}

	public void click(By locator) {
		waitForElementVisible(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		driver.findElement(locator).click();
	}

	public void clickElementPresent(By locator) {
		waitForElementPresent(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		driver.findElement(locator).click();
	}

	public void type(By locator, String text) {
		waitForElementVisible(locator);
		highlightElement(locator);
		System.out.println("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
		driver.findElement(locator).sendKeys(text);
		takeScreenshot();
		unHighlightElement(locator);
	}

	public String read(By locator) {
		waitForElementVisible(locator);
		highlightElement(locator);
		System.out.println("Reading text: " + driver.findElement(locator).getText());
		takeScreenshot();
		unHighlightElement(locator);
		return driver.findElement(locator).getText();
	}

	public boolean isDisplayed(By locator) {
		boolean succeed = driver.findElements(locator).size() > 0;
		if (succeed) {
			System.out.println("Element " + driver.findElement(locator).getText() + " is present.");
			highlightElement(locator);
			takeScreenshot();
			unHighlightElement(locator);
		} else System.out.println("Element " + driver.findElement(locator).getText() + " is not present.");
		return succeed;
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
			File copy = new File(screenshotName + ".png");
			FileUtils.copyFile(screenshot, copy);
			System.out.println("Saved screenshot: " + screenshotName);
		} catch (IOException e) {
			System.out.println("Failed to make screenshot");
		}
	}


	public String getTitle() {
		return driver.getTitle();
	}


	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

}
