package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.IArticlePanierDao;

public class ArticlePanierDao implements IArticlePanierDao {

	@Override
	public void addArticlePanier(ArticlePanier articlePanier) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(articlePanier);
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
	public ArticlePanier getArticlePanierById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		ArticlePanier articlePanier = null;
		try {
			articlePanier = session.find(ArticlePanier.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return articlePanier;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<ArticlePanier> getArticlePaniers() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<ArticlePanier> adresses = new ArrayList<ArticlePanier>();
		try {
			
			Query<ArticlePanier> query = session.createQuery("FROM ArticlePanier a", ArticlePanier.class);
			
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
	public void updateArticlePanier(ArticlePanier articlePanier) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(articlePanier);
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
	
	public void deleteArticlePanier(ArticlePanier articlePanier) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(articlePanier);
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
