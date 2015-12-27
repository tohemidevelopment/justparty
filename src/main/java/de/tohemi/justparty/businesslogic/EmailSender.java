package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.datainterfaces.DBUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by Heiko on 26.12.2015.
 */
public class EmailSender {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
    JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
    public void sendCreateConfirmation(DBUser sendTo, String eventname){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(sendTo.getEmail());
        simpleMessage.setText("Hallo "+sendTo.getFirstName()+"!\r\nDein Event \""+eventname+"\" wurde erfolgreich erstellt.\r\nViele Grüße\r\nDein justParty-Team");
        simpleMessage.setSubject("Dein Event wurde erfolgreich erstellt");
        simpleMessage.setFrom("noreply@justparty.ml");
        mailSender.send(simpleMessage);
    }
    public void sendEmailVerification(DBUser sendTo, String verificationID){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(sendTo.getEmail());
        simpleMessage.setText("Hallo!\r\nDein Account bei justParty wurde erfolgreich angelegt. Bitte verifiziere nun noch deine E-Mail-Adresse, indem du auf den volgenden Link klickst:\r\nhttp://justparty.ml/verifyEmail?id="+verificationID+"\r\nViele Grüße\r\nDein justParty-Team");
        simpleMessage.setSubject("E-Mail Verifizierung");
        simpleMessage.setFrom("noreply@justparty.ml");
        mailSender.send(simpleMessage);
    }
}
