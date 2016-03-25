package com.addressbook.service;

import com.addressbook.entity.AddressBook;
import com.addressbook.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ivan on 3/25/2016.
 */
public class AddressBookService {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public AddressBookService() {
        init();
    }

    public void init() {
        emf = Persistence.createEntityManagerFactory("addressbook");
        em = emf.createEntityManager();
    }

    public List<AddressBook> findAll() {
        TypedQuery<AddressBook> query =
                em.createNamedQuery("AddressBook.findAll", AddressBook.class);
        return query.getResultList();
    }
}
