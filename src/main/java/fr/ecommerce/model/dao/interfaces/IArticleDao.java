package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.Article;


public interface IArticleDao {
	
	public void addArticle (Article article)  throws Exception;

	public Article getArticleById(int id) throws Exception;
	public List<Article> getArticles() throws Exception;

	public void updateArticle(Article article) throws Exception; 
	public void deleteArticle (Article article)  throws Exception;
	

}
