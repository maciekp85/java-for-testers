package pl.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.GroupData;
import pl.jft.addressbook.model.Groups;

/**
 * Created by nishi on 2017-01-26.
 */
public class AddContactToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0 || app.db().groups().size() == 0) {
      Groups groups = app.db().groups();
      if(groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test 1")
                .withHeader("header").withFooter("footer"));
        groups = app.db().groups();
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
  public void testAddContactToGroup() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    ContactData checkedContact = app.db().contacts().iterator().next();
    Groups groupsBefore = checkedContact.getGroups();
    boolean isContactAddedToGroup = app.contact().addToGroup( checkedContact, groups );
    while (!isContactAddedToGroup) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new Group")
              .withHeader("header").withFooter("footer"));
      groups = app.db().groups();
      app.goTo().homePage();
      isContactAddedToGroup = app.contact().addToGroup(checkedContact, groups);
    }

    Groups groupsAfter = checkedContact.getGroups();
    Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
  }
}
