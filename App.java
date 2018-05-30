package com.mycompany.ThirdApp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*
 3. Написать простой REST web-service который бы принимал на вход строку (к примеру, имя + 
 фамилия) и отсылал простенькое письмо (произвольный текст, в начале которого бы была фраза типа
“Приветствую вас, дорогой <Имя Фамилия, которые поступили на сервис>
…….
С уважением, бот."

на фиксированный адрес (который опять же, прописывается в проперти файле)

*/
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.sendMsg("Артюшенко", "Дмитрий");
       
    }
    public void sendMsg(String surname, String name) {
        final Properties prop = new Properties();

        try {

        	FileReader reader=new FileReader("prop.properties");
        	prop.load(reader);

            Session mailSession = Session.getDefaultInstance(prop);
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(prop.getProperty("mail.smtps.user")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(prop.getProperty("mail.recipient")));
            message.setSubject("subject");
            message.setText("Приветствую вас, дорогой " + name + " " + surname+ "\t\n.........\t\nС уважениенм, бот.");
            

            Transport transport = mailSession.getTransport();
            transport.connect(null, "asdf123)");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
}   
}
