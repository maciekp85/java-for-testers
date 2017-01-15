package pl.jft.addressbook.tests;

import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        String path = "src/test/resources/photo.png";
        ContactData contact = new ContactData().
                withFirstName("Bruce").withLastName("Lee").withCompany("Bruce Lee Company").withAddress("kung fu 22").withGroup("test1").withPathToPhoto(path);
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact)));
    }

 }
