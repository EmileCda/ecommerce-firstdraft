package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.Categorie;


public interface ICategorieDao {
	
	public void addCategorie (Categorie Categorie)  throws Exception;

	public Categorie getCategorieById(int id) throws Exception;
	public List<Categorie> getCategories() throws Exception;

	public void updateCategorie(Categorie Categorie) throws Exception; 
	public void deleteCategorie (Categorie Categorie) throws Exception;
	


}
