package com.addressbook.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address_books")
@NamedQuery(name = "AddressBook.findByName",
        query = "select ab from AddressBook ab where ab.name = :name")
public class AddressBook implements Serializable{
    @Id
    @SequenceGenerator(name="address_books_id_seq",
            sequenceName="address_books_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="address_books_id_seq")
    @Column(name = "id", updatable=false)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "addressBook")
    private List<Contact> contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
