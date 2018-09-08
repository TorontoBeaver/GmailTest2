package com.gmail.qa.bo;

public class Letter {
	private String subject;
	private String recipient;
	private String content;

	public Letter(String subject, String recipient, String content) {
		this.subject = subject;
		this.recipient = recipient;
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getContent() {
		return content;
	}
}

