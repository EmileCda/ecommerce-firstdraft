package fr.ecommerce.backingbean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.common.IConstant;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.entity.User;
import fr.ecommerce.enums.Profile;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends MasterBean implements IConstant {

	private User user;

	private String welcome = null;
	private String labelCart = null;
	private boolean isAdmin;
	private boolean isStoreKeeper;
	private boolean isClient;;
	private boolean nbItemCart;;
	private boolean isConnected;;
	List<String> pickUpArticleList;

	public LoginBean() {

		user = new User();
		this.clean();
		this.setLabelCart(ResourceBundle.getBundle("webPage").getString("menu.cart"));

		if (this.getPickUpArticleList() == null) {
			this.setPickUpArticleList(new ArrayList<String>());
		}

		
	}

//%%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String checkUser() throws Exception {
		User userRetreive = new User();
		Utils.trace(HR);
		String pageReturn = null;

		if (getUser() == null) {
			return pageReturn;
		}

		String passwordSaisi = getUser().getPassword();
		IUserCtrl userCtrl = new UserCtrl();
		userRetreive = userCtrl.getUserByEmail(getUser().getEmail());
		if (userRetreive == null)
			return pageReturn;
		this.setUser(userRetreive);
		this.setPromptStatus("");

		if (userRetreive.getPassword().equals(passwordSaisi)) {
			this.setConnected(true);

			switch (this.getUser().getProfile()) {
			case COSTUMER:
				pageReturn = CLIENT_HOME;
				break;
			case MANAGER:
				pageReturn = ADMIN_HOME;
				break;
			case STORE_KEEPER:
				pageReturn = STOREKEEPER_HOME;
				break;
			default:
				return pageReturn = HOME;
			}
			this.setWelcome(String.format("Bonjour cher(e) [%s] %s", user.getProfile().getName(), user.getFirstname()));
			user.setPassword("");// cleaning input

		} else
			this.setPromptStatus("erreur login Ou mot de pass");

		return pageReturn;

	}

//-------------------------------------------------------------------------------------------------	
	public User getUser() {
		return user;
	}

//-------------------------------------------------------------------------------------------------	
	public void setUser(User user) {
		this.user = user;
	}

//-------------------------------------------------------------------------------------------------	
	public void initTestUser() {
		String firstname = DataTest.firstname();
		this.getUser().setEmail(DataTest.email(firstname, DataTest.lastname()));
		this.getUser().setPassword(DataTest.pass(firstname));
	}

	// -------------------------------------------------------------------------------------------------
	public String disconnect() {

		resetContent();
		return HOME;
	}

	// -------------------------------------------------------------------------------------------------
	public void clean() {

		if (this.getUser() != null)
			this.getUser().clean();
		this.setPromptStatus(null);
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = this.getUser() .getProfile() == Profile.MANAGER;
	}

	public boolean getIsAdmin() {

		if (this.getUser() != null)
			return this.getUser().getProfile() == Profile.MANAGER;

		return false;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.setAdmin(isAdmin);
	}

	public boolean isStoreKeeper() {
		if (this.getUser() != null)
			return this.getUser() .getProfile() == Profile.STORE_KEEPER;
		return false;
	}

	public boolean getIsStoreKeeper() {
		return this.isStoreKeeper();
	}

	public void setIsStoreKeeper(boolean isStoreKeeper) {
		this.setStoreKeeper(isStoreKeeper);
	}

	public void setStoreKeeper(boolean isStoreKeeper) {
		this.isStoreKeeper = this.getUser() .getProfile() == Profile.STORE_KEEPER;
	}

	public boolean getIsClient() {
		return this.isClient();
	}

	public void setClient(boolean isClient) {
		this.isClient = this.getUser() .getProfile() == Profile.COSTUMER;
	}

	public boolean isClient() {
		if (this.getUser() != null)
			return this.getUser() .getProfile() == Profile.COSTUMER;
		return false;
	}

	public void setIsClient(boolean isClient) {
		this.setClient(this.getUser() .getProfile() == Profile.COSTUMER);
	}

	

	public boolean isNbItemCart() {
		return nbItemCart;
	}

	public void setNbItemCart(boolean nbItemCart) {
		this.nbItemCart = nbItemCart;
	}

	public String getLabelCart() {
		return labelCart;
	}

	public void setLabelCart(String labelCart) {
		this.labelCart = labelCart;
	}

	public void resetContent() {

		this.getUser().clean();
		this.setPromptStatus("");
		this.setWelcome("");
		this.setIsConnected(false);
		this.setLabelCart(ResourceBundle.getBundle("webPage").getString("menu.cart"));
		this.setAdmin(false);
		this.getPickUpArticleList().clear();

	}

	public List<String> getPickUpArticleList() {
		return pickUpArticleList;
	}

	public void setPickUpArticleList(List<String> pickUpArticleList) {
		this.pickUpArticleList = pickUpArticleList;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean getIsConnected() {
		return this.isConnected();

	}

	public void setIsConnected(boolean isConnected) {
		this.setConnected(isConnected);
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

		public void cheatTest(Profile profile) {

		User cheatUser = new User();
		cheatUser = DataTest.genUser();
		cheatUser.setProfile(profile);
		this.setUser(cheatUser);
		this.setIsClient(this.user.getProfile() == Profile.COSTUMER);
		this.setWelcome(String.format("Bonjour cheatUser [%s] %s is client %b--%b", getUser() .getProfile().getName(),
				getUser() .getFirstname(), this.getIsClient(), this.isClient));

	}
}
