package com.addressbook.service;

import com.addressbook.entity.Contact;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ContactsServiceImplTest {

    @Test
    public void testDeepSearch() {
        ContactsService contactsService = new ContactsServiceImpl();
        Contact contact =  new Contact();
        contact.setFirstName("David");
        contact.setLastName("Ford");
        List<Contact> findedContacts = contactsService.deepSearch(contact);
        Assert.assertTrue(!findedContacts.isEmpty());
    }
}
