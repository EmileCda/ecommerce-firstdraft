package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.LigneDeCommande;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.ILigneDeCommandeDao;
import fr.ecommerce.utils.Utils;

public class LigneDeCommandeDao implements ILigneDeCommandeDao {

	@Override
	public void addLigneDeCommande(LigneDeCommande ligneDeCommande) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(ligneDeCommande);
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
	public LigneDeCommande getLigneDeCommandeById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		LigneDeCommande ligneDeCommande = null;
		try {
			ligneDeCommande = session.find(LigneDeCommande.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ligneDeCommande;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<LigneDeCommande> getLigneDeCommandes() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<LigneDeCommande> adresses = new ArrayList<LigneDeCommande>();
		try {
			
			Query<LigneDeCommande> query = session.createQuery("FROM LigneDeCommande a", LigneDeCommande.class);
			
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

	public void updateLigneDeCommande(LigneDeCommande ligneDeCommande) throws Exception {

	Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(ligneDeCommande);
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
	
	public void deleteLigneDeCommande(LigneDeCommande ligneDeCommande) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(ligneDeCommande);
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
