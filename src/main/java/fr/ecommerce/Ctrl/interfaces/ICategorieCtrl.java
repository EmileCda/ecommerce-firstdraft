package fr.ecommerce.Ctrl.interfaces;


import java.util.List;

import fr.ecommerce.entity.Categorie;


public interface ICategorieCtrl {
	
	
	public void addCategorie(Categorie Categorie) throws Exception ;
	public Categorie getCategorieById(int id) throws Exception;
	public Categorie getCategorieByEmail(String email) throws Exception;
	public List<Categorie> getCategories() throws Exception;
	public void updateCategorie(Categorie Categorie) throws Exception; 
	
	public void deleteCategorie(Categorie categorie) throws Exception; 
	
}
