package pl.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;
import pl.jft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by nishi on 2016-12-10.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Wojtek").withLastName("Barski").withCompany("Sea").withAddress("barska street\n33-444 Warsaw").inGroup(groups.iterator().next().withName("test1")));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contacts = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Jan").withLastName("Kowalski").withCompany("Home").withAddress("kowalska street\r\n12-345 Cracow");
    app.goTo().homePage();
    app.contact().modify(contacts);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contacts)));
  }

}
