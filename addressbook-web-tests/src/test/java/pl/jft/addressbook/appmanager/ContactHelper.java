package pl.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by nishi on 2016-12-10.
 */
public class ContactHelper extends HelperBase {

  private NavigationHelper navigationHelper;

  public ContactHelper(WebDriver wd) {
    super(wd);
    navigationHelper = new NavigationHelper(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());

    if (creation) {
      if (wd.findElement(By.name("new_group")).getText().contains("test1"))
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
    navigationHelper.homePage();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contact) {
    navigationHelper.contactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    submitContactDeletion();
  }

  public void modify(ContactData contacts) {
    initContactModification();
    fillContactForm(contacts, false);
    submitContactModification();
    returnToHomePage();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.tagName("tr"));
    for (int i = 1; i < elements.size(); i++) {
      int id = Integer.parseInt(wd.findElements(By.xpath("//tr")).get(i).findElement(By.tagName("input")).getAttribute("id"));
      String firstName = wd.findElements(By.tagName("tr")).get(i).findElement(By.xpath("td[3]")).getText();
      String lastName = wd.findElements(By.tagName("tr")).get(i).findElement(By.xpath("td[2]")).getText();
      ContactData contactData = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contactData);
    }
    return contacts;
  }
}
