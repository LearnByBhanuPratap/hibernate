package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SaveData {

	public static void main(String[] args) {
		// creating configuration object
		Configuration cfg = new Configuration();
		String cfglocation = "hibernate.cfg.xml";
		// populates the data of the configuration
		cfg.configure(cfglocation);
		SessionFactory factory = cfg.buildSessionFactory();
		// creating session object
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Employee e1 = new Employee();
		e1.setId(25);
		e1.setFirstName("abc17");
		e1.setLastName("xyz17");

		session.persist(e1);
		t.commit();
		session.close();

		System.out.println("successfully saved");

	}
}
