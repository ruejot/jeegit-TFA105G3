package com.commonUtil;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String sender = "tfa105g3tiba@gmail.com";
	private String recipient;
	private String replySubject;
	private String replyContent;
	private String host = "smtp.gmail.com";
	// Get system properties
	private Properties properties = System.getProperties();
	
	private Session session;

	public SendMail(String recipient, String replySubject, String replyContent) {
		this.recipient = recipient;
		this.replySubject = replySubject;
		this.replyContent = replyContent;
		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
	}

	public boolean send() {
		// Used to debug SMTP issues
		this.getSession().setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(this.getSession());

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(sender));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Set Subject: header field
			message.setSubject(replySubject);

			// Now set the actual message
			message.setText(replyContent);

			// Send message
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Session getSession() {
		// Get the Session object.// and pass username and password
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, "tfa105g3");
			}
		});
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getReplySubject() {
		return replySubject;
	}

	public void setReplySubject(String replySubject) {
		this.replySubject = replySubject;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}