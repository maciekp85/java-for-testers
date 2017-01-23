package pl.jft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName = "";

  @Expose
  @Column(name = "lastname")
  private String lastName = "";

  @Expose
  @Column(name = "company")
  private String company = "";

  @Expose
  @Column(name = "address")
  @Type( type = "text")
  private String address = "";

  @Expose
  @Column(name = "home")
  @Type( type = "text")
  private String homePhone = "";

  @Expose
  @Column(name = "mobile")
  @Type( type = "text")
  private String mobilePhone = "";

  @Expose
  @Column(name = "work")
  @Type( type = "text")
  private String workPhone = "";

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Transient
  private String group;

  @Expose
  @Column(name = "email")
  @Type( type = "text")
  private String email = "";

  @Expose
  @Column(name = "email2")
  @Type( type = "text")
  private String email2 = "";

  @Expose
  @Column(name = "email3")
  @Type( type = "text")
  private String email3 = "";

  @Transient
  private String allEmails;

  @Transient
  private String contactDetails;

  @Expose
  @Column(name = "photo")
  @Type( type = "text")
  private String pathToPhoto = "";

  public int getId() {
    return id;
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getGroup() {
    return group;
  }

  public String getContactDetails() {
    return contactDetails;
  }

  public String getPathToPhoto() {
    return pathToPhoto;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String phones) {
    this.allPhones = phones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String emails) {
    this.allEmails = emails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withContactDetails(String details) {
    this.contactDetails = details;
    return this;
  }

  public ContactData withPathToPhoto(String path) {
    this.pathToPhoto = path;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals( firstName, that.firstName ) &&
            Objects.equals( lastName, that.lastName ) &&
            Objects.equals( company, that.company ) &&
            Objects.equals( address, that.address ) &&
            Objects.equals( homePhone, that.homePhone ) &&
            Objects.equals( mobilePhone, that.mobilePhone ) &&
            Objects.equals( workPhone, that.workPhone ) &&
            Objects.equals( email, that.email ) &&
            Objects.equals( email2, that.email2 ) &&
            Objects.equals( email3, that.email3 );
  }

  @Override
  public int hashCode() {
    return Objects.hash( id, firstName, lastName, company, address, homePhone, mobilePhone, workPhone, email, email2, email3 );
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
