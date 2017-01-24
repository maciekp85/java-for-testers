package pl.jft.addressbook.tests;

import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by nishi on 2017-01-08.
 */
public class ContactDetailsTests extends TestBase {

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

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
