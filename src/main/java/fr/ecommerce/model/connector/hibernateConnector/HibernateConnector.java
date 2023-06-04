package fr.ecommerce.model.connector.hibernateConnector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateConnector {

	private static HibernateConnector instance;
	private static SessionFactory sessionFactory;
	private static Session session;
	
	private HibernateConnector() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	}
	
	public static Session getSession() {
		if (instance == null) {
			instance = new HibernateConnector();
		}
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		return session;
	}
}
