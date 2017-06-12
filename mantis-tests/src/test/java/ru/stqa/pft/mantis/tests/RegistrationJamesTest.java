package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Alesya on 06/01/2017.
 */
public class RegistrationJamesTest extends TestBase {


  @Test
  public void testRegistrationJames() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String email = String.format("user%s@localhost.localhostdomain", now);
    String password = "password";
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }
}


