package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.Commande;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.ICommandeDao;

public class CommandeDao implements ICommandeDao {

	@Override
	public void addCommande(Commande commande) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(commande);
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
	public Commande getCommandeById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Commande commande = null;
		try {
			commande = session.find(Commande.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return commande;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Commande> getCommandes() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Commande> commandes = new ArrayList<Commande>();
		try {
			
			Query<Commande> query = session.createQuery("FROM Commande a", Commande.class);
			
			commandes = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return commandes;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void updateCommande(Commande commande) throws Exception {

	Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(commande);
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
	public void deleteCommande(Commande commande) throws Exception {
	
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(commande);
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
