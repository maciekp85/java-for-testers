package pl.jft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by nishi on 2016-12-10.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactDeletion();
  }
}
