package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.util.SystemProperties;
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
    private JavaMailSender mailSender;

    public EmailSender() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
        mailSender = context.getBean("mailSender", JavaMailSender.class);
        Charset encoding = StandardCharsets.UTF_8;
        byte[] encoded = new byte[0];
        try {

            encoded = Files.readAllBytes(context.getResource("email/email-template.html").getFile().toPath());
        } catch (IOException e) {
            SystemProperties.getLogger().logException(e);
        } finally {
            context.close();
        }
        htmlBasic = new String(encoded, encoding);
    }


    public void sendEmailVerification(User sendTo, String verificationID) {
        String emailContent = "Dein Account bei justParty wurde erfolgreich angelegt. Bitte verifiziere nun noch deine E-Mail-Adresse:<br>" + insertButton("http://justparty.ml/verifyEmail?id=" + verificationID, "E-Mail verifizieren");
        String subject = "E-Mail Verifizierung";
        String emailAddress = sendTo.getEmail();
        String firstName = sendTo.getFirstName();

        sendEmail(emailAddress, firstName, subject, emailContent);
    }

    public void sendCreateConfirmation(User sendTo, String eventname) {

        String emailContent = "Dein Event <b>" + eventname + "</b> wurde erfolgreich erstellt.";
        String subject = "Dein Event wurde erfolgreich erstellt";
        String emailAddress = sendTo.getEmail();
        String firstName = sendTo.getFirstName();

        sendEmail(emailAddress, firstName, subject, emailContent);
    }

    public void sendInvitationToUser(User sendTo, User inviter, Event event) {


        String firstNameInviter = (inviter.getFirstName() != null) ? inviter.getFirstName() : "einem User";
        String emailContent = "Du wurdest von " + firstNameInviter + " zu der Veranstaltung <b>" + event.getName() + "</b> eingeladen. Besuche die <a href=\"justParty.ml/event&id=" + event.getId() + "\">Veranstaltungsseite</a>";
        String address = sendTo.getEmail();
        String firstName = sendTo.getEmail();
        String subject = "Veranstaltungseinladung";

        sendEmail(address, firstName, subject, emailContent);
    }

    public void sendInvitationToNonUser(User sendTo, User inviter, Event event) {


        String emailContent = "Du wurdest von " + inviter.getEmail() + " zu der Veranstaltung <b>" + event.getName() + "</b> eingeladen.";
        String address = sendTo.getEmail();
        String firstName = null;
        String subject = "Veranstaltungseinladung";

        sendEmail(address, firstName, subject, emailContent);
    }

    private void sendEmail(String address, String firstName, String subject, String emailContent) {
        String newEmailContent = htmlFormat(emailContent, firstName);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(address);
            helper.setText(emailContent, true);
            helper.setSubject(subject);
            helper.setFrom("justParty");
        } catch (MessagingException e) {
            SystemProperties.getLogger().logException(e);
        }
        mailSender.send(message);
    }


    private String htmlFormat(String emailContent, String firstName) {
        String newFirstName = (firstName != null) ? " " + firstName : "";
        return htmlBasic.replace("_content", emailContent).replace("_firstName", newFirstName);
    }

    private String insertButton(String href, String label) {
        return "<table class=\"button facebook expand\">\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <center data-parsed=\"\"><a href=\"" + href + "\" align=\"center\" class=\"text-center\">" + label + "</a></center>\n" +
                "                    </td>\n" +
                "                    <td class=\"expander\"></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>";
    }

}
