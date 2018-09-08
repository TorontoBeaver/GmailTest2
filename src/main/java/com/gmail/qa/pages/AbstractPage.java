package com.gmail.qa.pages;

import com.gmail.qa.base.Browser;


public class AbstractPage {

	protected Browser browser;

	public AbstractPage() {
		this.browser = Browser.getInstance();
	}

}