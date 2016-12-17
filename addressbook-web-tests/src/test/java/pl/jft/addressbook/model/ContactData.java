package pl.jft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String company;
  private final String address;
  private String group;

  public ContactData(String firstName, String lastName, String company, String address, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }
}
