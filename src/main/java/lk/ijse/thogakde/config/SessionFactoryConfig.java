package lk.ijse.thogakde.config;

import lk.ijse.thogakde.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factaryConfig;
    private SessionFactoryConfig(){}
    public static SessionFactoryConfig getFactoryConfig(){
        return (null == factaryConfig)?factaryConfig = new SessionFactoryConfig() : factaryConfig;
    }
    public Session getSession(){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure() .build();
        Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(Customer.class).getMetadataBuilder().build();
        SessionFactory sessionFactory= metadata.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
