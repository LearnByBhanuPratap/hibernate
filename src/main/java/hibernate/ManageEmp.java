package hibernate;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class ManageEmp {

	private static SessionFactory factory;

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer employeeID = null;
		try {
			transaction = session.beginTransaction();
			Emp employee = new Emp(fname, lname, salary);
			employeeID = (Integer) session.save(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Emp").list();
			Iterator<?> iterator = employees.iterator();
			while ( iterator.hasNext()) {
				Emp employee = (Emp) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print("  Last Name: " + employee.getLastName());
				System.out.println("  Salary: " + employee.getSalary());
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, int salary) {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Emp employee = (Emp) session.get(Emp.class, EmployeeID);
			employee.setSalary(salary);
			session.update(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Emp employee = (Emp) session.get(Emp.class, EmployeeID);
			session.delete(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		ManageEmp ME = new ManageEmp();
		System.out.println("addEmployee");
		//Add few employee records in database
		Integer empID1 = ME.addEmployee("Bhanu", "Ptatap", 1000);
		Integer empID2 = ME.addEmployee("Bhanu1", "Ptatap1", 50);
		Integer empID3 = ME.addEmployee("Bhanu2", "Ptatap2", 10000);
		
		System.out.println("ME.listEmployees()");
		
		//List down all the employees
		ME.listEmployees();
		System.out.println("-updateEmployee-------");
	
		//Update employee's records
		ME.updateEmployee(empID1, 5);

		System.out.println("-deleteEmployee-------");
		//Delete an employee from the database
		ME.deleteEmployee(empID2);

		System.out.println("-listEmployees-------");
		//List down new list of the employees
		ME.listEmployees();
	}

}