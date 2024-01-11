package com.openspace.Model.Config;

import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 * @author Narasimharao.a date 28-03-2013
 * 
 * 
 */
public class Mail {

	
	static ResourceBundle rb = ResourceBundle.getBundle("mail");
    private static final String SMTP_HOST_NAME = rb.getString("SMTP_HOST_NAME").trim();
    private static final String SMTP_AUTH_USER = rb.getString("SMTP_AUTH_USER").trim();
    private static final String SMTP_AUTH_PWD = rb.getString("SMTP_AUTH_PWD").trim();
    private static final String FROM_ADDRESS = rb.getString("FROM_ADDRESS").trim();
    private static final String SMTP_PORT = rb.getString("mail.smtp.port").trim();
    private static final String SMTP_SSL = rb.getString("mail.smtp.ssl").trim();
    private static final String SMTP_TLS = rb.getString("mail.smtp.tls").trim();
    private static String sub = "".trim();
    private static String MsgTxt = "".trim();


	public boolean postMail(String to, String subject, String bodymsg)
	{
		String tos[] = { to };	
	return postMail(tos,null,null,subject, bodymsg,null);
		
	}
	public boolean postMail(String to[], String cc[], String bcc[],
			String subject, String bodymsg, String[] filenames) {
		boolean status =false;
		try {

			System.out.println("$$$$$$$$$$$$ SMTP_HOST_NAME : " + SMTP_HOST_NAME+" From :"+FROM_ADDRESS +" or "+SMTP_AUTH_USER
					+ " ---to addr :: " + to[0]);
			
			boolean debug = false;
			//java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			sub = subject;
			MsgTxt = bodymsg;
			
			// Set the host smtp address
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			
			
			if(SMTP_TLS.equals("true")){
				props.put("mail.smtp.starttls.enable", "true");
			}else{
				props.put("mail.smtp.starttls.enable", "false");
			}
			
			if(SMTP_SSL.equals("true")){
				//for SSL dmain mail server like gmail,yahooo and etc..
				props.put("mail.smtp.EnableSSL.enable", "true");
				props.put("mail.smtp.starttls.required","true");
			}else{
				//for Non SSL domain mail server like openspaceibbovates.com,calibrateit.com and etc
				props.put("mail.smtp.EnableSSL.enable", "false");
				props.put("mail.smtp.starttls.required","false");
			
			}
			
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			
			
			props.setProperty("mail.smtp.port", SMTP_PORT);
			
			// props.setProperty("mail.smtp.port", "" + 587);
			//props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			//props.setProperty("mail.smtp.socketFactory.fallback", "false");
			//props.setProperty("mail.smtp.socketFactory.port",SMTP_PORT);
			
			
			
			Authenticator auth = new SMTPAuthenticator();
			System.out.println("getDefaultInstance session ---to addr :: " + to);
			Session session = Session.getInstance(props, auth);
			session.setDebug(debug);
			System.out.println("getDefaultInstance  ---to addr :: " + to);
			// create a message
			Message msg = new MimeMessage(session);
			// set the from Address
			InternetAddress addressFrom = new InternetAddress(FROM_ADDRESS,"Team HealthApplication");
			msg.setFrom(addressFrom);

			// set the To Address
			// System.out.println("$$$$$$$$$$$$ SMTP_HOST_NAME"+SMTP_HOST_NAME+" ---to addr"+to);
			if (to != null && to.length !=0) {
				InternetAddress[] addressTo = new InternetAddress[to.length];
				for (int i = 0; i < to.length; i++) {
					addressTo[i] = new InternetAddress(to[i]);
				}
				msg.setRecipients(Message.RecipientType.TO, addressTo);
			}
			// set the CC Address
			if (cc != null && cc.length !=0) {
				InternetAddress[] addressCC = new InternetAddress[cc.length];
				for (int i = 0; i < cc.length; i++) {
					addressCC[i] = new InternetAddress(cc[i]);
				}
				msg.setRecipients(Message.RecipientType.CC, addressCC);
			}
			// set the BCC Address
			if (bcc != null && bcc.length !=0) {
				InternetAddress[] addressBCC = new InternetAddress[bcc.length];
				for (int i = 0; i < bcc.length; i++) {
					addressBCC[i] = new InternetAddress(bcc[i]);
				}
				msg.setRecipients(Message.RecipientType.BCC, addressBCC);
			}
			// Setting the Subject and Content Type
			msg.setSubject(sub);
			if (filenames == null)
				msg.setContent(getMultipart());
			else {
				File[] attachments = new File[filenames.length];
				for (int i = 0; i < filenames.length; i++)
					attachments[i] = new File(filenames[i]);
				msg.setContent(createAttachment(attachments));

			}
			Transport.send(msg,msg.getAllRecipients());
			status= true;
		} catch (Exception e) {
			e.printStackTrace();
			
			status = false;
		}finally{
			
		}
		
		return status;
	}

	
	
	
	
	
	private static Multipart getMultipart() throws MessagingException {

		Multipart multi = new MimeMultipart("mixed");
		multi.addBodyPart(createContent());
		return multi;

	}

	private static BodyPart createHtmlBody() throws MessagingException {
		BodyPart html = new MimeBodyPart();
		html.setContent(MsgTxt, "text/html;charset=gbk");
		return html;
	}

	private static BodyPart createContent() throws MessagingException {
		BodyPart content = new MimeBodyPart();
		Multipart relate = new MimeMultipart("related");
		relate.addBodyPart(createHtmlBody());

		content.setContent(relate);
		return content;
	}

	private static Multipart createAttachment(File[] files)
			throws MessagingException {

		Multipart multi = new MimeMultipart("mixed");

		for (int i = 0; i < files.length; i++) {
			BodyPart attach = new MimeBodyPart();
			DataSource ds = new FileDataSource(files[i]);
			attach.setDataHandler(new DataHandler(ds));
			attach.setFileName(ds.getName());
			multi.addBodyPart(attach);

		}
		multi.addBodyPart(createContent());
		return multi;
	}

	boolean sendMail(String from, String to, String string, String string0,
			String message, String vippdffilespath, String attachfilename,
			String string1, String subject) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
