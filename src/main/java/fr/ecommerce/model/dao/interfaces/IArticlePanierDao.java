package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.ArticlePanier;


public interface IArticlePanierDao {
	
	public void addArticlePanier (ArticlePanier ArticlePanier)  throws Exception;

	public ArticlePanier getArticlePanierById(int id) throws Exception;
	public List<ArticlePanier> getArticlePaniers() throws Exception;

	public void updateArticlePanier(ArticlePanier ArticlePanier) throws Exception; 
	public void deleteArticlePanier (ArticlePanier ArticlePanier)  throws Exception;


}
