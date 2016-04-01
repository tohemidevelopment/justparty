package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.datainterfaces.DBUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Heiko on 26.12.2015.
 */
public class EmailSender {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
    JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);

    public void sendEmailVerification(DBUser sendTo, String verificationID){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(sendTo.getEmail());
        simpleMessage.setText("Hallo!\r\nDein Account bei justParty wurde erfolgreich angelegt. Bitte verifiziere nun noch deine E-Mail-Adresse, indem du auf den folgenden Link klickst:\r\nhttp://justparty.ml/verifyEmail?id="+verificationID+"\r\nViele Grüße\r\nDein justParty-Team");
        simpleMessage.setSubject("E-Mail Verifizierung");
        simpleMessage.setFrom("noreply@justparty.ml");
        mailSender.send(simpleMessage);
    }

    public void sendCreateConfirmation(DBUser sendTo, String eventname){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);

        String emailContent="Dein Event <b>"+eventname+"</b> wurde erfolgreich erstellt.";
        emailContent=htmlFormat(emailContent,sendTo.getFirstName());
        try {
            helper.setTo(sendTo.getEmail());
            helper.setText(emailContent,true);
            helper.setSubject("Dein Event wurde erfolgreich erstellt");
            helper.setFrom("justParty");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    private String htmlFormat(String emailContent, String firstName) {
        firstName=(firstName!=null)?" "+firstName:"";
        return htmlBasic.replace("_content",emailContent).replace("_firstName",firstName);

    }

    private String htmlBasic="<html><body><h1>Hallo_firstName!</h1><br>_content<br><br>Viele Grüße<br>Dein justParty-Team</body></html>";
}
