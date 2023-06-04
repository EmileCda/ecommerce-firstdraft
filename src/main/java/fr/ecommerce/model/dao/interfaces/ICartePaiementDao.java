package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.CartePaiement;


public interface ICartePaiementDao {
	
	public void  addCartePaiement (CartePaiement cartePaiement)  throws Exception;

	public CartePaiement getCartePaiementById(int id) throws Exception;
	public List<CartePaiement> getCartePaiements() throws Exception;

	public void updateCartePaiement(CartePaiement cartePaiement) throws Exception; 
	public void deleteCartePaiement (CartePaiement cartePaiement) throws Exception;
	

}
