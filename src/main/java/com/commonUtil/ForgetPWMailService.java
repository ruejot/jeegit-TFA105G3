package com.commonUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

//以下使用JavaMail API來建立EMAIL(建立含有HTML標籤的郵件)
//邏輯：
//step1、從Session獲得實現了某種郵件發送協議的Transport對象
//step2、使用Session建立Message對象，並調用Message的方法封裝email的數據；
//step3、連接指定的SMTP服務器(需取得該mail server的SMTP授權碼)，調用Transport中的郵件發送方法Message中封裝的郵件數據。

//MimeMessage類表示整封郵件、MimeBodyPart類表示郵件的一個MIME訊息、MimeMultipart類表示一個由多個MIME訊息組合成的組合MIME訊息

public class ForgetPWMailService {
	private final static String HOST = "smtp.gmail.com"; 			//寄件人的email 的SMTP伺服器地址
	private final static String AUTH = "true";
	private final static String PORT = "465";
	private final static String STARTTLE_ENABLE = "true";
	private final static String SENDER = "tfa105g3tiba@gmail.com";	//寄件人的email address
	private final static String PASSWORD = "tfa105g3";				//寄件人的email 的password

	// 設定傳送email:至收信人的Email信箱、Email主旨、Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail 的smtp Server
			Properties props = new Properties();
//			(Properties類：該類表示了一個持久的屬性集，用於存放相關鍵值對資訊作為引數來建立Session物件，這裡構造了一個空的集合作為引數)
			props.put("mail.smtp.host", HOST);
			props.put("mail.smtp.auth", AUTH);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.starttls.enable", STARTTLE_ENABLE);//設定starttls加密(可以支援SSL/TLS二種加密)
			props.put("mail.smtp.ssl.trust", HOST);					//設定信任所有的主機
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			// 設定 gmail 的帳號&密碼 (藉設定的Gmail來傳送Email)
			// 須將myGmail的"安全性較低的應用程式存取權"打開才不會被擋信

			// 建立一個驗證器物件
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SENDER, PASSWORD);
				}
			};

			// 建立Session例項物件
			Session session = Session.getDefaultInstance(props, authenticator);
//			(Session類：該物件用於收集客戶端與郵件伺服器之間的網路連線資訊和定義整個郵件程式所需的環境資訊，
//			這些資訊作為Session物件的屬性儲存在Session物件中，
//			Session物件利用了java.util.Properties物件獲得了郵件伺服器、使用者名稱、密碼資訊和整個應用程式都要使用到的共享資訊，
//			由於Session類的構造方法是私有的，所以我們使用Session類提供的getDefaultInstance()這個靜態工廠方法獲得一個預設的Session物件)

			// 建立Message例項物件
			Message message = new MimeMessage(session);

			// 並設定各種郵件的欄位：寄件日期、型別、寄件人、收件人、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(MimeUtility.encodeText(subject, StandardCharsets.UTF_8.toString(), "B"));

			// 建立一個表示HTML正文的MimeBodyPart物件， 並將它加入到前面建立的MimeMultipart物件中
//			(MimeBodyPart、MimeMultipart才可以在mail內放圖，否則僅能純文字)	
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setContent(messageText, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);

			// 將MimeMultipart物件設定為整個郵件的內容，並注意呼叫saveChanges方法進行更新
			message.setContent(multipart);

			//獲取Transport的對象(傳送給誰)
			Transport transport = session.getTransport("smtp");
			try {
				//取的tfa105g3tiba@gmail.com要發送mail時，gmail的smtp授權碼
				transport.connect();
				//發送email給新增新建時，添加的所有收件人
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}
			System.out.println("傳送成功!");
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

}
