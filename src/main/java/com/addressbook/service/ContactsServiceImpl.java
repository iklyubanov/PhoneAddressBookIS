package com.addressbook.service;

import com.addressbook.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class ContactsServiceImpl implements ContactsService {

    EntityManagerFactory emf = null;
    EntityManager em = null;

    public ContactsServiceImpl() {
        init();
    }

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("addressbook");
        em = emf.createEntityManager();
    }

    @Override
    public List<Contact> findAll() {
        TypedQuery<Contact> query =
                em.createNamedQuery("Contact.findAll", Contact.class);
        return query.getResultList();
    }

    @Override
    public List<Contact> findByAddressBook(String addressBookName) {
        TypedQuery<Contact> query =
                em.createNamedQuery("Contact.findByAddressBook", Contact.class);
        query.setParameter("addressBookName", addressBookName);
        return query.getResultList();
    }

    @Override
    public List<Contact> findByName(String lastName) {
        TypedQuery<Contact> query =
                em.createNamedQuery("Contact.findByName", Contact.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<Contact> deepSearch(Contact contact) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
        Root<Contact> root = query.from(Contact.class);

        Predicate predicate = buildPredicate(contact, cb, root);

        query.select(root);
        if(predicate != null) {
            query.where(predicate);
        }
        return em.createQuery(query).getResultList();
    }

    private Predicate buildPredicate(Contact contact, CriteriaBuilder cb, Root<Contact> root) {
        Predicate predicate = checkParameter(contact.getFirstName(), "firstName", null, root, cb);
        predicate = checkParameter(contact.getLastName(), "lastName", predicate, root, cb);
        predicate = checkParameter(contact.getAddress1(), "address1", predicate, root, cb);
        predicate = checkParameter(contact.getAddress2(), "address2", predicate, root, cb);
        predicate = checkParameter(contact.getTown(), "town", predicate, root, cb);
        predicate = checkParameter(contact.getCounty(), "county", predicate, root, cb);
        predicate = checkParameter(contact.getCountry(), "country", predicate, root, cb);
        predicate = checkParameter(contact.getPostCode(), "postCode", predicate, root, cb);
        predicate = checkParameter(contact.getHomeTelephone(), "homeTelephone", predicate, root, cb);
        predicate = checkParameter(contact.getWorkTelephone(), "workTelephone", predicate, root, cb);
        predicate = checkParameter(contact.getMobileTelephone(), "mobileTelephone", predicate, root, cb);
        predicate = checkParameter(contact.getFax(), "fax", predicate, root, cb);
        predicate = checkParameter(contact.getEmail(), "email", predicate, root, cb);
        return predicate;
    }

    private Predicate checkParameter(final Object parameter, final String paramName, Predicate basePredicate, final Root<Contact> root, final CriteriaBuilder cb) {
        //если параметр не null и если тип параметра String - строка не пустая
        if(parameter != null && !(parameter instanceof String && ((String)parameter).isEmpty())) {
            Predicate nextPredicate = cb.equal(root.get(paramName), parameter);
            basePredicate = preparePredicate(basePredicate, nextPredicate, cb);
        }
        return basePredicate;
    }

    private Predicate preparePredicate(Predicate basePredicate, final Predicate nextPredicate, final CriteriaBuilder cb) {
        if(basePredicate == null) {
            basePredicate = nextPredicate;
        } else {
            basePredicate = cb.and(basePredicate, nextPredicate);
        }
        return basePredicate;
    }

    @Override
    public void addNew(Contact contact) throws Exception{
        em.getTransaction().begin();
        em.persist(contact);
        em.getTransaction().commit();
    }

    @Override
    public Contact save(Contact contact) {
        em.getTransaction().begin();
        em.merge(contact);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Contact contact) {
        em.getTransaction().begin();
        em.remove(contact);
        em.getTransaction().commit();
    }
}
