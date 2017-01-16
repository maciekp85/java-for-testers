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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    attach(By.name("photo"), contactData.getPathToPhoto());

    if (creation) {
      if (wd.findElement(By.name("new_group")).getText().contains("test1"))
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
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

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
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
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row: rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      ContactData contactData = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
      contacts.add(contactData);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withCompany(company).withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withGroup(contact.getGroup());
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactDetailsById(contact.getId());
    String contactDetails = wd.findElement(By.id("content")).getText();
    String group = "";
    String [] elements = contactDetails.split("\n");
    for (int i = 0; i < elements.length; i++) {
      if (elements[i].contains("Członek grupy:")) {
        group = elements[i].substring(14);
      }

    }
    wd.navigate().back();
    contact.withGroup(group);
    return new ContactData().withId(contact.getId()).withGroup(group).withContactDetails(contactDetails);
  }

  private void initContactDetailsById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }
}
