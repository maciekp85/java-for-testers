package pl.jft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.jft.mantis.appmanager.HttpSession;
import pl.jft.mantis.model.MailMessage;
import pl.jft.mantis.model.UserData;
import pl.jft.mantis.model.Users;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by nishi on 2017-02-03.
 */
public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() throws IOException {
    Users users = app.db().users();
    UserData user = users.iterator().next();
    if (user.getEmail().equals( "root@localhost" )) {
      user = users.stream().skip(user.getId()).iterator().next();   // skipped administrator
    }
    String username = user.getUsername();
    String password = "password";
    String email = user.getEmail();
    app.newSession().loginUI( "administrator", "root" );
    app.goTo().manageUsersPage();
    app.mail().start();
    app.user().changePasswordFor(user);
    List<MailMessage> mailMessages = app.mail().waitForMail( 1, 10000 );
    String confirmationLink = findConfirmationLink( mailMessages, email );
    app.registration().finish(confirmationLink, password );
    HttpSession session = app.newSession();
    assertTrue(session.login(username, password));
    assertTrue(session.isLoggedInAs(username));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals( email ) ).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
