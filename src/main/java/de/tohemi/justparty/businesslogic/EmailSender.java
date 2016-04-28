package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.datainterfaces.DBUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Created by Heiko on 26.12.2015.
 */
public class EmailSender {
    private String htmlBasic;
    public EmailSender(){
        Charset encoding= StandardCharsets.UTF_8;
        byte[] encoded = new byte[0];
        try {

            encoded = Files.readAllBytes(context.getResource("email/email-template.html").getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        htmlBasic= new String(encoded,encoding);
    }
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
    JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);

    public void sendEmailVerification(DBUser sendTo, String verificationID){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);

        String emailContent="Dein Account bei justParty wurde erfolgreich angelegt. Bitte verifiziere nun noch deine E-Mail-Adresse:<br>"+insertButton("http://justparty.ml/verifyEmail?id="+verificationID,"E-Mail verifizieren");
        emailContent=htmlFormat(emailContent,sendTo.getFirstName());
        try {
            helper.setTo(sendTo.getEmail());
            helper.setText(emailContent,true);
            helper.setSubject("E-Mail Verifizierung");
            helper.setFrom("justParty");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
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
    private String insertButton(String href, String label){
        return "<table class=\"button facebook expand\">\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <center data-parsed=\"\"><a href=\""+href+"\" align=\"center\" class=\"text-center\">"+label+"</a></center>\n" +
                "                    </td>\n" +
                "                    <td class=\"expander\"></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>";
    }

}
