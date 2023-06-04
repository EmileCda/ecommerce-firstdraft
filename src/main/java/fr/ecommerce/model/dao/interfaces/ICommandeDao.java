package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.Commande;



public interface ICommandeDao {
	
	public void addCommande (Commande commande)  throws Exception;

	public Commande getCommandeById(int id) throws Exception;
	public List<Commande> getCommandes() throws Exception;

	public void updateCommande(Commande commande) throws Exception; 
	public void deleteCommande (Commande commande) throws Exception;
	

}
