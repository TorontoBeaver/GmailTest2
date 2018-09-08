package com.gmail.qa.pages;

import com.gmail.qa.bo.Letter;
import org.openqa.selenium.By;

public class HomePage extends AbstractPage {

	private static final By COMPOSE_BUTTON_LOCATOR = By.xpath("//div[@gh='cm']");
	private static final By INBOX__BUTTON_LOCATOR = By.xpath("//a[contains(@title,'Inbox')]");
	private static final By MORE__BUTTON_LOCATOR = By.xpath("//span[@class='CJ'][contains(.,'More')]");
	private static final By TRASH__BUTTON_LOCATOR = By.xpath("//a[@title='Trash']");
	private static final By USER_AVATAR_LOCATOR = By.xpath("//a[contains(@aria-label,'potlightexpress@gmail.com')]");
	private static final By DELETE_BUTTON_LOCATOR = By.xpath("//*[@id=\":2\"]/div/div/div/div/div/div[1]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[3]");

	public NewMessagePage clickComposeButton() {
		browser.click(COMPOSE_BUTTON_LOCATOR);
		return new NewMessagePage();
	}
	public void clickMoreButton() {
		browser.click(MORE__BUTTON_LOCATOR);
	}

	public TrashPage clickTrashButton() {
		browser.click(TRASH__BUTTON_LOCATOR);
		return new TrashPage();
	}
	public boolean isLetterPresent(Letter letter) {
		By xpath = By.xpath("//span[text()='" + letter.getSubject() + "']");
		return browser.isDisplayed(xpath);
	}

	public HomePage deleteMyMessage(Letter letter) {

		browser.clickElementPresent(INBOX__BUTTON_LOCATOR);

		browser.clickElementPresent(By.xpath("//span[contains(text(),'" + letter.getSubject() + "')]"));

		browser.clickElementPresent(DELETE_BUTTON_LOCATOR);

		return new HomePage();
	}
	public boolean isUserAvatarDisplayed() {
		return browser.isDisplayed(USER_AVATAR_LOCATOR);
	}
}