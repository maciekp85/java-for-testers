package pl.jft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by nishi on 2017-01-29.
 */
public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String usernname, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
