package pl.jft.addressbook.tests;

import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().createContact(new ContactData("Maciej", "Piotrowski", "Home", "testowa street\n12-345 Cracow", "test1"));
    }
}
