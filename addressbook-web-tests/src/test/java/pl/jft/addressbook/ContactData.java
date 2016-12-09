package pl.jft.addressbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String company;
  private final String address;

  public ContactData(String firstName, String lastName, String company, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
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
}
