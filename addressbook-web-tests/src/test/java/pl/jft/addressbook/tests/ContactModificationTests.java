package pl.jft.addressbook.tests;

import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

/**
 * Created by nishi on 2016-12-10.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Maciej", "Piotrowski", "Home", "testowa street\n12-345 Cracow"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }
}
