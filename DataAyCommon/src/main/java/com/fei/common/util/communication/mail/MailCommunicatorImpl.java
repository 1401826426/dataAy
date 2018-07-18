package com.fei.common.util.communication.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.fei.common.Response;
import com.fei.common.util.communication.AbstractCommunicator;
import com.fei.common.util.communication.Content;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailCommunicatorImpl extends AbstractCommunicator implements MailCommunicator{
	
	private String mailFrom ; 
	
	private String mailSmtpHost ; 
	
	private String mailSmtpAuth ; 
	
	private String mailSmtpSslEnable ; 
	
	private String mailFromPassord ; 
	
	private Properties properties = new Properties();
	
	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public String getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(String mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public String getMailSmtpSslEnable() {
		return mailSmtpSslEnable;
	}

	public void setMailSmtpSslEnable(String mailSmtpSslEnable) {
		this.mailSmtpSslEnable = mailSmtpSslEnable;
	}

	public String getMailFromPassord() {
		return mailFromPassord;
	}

	public void setMailFromPassord(String mailFromPassord) {
		this.mailFromPassord = mailFromPassord;
	}

	@Override
	public void ini() {
		MailSSLSocketFactory sf=null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		properties.put("mail.from",mailFrom) ; 
		properties.put("mail.smtp.host",mailSmtpHost) ;
		properties.put("mail.smtp.auth",mailSmtpAuth) ;
		properties.put("mail.smtp.ssl.enable",mailSmtpSslEnable) ;
		properties.put("mail.from.password",mailFromPassord) ;
		super.ini();
		
	}

	@Override
	public Response send(Content content) {
		boolean flag = true ; 
		for(String to:content.getTos()){
			try {
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("mail.from"),
								properties.getProperty("mail.from.password")); // 发件人邮件用户名、密码
					}
				});
				MimeMessage message = new MimeMessage(session);
				String from = properties.getProperty("mail.from" ) ; 
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(content.getTitle());
				message.setText(content.getContent());
//				Transport.send(message);
//				logger.info("发送邮件成功" + from + "  " + to) ;
//				System.out.println("发送邮件成功" + from + "  " + to) ;
				
			} catch (Exception e) {
//				logger.warn("发送邮件失败" + to , e);
				e.printStackTrace();
				flag = false ;
			}
		}
		if(flag){			
			return Response.ok(); 
		}else{
			return Response.fail("内部异常") ; 
		}
	}

}
