package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.Commentaire;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.ICommentaireDao;
import fr.ecommerce.utils.Utils;

public class CommentaireDao implements ICommentaireDao {

	@Override
	public void addCommentaire(Commentaire commentaire) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(commentaire);
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
	public Commentaire getCommentaireById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Commentaire commentaire = null;
		try {
			commentaire = session.find(Commentaire.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return commentaire;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Commentaire> getCommentaires() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		try {
			
			Query<Commentaire> query = session.createQuery("FROM Commentaire a", Commentaire.class);
			
			commentaires = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return commentaires;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void updateCommentaire(Commentaire commentaire) throws Exception {

	Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(commentaire);
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
	public void deleteCommentaire(Commentaire commentaire) throws Exception {
	
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(commentaire);
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
	public List<Commentaire> getCommentaireByUser(int userId) throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		try {
			
			Query<Commentaire> query = session.createQuery("FROM Commentaire WHERE user_id = :userId",
//					Query<Commentaire> query = session.createNativeQuery("SELECT * FROM comment WHERE user_id = :userId",
					Commentaire.class);
			query.setParameter("userId", userId);
			Utils.trace(" getCommentaireByUser %s )\n",query);
			commentaires = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return commentaires;
	}
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<Commentaire> getCommentaireByUser(User user ) throws Exception{
		
		return this.getCommentaireByUser(user.getId());
	}


//-------------------------------------------------------------------------------------------------

}
