package pl.jft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by nishi on 2016-02-03.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {
    if (isElementPresent(By.cssSelector("a[href='manage_overview_page.php]"))) {
      return;
    }
    click(By.linkText("Zarządzanie"));
  }

  public void manageUsersPage() {
    if (wd.findElement( By.tagName("h4")).getText().equals( "Zarządzenie kontami" )) {
      return;
    }
    managePage();
    click(By.cssSelector("a[href*='manage_user_page.php']"));
  }
}
