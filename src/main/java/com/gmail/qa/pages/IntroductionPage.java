package com.gmail.qa.pages;

import com.gmail.qa.utils.PropertyLoader;
import org.openqa.selenium.By;

public class IntroductionPage extends AbstractPage {

    private static final By GMAIL_LOGO_LOCATOR = By.xpath("//div[contains(@class,'gmail-logo')]");
    private static final By SIGNIN_BUTTON_LOCATOR = By.xpath("//a[contains(@class,'sign-in')]");
    private static final By CREATE_AN_ACCOUNT_TOP_RIGHT_BUTTON_LOCATOR = By.xpath("//a[@class='gmail-nav__nav-link gmail-nav__nav-link__create-account']");
    private static final By CREATE_AN_ACCOUNT_MIDDLE_LEFT_BUTTON_LOCATOR = By.xpath("//a[@class='hero_home__link__desktop']");
    private static final By FOR_WORK_BUTTON_LOCATOR = By.xpath("//a[contains(@class,'for-work')]");

    public IntroductionPage open() {
        String url = PropertyLoader.loadProps("src/main/java/com/gmail/qa/config/config.properties").getProperty("welcomPageUrl");
        browser.open(url);
        return new IntroductionPage();
    }

    public boolean getGmailLogo() {
        return browser.isDisplayed(GMAIL_LOGO_LOCATOR);
    }

    public LogInPage openSignInPage() {
        browser.click(SIGNIN_BUTTON_LOCATOR);
        return new LogInPage();
    }

    public CreateYourGoogleAccountPage createAnAccountTopRight() {
        browser.click(CREATE_AN_ACCOUNT_TOP_RIGHT_BUTTON_LOCATOR);
        return new CreateYourGoogleAccountPage();
    }

    public CreateYourGoogleAccountPage createAnAccountMiddleLeft() {
        browser.click(CREATE_AN_ACCOUNT_MIDDLE_LEFT_BUTTON_LOCATOR);
        return new CreateYourGoogleAccountPage();
    }

    public ForWorkPage forWork() {
        browser.click(FOR_WORK_BUTTON_LOCATOR);
        return new ForWorkPage();
    }
}
