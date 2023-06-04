package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.Commentaire;
import fr.ecommerce.entity.User;


public interface ICommentaireDao {
	
	public void addCommentaire (Commentaire Commentaire)  throws Exception;

	public Commentaire getCommentaireById(int id) throws Exception;
	public List<Commentaire> getCommentaireByUser(int userId) throws Exception;
	public List<Commentaire> getCommentaireByUser(User user ) throws Exception;
	
	public List<Commentaire> getCommentaires() throws Exception;

	public void updateCommentaire(Commentaire Commentaire) throws Exception; 
	public void deleteCommentaire (Commentaire Commentaire) throws Exception;
	
	

}
