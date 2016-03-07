package com.addressbook.service;

import com.addressbook.entity.Contact;

import java.util.List;

public interface ContactsService {
    void init();
    List<Contact> findAll();
    List<Contact> findByAddressBook(String addressBookName);
    List<Contact> findByName(String lastName);
    List<Contact> deepSearch(Contact contact);
    void addNew(Contact contact);
    Contact save(Contact contact);
    void delete(Contact contact);
}
