package pl.jft.addressbook.tests;

import org.testng.Assert;
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
public class DeleteContactFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.pre().addGroupOrContactIfNotExist(app);
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    ContactData checkedContact = app.db().contacts().iterator().next();
    Groups groupsForContact = checkedContact.getGroups();
    GroupData groupToAdd, groupToDelete;
    if (groupsForContact.size() == 0) {
      groupToAdd  = groups.iterator().next();
      app.contact().addToGroup( checkedContact, groupToAdd, groups );
      groupToDelete = groupToAdd;
    } else {
      groupToDelete = groupsForContact.iterator().next();
    }
    app.goTo().homePage();
    app.contact().deleteFromGroup( checkedContact, groupToDelete, groups );
    app.goTo().homePage();
    ContactData contactAfterDeletedGroup = app.db().contacts().iterator().next();
    Assert.assertFalse( contactAfterDeletedGroup.getGroups().contains(groupToDelete));
    assertThat(checkedContact.getGroups(), equalTo(contactAfterDeletedGroup.getGroups().withAdded(groupToDelete)));
  }
}
