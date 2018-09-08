package com.gmail.qa.testcases;

import com.gmail.qa.base.Browser;
import com.gmail.qa.pages.HomePage;
import com.gmail.qa.pages.IntroductionPage;
import com.gmail.qa.pages.LogInPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class LoginTest extends AbstractTest {
    @Test
    public void positiveLoginTest() {
        IntroductionPage introductionPage = new IntroductionPage();
        introductionPage.open();
        System.out.println("Starting positive Login test");
        LogInPage logInPage = introductionPage.openSignInPage();
        HomePage homePage = logInPage.logIn(account);
        Assert.assertTrue(homePage.isUserAvatarDisplayed());
    }

    @Test
    public void negativeLoginTestWrongUserId() {
        IntroductionPage introductionPage = new IntroductionPage();
        introductionPage.open();
        System.out.println("Starting incorrect LogIn useID test");
        LogInPage logInPage = introductionPage.openSignInPage();
        HomePage homePage = logInPage.wrongUserLoginID(prop.getProperty("wrongUserID"));
        Assert.assertTrue(logInPage.isUWrongUserNameErrorMessageDisplayed());
    }

    @Test
    public void negativeLoginTestWrongPassword() {
        IntroductionPage introductionPage = new IntroductionPage();
        introductionPage.open();
        System.out.println("Starting incorrect LogIn userPassword test");
        LogInPage logInPage = introductionPage.openSignInPage();
        HomePage homePage = logInPage.wrongUserLoginPassword(prop.getProperty("username"), prop.getProperty("wrongPassword"));
        Assert.assertTrue(logInPage.isWrongPasswordErrorMessageDisplayed());
    }

    @AfterClass(description = "close browser")
    public void kill() {
        Browser.kill();
    }
}
