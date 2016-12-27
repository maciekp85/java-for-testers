package pl.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before + 1);
  }

}
