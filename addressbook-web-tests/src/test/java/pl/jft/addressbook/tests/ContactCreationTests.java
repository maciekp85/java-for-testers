package pl.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() {
        app.goTo().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().goToContactPage();
        ContactData contact = new ContactData("Bruce", "Lee", "Bruce Lee Company", "kung fu 22", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
