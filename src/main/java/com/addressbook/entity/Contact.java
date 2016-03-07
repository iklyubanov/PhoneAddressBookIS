package com.addressbook.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@NamedQueries({@NamedQuery(name = "Contact.findAll",
        query = "select c from Contact c"),
        @NamedQuery(name = "Contact.findByAddressBook",
                query = "select c from Contact c where c.addressBook.name = :addressBookName"),
        @NamedQuery(name = "Contact.findByName",
                query = "select c from Contact c where c.lastName = :lastName")})
public class Contact {
    @Id
    @SequenceGenerator(name="contacts_id_seq",
            sequenceName="contacts_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="contacts_id_seq")
    @Column(name = "id", updatable=false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "address_book_id", nullable = false)
    private AddressBook addressBook;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address_1")
    private String address1;
    @Column(name = "address_2")
    private String address2;
    private String town;
    /**Округ*/
    private String county;
    /**Страна*/
    private String country;
    @Column(name = "post_code", length = 10)
    private String postCode;
    @Column(name = "home_tel", length = 100)
    private String homeTelephone;
    @Column(name = "work_tel", length = 100)
    private String workTelephone;
    @Column(name = "mobile_tel", length = 100)
    private String mobileTelephone;
    @Column(length = 100)
    private String fax;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public String getAddressBookName() {
        return addressBook != null ? addressBook.getName() : "";
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
