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
public class ContactEmailTests extends TestBase {

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  public String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s -> ! s.equals("")))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }


}
