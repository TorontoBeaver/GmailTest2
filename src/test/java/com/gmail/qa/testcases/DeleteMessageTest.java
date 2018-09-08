package com.gmail.qa.testcases;

import com.gmail.qa.base.Browser;
import com.gmail.qa.bo.Letter;
import com.gmail.qa.pages.HomePage;
import com.gmail.qa.pages.LogInPage;
import org.apache.commons.lang3.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Test
public class DeleteMessageTest extends AbstractTest {

	public void deleteEmailMessage() {

		Letter letter = new Letter(prop.getProperty("subject") + RandomStringUtils.randomNumeric(5), prop.getProperty("email"), prop.getProperty("text"));
		HomePage homePage = new LogInPage().open().logIn(account).clickComposeButton().sendMessage(letter);
		homePage.deleteMyMessage(letter);
		homePage.clickMoreButton();
		homePage.clickTrashButton();
		Assert.assertTrue(homePage.isLetterPresent(letter));
	}
	@AfterClass(description = "close browser")
	public void kill() {
		Browser.kill();
	}

}


