package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Alesya on 05/29/2017.
 */
public class PasswordChangeTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String password = String.format("pass%s", now);
    Users allUsers = app.db().users();
    UserData userToChange = allUsers.iterator().next();
    UserData user = new UserData().withId(userToChange.getId()).
            withUsername(userToChange.getUsername()).withEmail(userToChange.getEmail());
    app.manage().logInAdmin("administrator", "root");
    app.manage().changePasswordStart(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
    app.manage().changePasswordFinish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
