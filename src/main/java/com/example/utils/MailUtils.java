package com.example.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils implements Runnable {
	private String email;
	private String code; // 激活码

	public MailUtils(String email, String code) {
		this.email = email;
		this.code = code;
	}

	public void run() {
		Properties prop=new Properties();
		prop.put("mail.host","smtp.163.com" );
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", true);

		try {
			// SSL加密
			MailSSLSocketFactory sf = null;
			sf = new MailSSLSocketFactory();
			// 设置信任所有的主机
			sf.setTrustAllHosts(true);
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);

			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("m18819253626@163.com", "123456abc");
				}
			});
			session.setDebug(true);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("m18819253626@163.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("激活邮件");
			String content = "<html><head></head><body><h1>请点击连接激活</h1><h3><a href='http://localhost:8080/active?code="
					+ code + "'>http://localhost:8080/active?code=" + code + "</href></h3></body></html>";
			message.setContent(content, "text/html;charset=UTF-8");
			// Transport.send(message);
			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}