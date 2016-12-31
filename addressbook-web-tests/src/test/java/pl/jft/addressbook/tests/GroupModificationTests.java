package pl.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.GroupData;

/**
 * Created by nishi on 2016-12-10.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before);
  }

}
