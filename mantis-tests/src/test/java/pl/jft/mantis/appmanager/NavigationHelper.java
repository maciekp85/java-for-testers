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

  public void groupPage() {
    if (isElementPresent(By.cssSelector("div[id='content'] > h1"))
            && wd.findElement(By.cssSelector("div[id='content'] > h1")).getText().equals("Grupy")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("grupy"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("strona główna"));
  }

  public void contactPage() {
    if (isElementPresent(By.cssSelector("div[id='content'] > h1"))
            && wd.findElement(By.cssSelector("div[id='content'] > h1")).getText().equals("Edytuj / dodaj wpis do książki adresowej")
            && isElementPresent(By.name("firstname"))) {
      return;
    }
    click(By.linkText("nowy wpis"));
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
