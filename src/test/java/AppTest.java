import com.addressbook.entity.AddressBook;
import com.addressbook.entity.Contact;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.gen5.api.Disabled;
import org.junit.gen5.api.DisplayName;
import org.junit.gen5.api.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Predicate;

/**
 * Unit test for simple App
 */
@Tag("fast")
public class AppTest extends TestCase {

    final static Logger logger = LogManager.getLogger(AppTest.class);

    @Disabled
    @Test
    @DisplayName("First JPA JUnit 5 test")
    public void testApp() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("addressbook");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            AddressBook newAddressBook = new AddressBook();
            newAddressBook.setName("New Order");
            em.persist(newAddressBook);
            TypedQuery<AddressBook> query =
                    em.createNamedQuery("AddressBook.findByName", AddressBook.class);
            query.setParameter("name", "Home");
            AddressBook addressBook = query.getSingleResult();
            List<Contact> contacts = addressBook.getContacts();

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
