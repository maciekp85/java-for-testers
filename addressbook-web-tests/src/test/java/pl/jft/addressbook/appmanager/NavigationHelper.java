package pl.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by nishi on 2016-12-10.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd =wd;
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("grupy")).click();
  }

  public void goToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void goToContactPage() {
    wd.findElement(By.linkText("nowy wpis")).click();
  }
}
