package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.Adresse;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.IAdresseDao;

public class AdresseDao implements IAdresseDao {

	@Override
	public void addAdresse(Adresse adresse) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(adresse);
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
	public Adresse getAdresseById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Adresse adresse = null;
		try {
			adresse = session.find(Adresse.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return adresse;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Adresse> getAdresses() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Adresse> adresses = new ArrayList<Adresse>();
		try {
			
			Query<Adresse> query = session.createQuery("FROM Adresse a", Adresse.class);
			
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
	public void updateAdresse(Adresse adresse) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(adresse);
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
	public void deleteAdresse(Adresse adresse) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(adresse);
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

}
