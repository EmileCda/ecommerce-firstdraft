package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.CartePaiement;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.ICartePaiementDao;

public class CartePaiementDao implements ICartePaiementDao {

	@Override
	public void addCartePaiement(CartePaiement cartePaiement) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(cartePaiement);
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
	public CartePaiement getCartePaiementById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		CartePaiement cartePaiement = null;
		try {
			cartePaiement = session.find(CartePaiement.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return cartePaiement;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<CartePaiement> getCartePaiements() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<CartePaiement> adresses = new ArrayList<CartePaiement>();
		try {
			
			Query<CartePaiement> query = session.createQuery("FROM CartePaiement a", CartePaiement.class);
			
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
	public void updateCartePaiement(CartePaiement cartePaiement) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(cartePaiement);
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
	
	public void deleteCartePaiement(CartePaiement cartePaiement) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(cartePaiement);
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
