package pl.jft.mantis.appmanager;

import org.openqa.selenium.By;
import pl.jft.mantis.model.UserData;

/**
 * Created by nishi on 2017-02-03.
 */
public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super( app );
  }

  public void changePasswordFor(UserData user) {
    selectUserById(user.getId());
    resetPasswordForSelectedUser();
  }

  private void resetPasswordForSelectedUser() {
    wd.findElement( By.cssSelector("input[value='Nowe hasło']")).click();
  }

  private void selectUserById(int id) {
    wd.findElement(By.cssSelector("a[href*='user_id="+ id + "']")).click();
  }
}
