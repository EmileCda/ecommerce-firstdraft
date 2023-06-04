package fr.ecommerce.Ctrl.implement;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.interfaces.ICartePaiementCtrl;
import fr.ecommerce.entity.CartePaiement;
import fr.ecommerce.model.dao.implement.CartePaiementDao;
import fr.ecommerce.model.dao.interfaces.ICartePaiementDao;
import fr.ecommerce.utils.Code;
import fr.ecommerce.utils.Dates;
import fr.ecommerce.utils.Utils;

public class CartePaiementCtrl implements ICartePaiementCtrl {

	
	
	
	public CartePaiementCtrl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCartePaiement(CartePaiement cartePaiement) throws Exception {
		cartePaiement = preWrite(cartePaiement);
		ICartePaiementDao cartePaiementDao = new CartePaiementDao();
		cartePaiementDao.addCartePaiement(cartePaiement);

	}

	@Override
	public CartePaiement getCartePaiementById(int id) throws Exception {
		ICartePaiementDao cartePaiementDao = new CartePaiementDao();
		CartePaiement cartePaiement = new CartePaiement();
		cartePaiement = cartePaiementDao.getCartePaiementById(id);
		return postRead(cartePaiement);
	}

	@Override
	public List<CartePaiement> getCartePaiements() throws Exception {
		List<CartePaiement> cartePaiementList = new ArrayList<CartePaiement>();
		List<CartePaiement> cartePaiementListReturn = new ArrayList<CartePaiement>();

		ICartePaiementDao cartePaiementDao = new CartePaiementDao();
		cartePaiementList = cartePaiementDao.getCartePaiements();

		for (CartePaiement cartePaiement : cartePaiementList) {

			cartePaiementListReturn.add(postRead(cartePaiement));

		}

		return cartePaiementListReturn;
	}

	@Override
	public CartePaiement updateCartePaiement(CartePaiement cartePaiement) throws Exception {

		ICartePaiementDao cartePaiementDao = new CartePaiementDao();
		cartePaiement = preWrite(cartePaiement);
		cartePaiementDao.updateCartePaiement(cartePaiement);
		postRead(cartePaiement);
		return cartePaiement;
	}

	@Override
	public CartePaiement invaliderCartePaiement(CartePaiement cartePaiement) throws Exception {

		cartePaiement.setIsValid(false);
		return updateCartePaiement(cartePaiement);
	}

	// -------------------------------------------------------------------------------------------------
	public CartePaiement preWrite(CartePaiement cartePaiement) {
		if (cartePaiement != null) {
			cartePaiement.setCardNumberEncrypted(Code.encrypt(cartePaiement.getCardNumber()));
			cartePaiement.setCryptoEncrypted(Code.encrypt(cartePaiement.getCrypto()));
			cartePaiement.setExpiryDateSql(Dates.convertDateUtilToSql(cartePaiement.getExpiryDate()));
		}
		return cartePaiement;
	}

	// -------------------------------------------------------------------------------------------------
	public CartePaiement postRead(CartePaiement cartePaiement) {
		if (cartePaiement != null) {
			cartePaiement.setCardNumber(Code.decrypt(cartePaiement.getCardNumberEncrypted()));
			cartePaiement.setCrypto(Code.decrypt(cartePaiement.getCryptoEncrypted()));
			cartePaiement.setExpiryDate(Dates.convertDateSqlToUtil(cartePaiement.getExpiryDateSql()));
		}
		return cartePaiement;
	}

}
