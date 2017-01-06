package pl.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by nishi on 2016-12-10.
 */
public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Maciej", "Piotrowski", "Home", "testowa street\n12-345 Cracow", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification();
    ContactData contacts = new ContactData(before.get(0).getId(), "Jan", "Kowalski", "Home", "testowa street\n12-345 Cracow", null);
    app.getContactHelper().fillContactForm(contacts, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    before.remove(0);
    before.add(contacts);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
