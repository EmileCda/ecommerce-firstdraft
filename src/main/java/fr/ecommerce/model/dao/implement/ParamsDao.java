package fr.ecommerce.model.dao.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ecommerce.entity.User;
import fr.ecommerce.model.connector.hibernateConnector.HibernateConnector;
import fr.ecommerce.model.dao.interfaces.IParamsDao;
import fr.ecommerce.utils.Params;

public class ParamsDao implements IParamsDao {

	@Override
	public void addParams(Params params) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(params);
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
	public Params getParamsById(int id) throws Exception {
		Session session = HibernateConnector.getSession();
		Params params = null;
		try {
			params = session.find(Params.class, id);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return params;
	}

	
	//-------------------------------------------------------------------------------------------------

	@Override
	public List<Params> getParamss() throws Exception {
		
		Session session = HibernateConnector.getSession();
		List<Params> paramss = new ArrayList<Params>();
		try {
			
			Query<Params> query = session.createQuery("FROM Params a", Params.class);
			
			paramss = query.list();  
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return paramss;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void updateParams(Params params) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(params);
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
	public void deleteParams(Params params) throws Exception {
		Session session = HibernateConnector.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(params);
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
	public Params getParamsFunctionCode(int functionCode) throws Exception {
		Session session = HibernateConnector.getSession();
		Params params = null;
		try {
			Query<Params> query = session.createQuery("FROM Params p WHERE p.functionCode = ?1", Params.class);
			query.setParameter(1, functionCode);
			params = query.uniqueResult();   
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return params;
	}



//-------------------------------------------------------------------------------------------------

}
