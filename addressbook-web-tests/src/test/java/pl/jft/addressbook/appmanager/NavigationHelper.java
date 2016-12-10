package pl.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by nishi on 2016-12-10.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("grupy"));
  }

  public void goToHomePage() {
    click(By.linkText("strona główna"));
  }

  public void goToContactPage() {
    click(By.linkText("nowy wpis"));
  }
}
