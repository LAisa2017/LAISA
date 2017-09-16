/**
 * 
 */
package www.com.screenplay.awards.email.provider;

import java.util.Properties;

/**
 * @author ssara
 *
 */
public enum EMailProviderType {
	
	GMAIL(1),
	YAHOO(2),
	HOTMAIL(3);
	
	private Properties properties;
	private final String gmailHost 		= "smtp.gmail.com";
	private final String yahooHost 		= "smtp.mail.yahoo.com";
	private final String hotmailHost 	= "smtp.live.com";
	private final String port  			= "587"; // default smtp port 
	private final String sport 			= "587"; // default socketfactory port 
	private final String gmailSSLPort   = "465";
	
	/*
Gmail SMTP server address: smtp.gmail.com
Gmail SMTP username: Your Gmail address (e.g. example@gmail.com)
Gmail SMTP password: Your Gmail password
Gmail SMTP port (TLS): 587
Gmail SMTP port (SSL): 465
Gmail SMTP TLS/SSL required: yes
TSL GMAIL settings
//				this.properties.put("mail.smtp.auth", "true");
//				this.properties.put("mail.smtp.starttls.enable", "true");
//				this.properties.put("mail.smtp.host", this.gmailHost);
//				this.properties.put("mail.smtp.port", this.port);
	 */
	
	private EMailProviderType(int tpe){
		this.properties = new Properties();
		switch(tpe){
			case 1:
				//SSL GMAIL Settings
				this.properties.put("mail.smtp.host", this.gmailHost);
				this.properties.put("mail.smtp.socketFactory.port", this.gmailSSLPort);
				this.properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				this.properties.put("mail.smtp.auth", "true");
				this.properties.put("mail.smtp.port", this.gmailSSLPort);
			break;
			case 2:
				//YAHOO setings
				 properties.put("mail.smtp.auth", "true");
				 properties.put("mail.debug", "false");
				 properties.put("mail.smtp.host", this.yahooHost);
				 properties.put("mail.smtp.port", this.port);
			break;
			case 3:
				//HOTMAIL Settings
				 properties.put("mail.smtp.auth", "true");
				 properties.put("mail.debug", "false");
				 properties.put("mail.smtp.host", this.hotmailHost);  
				 properties.put("mail.smtp.port", this.port); 
				 properties.put("mail.smtp.socketFactory.port", this.sport); 
				 properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
				 properties.put("mail.smtp.socketFactory.fallback", "false"); 
			break;
			default:
		
		}
	}
	
	public Properties getProperties(){
		return this.properties;
	}
}
