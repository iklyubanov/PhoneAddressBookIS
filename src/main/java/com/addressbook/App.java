package com.addressbook;

import com.addressbook.entity.AddressBook;
import com.addressbook.entity.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App {

    final static Logger logger = LogManager.getLogger(App.class);

    /*public static void main(String[] args) {
        App app = new App();
        app.run();
    }*/

    void run() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("addressbook");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<AddressBook> query =
                    em.createNamedQuery("AddressBook.findByName", AddressBook.class);
            AddressBook addressBook = query.getSingleResult();
            TypedQuery<Contact> contactQuery =
                    em.createNamedQuery("Contact.findByAddressBook", Contact.class);
            List<Contact> contacts = contactQuery.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if(em != null) {
                em.close();
            }
            if(emf != null) {
                emf.close();
            }
        }

    }
}
