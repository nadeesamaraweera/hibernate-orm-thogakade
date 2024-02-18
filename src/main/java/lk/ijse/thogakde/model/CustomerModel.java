//package lk.ijse.thogakde.model;
//
//import lk.ijse.thogakde.config.SessionFactoryConfig;
//import lk.ijse.thogakde.entity.Customer;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class CustomerModel {
//    public boolean saveCustomer(Customer testCustomer) {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(testCustomer);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//
//    public Customer searchCustomer(int customerId) {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//         Customer customer= session.get(Customer.class, customerId);
//        if (customer== null) {
//            return null;
//        }
//        transaction.commit();
//        Hibernate.initialize(customer.getNumbers()); // Initialize the numbers collection
//        session.close();
//        return customer;
//    }
//
//
//    public boolean updateCustomer(int customerId, Customer customer) {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Customer existingCustomer = session.get(Customer.class, customerId);
//        existingCustomer.setName(customer.getName());
//        existingCustomer.setBirthdate(customer.getBirthdate());
//        existingCustomer.setNumbers(customer.getNumbers());
//        session.update(existingCustomer);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    public boolean deleteCustomer(int customerId) {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Customer customer = session.get(Customer.class, customerId);
//        session.delete(customer);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//
//}
//
