package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {
	
	public static void main(String[] args) {

		// creating configuration object
		Configuration cfg = new Configuration();
		String cfglocation = "hibernate.cfg.xml";
		// populates the data of the configuration
		// file
		cfg.configure(cfglocation);

		// creating session factory object
		//The main contract here is the creation of Session instances. 
		//Usually an application has a single SessionFactory instance and threads servicing client requests obtain Session instances from this factory. 

		//The internal state of a SessionFactory is immutable. 
		//Once it is created this internal state is set. This internal state includes all of the metadata about Object/Relational Mapping. 

		//Implementors must be threadsafe.

		SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		//The main runtime interface between a Java application and Hibernate.
		//This is the central API class abstracting the notion of a persistence service.

		//The lifecycle of a Session is bounded by the beginning and end of a logical 
		//transaction. (Long transactions might span several database transactions.)

		//The main function of the Session is to offer create, read and delete operations for instances of mapped entity classes. Instances may exist in one of three states:
		Session session = factory.openSession();

		// creating transaction object
		//A transaction is associated with a Session and is usually initiated by a call to org.hibernate.Session.beginTransaction().
		//A single session might span multiple transactions since the notion of a session (a conversation between the application and the datastore)
		//is of coarser granularity than the notion of a transaction. However, it is intended that there be at most one uncommitted transaction associated with a particular Session at any time. 
		Transaction t = session.beginTransaction();

		Employee e1 = new Employee();
		e1.setId(29);
		e1.setFirstName("abc13");
		e1.setLastName("xyz13");

		session.persist(e1);// persisting the object
		t.commit();// transaction is committed
		
//		Transaction t1 = session.beginTransaction();
//		Employee e2 = new Employee();
//		e2.setId(10);
//		e2.setFirstName("ppp10");
//		e2.setLastName("lll10");
//		session.persist(e2);
//		t1.commit();
		
		session.close();

		System.out.println("successfully saved");

	}
}
