package pl.jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.jft.addressbook.model.ContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishi on 2017-01-15.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
     if (format.equals("xml")) {
      saveAsXml(contacts, file);
    } else if (format.equals("json")) {
      saveAsJson(contacts, file);
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, String file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ContactData> contacts, String file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName("First" + i).withLastName("Last" + i).withCompany("Company" + i)
        .withAddress("Address" + i).withHomePhone(String.format("%s%s%s-%s%s%s-%s%s%s", i,i,i, i,i,i, i,i,i)).withMobilePhone(String.format("%s%s%s-%s%s%s-%s%s%s", i,i,i, i,i,i, i,i,i))
        .withWorkPhone(String.format("%s%s%s-%s%s%s-%s%s%s", i,i,i, i,i,i, i,i,i)).withGroup("Group " + i).withEmail("test" + i + "@email.pl")
        .withEmail2("test" + i + "@email2.pl").withEmail3("test" + i + "@email1.pl").withPathToPhoto("src/test/resources/test" + i + ".png"));
    }
    return contacts;
  }
}
