package fr.ecommerce.Ctrl.interfaces;

import java.util.List;

import fr.ecommerce.entity.CartePaiement;

public interface ICartePaiementCtrl {
	
	public void addCartePaiement(CartePaiement cartePaiement) throws Exception ;
	public CartePaiement getCartePaiementById(int id) throws Exception;
	public List<CartePaiement> getCartePaiements() throws Exception;
	public CartePaiement  updateCartePaiement(CartePaiement cartePaiement) throws Exception; 
	public CartePaiement invaliderCartePaiement(CartePaiement cartePaiement) throws Exception; 
	
	
}
