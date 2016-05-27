package de.tohemi.justparty.test.junit.tests;

import de.tohemi.justparty.util.IDGenerator;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by Heiko on 16.12.2015.
 */
public class RandomizerTest extends TestCase {
    public void testNumber2() {

        assertTrue(50 == IDGenerator.generateID(50).length());
    }
}
