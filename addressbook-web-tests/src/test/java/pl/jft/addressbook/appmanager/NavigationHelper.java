package pl.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by nishi on 2016-12-10.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.cssSelector("div[id='content'] > h1"))
            && wd.findElement(By.cssSelector("div[id='content'] > h1")).getText().equals("Grupy")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("grupy"));
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("strona główna"));
  }

  public void goToContactPage() {
    if (isElementPresent(By.cssSelector("div[id='content'] > h1"))
            && wd.findElement(By.cssSelector("div[id='content'] > h1")).getText().equals("Edytuj / dodaj wpis do książki adresowej")
            && isElementPresent(By.name("firstname"))) {
      return;
    }
    click(By.linkText("nowy wpis"));
  }
}
