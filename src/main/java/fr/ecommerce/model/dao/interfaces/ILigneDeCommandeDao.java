package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.LigneDeCommande;



public interface ILigneDeCommandeDao {
	
	public void addLigneDeCommande (LigneDeCommande ligneDeCommande)  throws Exception;

	public LigneDeCommande getLigneDeCommandeById(int id) throws Exception;

	public List<LigneDeCommande> getLigneDeCommandes() throws Exception;

	public void updateLigneDeCommande(LigneDeCommande ligneDeCommande) throws Exception; 
	public void deleteLigneDeCommande (LigneDeCommande ligneDeCommande) throws Exception;


}
