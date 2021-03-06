package pl.jft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.jft.addressbook.model.ContactData;
import pl.jft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());  // List<ContactData>.class
            return contacts.stream().map((g) -> new Object[] {g} ).collect(Collectors.toList()).iterator();
        }
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        app.contact().create(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() + 1);

        List<Integer> ids = new ArrayList<>();
        for (ContactData c: after) {
            ids.add(c.getId());
        }
        Comparator<? super Integer> byId = Comparator.comparingInt( Integer::intValue );
        ids.sort(byId);
        contact.withId(ids.get(after.size() - 1));
        assertThat(after, equalTo(before.withAdded(contact)));
    }

 }
