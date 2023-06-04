package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.Article;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.IArticleDao;

public class ArticleDao implements IArticleDao {

	@Override
	public void addArticle(Article article) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(article);
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
	public Article getArticleById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Article article = null;
		try {
			article = session.find(Article.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return article;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Article> getArticles() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Article> articles = new ArrayList<Article>();
		try {
			
			Query<Article> query = session.createQuery("FROM Article a", Article.class);
			
			articles = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return articles;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void updateArticle(Article article) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(article);
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
	public void deleteArticle(Article article) throws Exception {
	
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(article);
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
