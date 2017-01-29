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
    app.pre().addGroupOrContactIfNotExist(app);
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    GroupData groupToAdd = groups.iterator().next();
    ContactData checkedContact = app.db().contacts().iterator().next();
    Groups groupsBefore = checkedContact.getGroups();
    boolean isContactAddedToGroup = app.contact().addToGroup( checkedContact, groupToAdd, groups );
    while (!isContactAddedToGroup) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new Group")
              .withHeader("header").withFooter("footer"));
      groups = app.db().groups();
      app.goTo().homePage();
      isContactAddedToGroup = app.contact().addToGroup(checkedContact, groupToAdd, groups);
    }

    Groups groupsAfter = checkedContact.getGroups();
    Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
  }
}
