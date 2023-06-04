package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.Categorie;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.ICategorieDao;
import fr.ecommerce.utils.Utils;

public class CategorieDao implements ICategorieDao {

	@Override
	public void addCategorie(Categorie categorie) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Utils.trace("CB %s \n",categorie);
			session.save(categorie);
			Utils.trace("CB %s \n",categorie);
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
	public Categorie getCategorieById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Categorie categorie = null;
		try {
			categorie = session.find(Categorie.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return categorie;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Categorie> getCategories() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Categorie> adresses = new ArrayList<Categorie>();
		try {
			
			Query<Categorie> query = session.createQuery("FROM Categorie a", Categorie.class);
			
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
	public void updateCategorie(Categorie categorie) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(categorie);
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
	
	public void deleteCategorie(Categorie categorie) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(categorie);
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
