package pl.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;
import pl.jft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by nishi on 2017-01-08.
 */
public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Wojtek").withLastName("Barski").withCompany("Sea").withAddress("barska street\n33-444 Warsaw").inGroup(groups.iterator().next())
              .withEmail("test@email.pl").withEmail2("test2@email.pl").withEmail3("test3@email.pl")
              .withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("777888999"));
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    Contacts contact = app.db().contacts();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact.iterator().next());
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact.iterator().next());

    assertThat(cleaned(contactInfoFromDetailsForm.getContactDetails()), equalTo(mergeAll(contactInfoFromEditForm)));
  }

  public String mergeAll(ContactData contact) {
    return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getCompany(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
            contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroups().iterator().next().getName())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining(""));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "").replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "").replaceAll("Członekgrupy:", "");
  }

}
