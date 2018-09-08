package com.gmail.qa.pages;

import com.gmail.qa.bo.Letter;
import org.openqa.selenium.By;

public class NewMessagePage extends AbstractPage {

    private static final By ADDRESS_INPUT_LOCATOR = By.xpath("//textarea[@name='to']");
    private static final By SUBJECT_INPUT_LOCATOR = By.xpath("//input[@name='subjectbox']");
    private static final By BODY_INPUT_LOCATOR = By.xpath("//div[@aria-label='Message Body']");
    private static final By SEND_BUTTON_LOCATOR = By.xpath("//div[@role='button' and contains(@data-tooltip,'Send')]");

    public HomePage sendMessage(Letter letter) {
        browser.type(ADDRESS_INPUT_LOCATOR, letter.getRecipient());
        browser.type(SUBJECT_INPUT_LOCATOR, letter.getSubject());
        browser.type(BODY_INPUT_LOCATOR, letter.getContent());
        browser.click(SEND_BUTTON_LOCATOR);
        return new HomePage();
    }
}