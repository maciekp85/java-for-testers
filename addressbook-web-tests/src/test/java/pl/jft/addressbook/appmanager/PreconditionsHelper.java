package pl.jft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.GroupData;
import pl.jft.addressbook.model.Groups;

/**
 * Created by nishi on 2017-01-29.
 */
public class PreconditionsHelper extends HelperBase {

  public PreconditionsHelper(WebDriver wd) {
    super(wd);
  }

  public void addGroupOrContactIfNotExist(ApplicationManager app) {
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
}
