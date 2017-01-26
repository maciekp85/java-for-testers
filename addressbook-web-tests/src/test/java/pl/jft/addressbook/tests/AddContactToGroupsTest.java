package pl.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;
import pl.jft.addressbook.model.GroupData;
import pl.jft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by nishi on 2017-01-26.
 */
public class AddContactToGroupsTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0 || app.db().groups().size() == 0) {
      Groups groups = app.db().groups();
      if(groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test 1")
                .withHeader("header").withFooter("footer"));
      }
      if(app.db().contacts().size() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData()
                .withFirstName("Wojtek").withLastName("Barski").withCompany("Sea").withAddress("barska street\n33-444 Warsaw").inGroup(groups.iterator().next())
                .withEmail("test@email.pl").withEmail2("test2@email.pl").withEmail3("test3@email.pl")
                .withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("777888999"));
      }
    }
  }

  @Test
  public void testAddContactToGroups() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData checkedContact = before.iterator().next();
    app.contact().addToGroup(checkedContact, groups);

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(checkedContact).withAdded(checkedContact)));
  }
}
