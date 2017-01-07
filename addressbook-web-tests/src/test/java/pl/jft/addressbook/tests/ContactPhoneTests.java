package pl.jft.addressbook.tests;

import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

/**
 * Created by nishi on 2017-01-07.
 */
public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
