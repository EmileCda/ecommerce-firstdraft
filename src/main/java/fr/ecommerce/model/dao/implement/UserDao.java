package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.User;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.IUserDao;

public class UserDao implements IUserDao {

	@Override
	public void addUser(User user) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			
		} catch (RollbackException e) {
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		 
	}
//-------------------------------------------------------------------------------------------------
	@Override
	public User getUserById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		User user = null;
		try {
			user = session.find(User.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<User> getUsers() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<User> adresses = new ArrayList<User>();
		try {
			
			Query<User> query = session.createQuery("FROM User a", User.class);
			
			adresses = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return adresses;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void updateUser(User user) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			
		} catch (RollbackException e) {
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}

//-------------------------------------------------------------------------------------------------
	@Override
	
	public void deleteUser(User user) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(user);
			tx.commit();
			
		} catch (RollbackException e) {
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
//-------------------------------------------------------------------------------------------------
	@Override
	public User getUserByEmail(String email) throws Exception {
		Session session = HibernateConnector.getSession();
		User user = null;
		try {
			Query<User> query = session.createQuery("FROM User u WHERE u.email = ?1", User.class);
			query.setParameter(1, email);
			user = query.uniqueResult();   //user = query.getSingleResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

//-------------------------------------------------------------------------------------------------

}
