package fr.ecommerce.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.common.IConstant;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.entity.Categorie;
import fr.ecommerce.entity.User;
import fr.ecommerce.enums.Profile;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.CategorieDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.ICategorieDao;
import fr.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class ItemManagementBean extends MasterBean implements IConstant {

	List<Categorie> categorieList;
	int categorieId;
	int nbItem;
	Categorie currentCategorie;
	private boolean articleChecked;
	List<Article> articleList;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public ItemManagementBean() {

		if (this.getCategorieList() == null) {
			this.setCategorieList(new ArrayList<Categorie>());
		}
		if (this.getArticleList() == null) {
			this.setArticleList(new ArrayList<Article>());
		}
		ICategorieDao categorieDao = new CategorieDao();
		try {
			categorieList = categorieDao.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (this.getCategorieList().size() >= 0) { // prendre l'id (par défaut) du premier de la liste
			this.setCategorieId(this.getCategorieList().get(0).getId());

		} else
			this.setCategorieId(DEFAULT_ID); // in case of empty list

		setCurrentCategorie(this.getCategorieId());
		this.cleanPromptStatus();
		this.setPromptStatus(String.format("catégorie en cours : %s", this.getCurrentCategorie().getName()));

//		cheatCode();
		
	}
	// -------------------------------------------------------------------------------------------------
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String updateCategorie(Categorie categorie) {

		// redirection vers une nouvelle page de gestion des catégorie
		// la page est pré-remplit avec la catégorie courante
		String pageReturn = null;

		return pageReturn;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String deleteCategory(Categorie categorie) {

		String pageReturn = null;

		return pageReturn;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action /
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String updateItem(Categorie categorie) {

		String pageReturn = null;

		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String updateItem(Article article) {

		String pageReturn = null;

		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String deleteItem(Article article) {

		String pageReturn = null;

		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String addItemToCart() {

		String pageReturn = null;
		Article article = new Article();
		ArticlePanier articlePanier;

		for (String articleStringId : this.getLoginBean().getPickUpArticleList()) {

			Utils.trace("[%s]\n", articleStringId);
			int articleId = Integer.parseInt(articleStringId);
			if (articleId > 0) {
				article = getArticle(articleId);
				articlePanier = new ArticlePanier(1, article);
				this.getLoginBean().getUser().addCartItem(articlePanier);
			}
		}
		this.getLoginBean().getPickUpArticleList().clear(); // clear the list once transfered in user.cartItemList

		this.getLoginBean().setLabelCart(String.format("%d", this.getLoginBean().getUser().getCartItemList().size()));
		return pageReturn;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String deleteItem(ArticlePanier articlePanier) {
		String pageReturn = null;
		this.getLoginBean().getUser().getCartItemList().remove(articlePanier);
		return pageReturn;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% action
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String updateCurrentCategorie(ValueChangeEvent eventCategoryList) {
		String pageReturn = null;
		int categoryId = (int) eventCategoryList.getNewValue();

		this.setCurrentCategorie(categoryId);

		this.setPromptStatus(String.format("catégorie en cours : %s", this.getCurrentCategorie().getName()));
		return pageReturn;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// -------------------------------------------------------------------------------------------------
	public String calculateOrder() {
		String pageReturn = null;

		for (ArticlePanier articlePanier : this.getLoginBean().getUser().getCartItemList()) {

			System.out.print(articlePanier.getQuantity());
			System.out.print(articlePanier.getArticle().getPrice());
			System.out.print(articlePanier.getArticle().getDiscount());
			System.out.println(articlePanier.getArticle().getName());

//			Utils.trace ("[%d][%d][%d]%s\n",articlePanier.getQuantity(),
//					articlePanier.getArticle().getPrice(),
//					articlePanier.getArticle().getDiscount(),
//					articlePanier.getArticle().getName() );
		}
		return pageReturn;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// -------------------------------------------------------------------------------------------------
	public String validateOrder() {
		String pageReturn = null;

		return pageReturn;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// -------------------------------------------------------------------------------------------------
	public Article getArticle(int articleId) {

		Article article = new Article();
		IArticleDao articleDao = new ArticleDao();
		try {
			article = articleDao.getArticleById(articleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	// -------------------------------------------------------------------------------------------------
	public List<Categorie> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}

	public int getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}

	public Categorie getCurrentCategorie() {
		return currentCategorie;
	}

	public void setCurrentCategorie(int categoryId) {
// this method is needed to change categoryId into indexof 
// categorieList in order to retreive the rightcategory

		for (Categorie categorie : this.getCategorieList()) {
			if (categorie.getId() == categoryId) {
				this.setCurrentCategorie(categorie);
				break; // once found, no need to loop
			}
		}

	}

	public void setCurrentCategorie(Categorie currentCategorie) {
		this.currentCategorie = currentCategorie;
	}

	public boolean isArticleChecked() {
		return articleChecked;
	}

	public void setArticleChecked(boolean articleChecked) {
		this.articleChecked = articleChecked;
	}

	public boolean getIsArticleChecked() {
		return this.getIsArticleChecked();
	}

	public void setIsArticleChecked(boolean articleChecked) {
		this.setArticleChecked(articleChecked);
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public int getNbItem() {
		return nbItem;
	}

	public void setNbItem(int nbItem) {
		this.nbItem = nbItem;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void cheatCode()  {
		int maxIndex = 3;
		Article article;
		User userRetreive = null  ; 
		ArticlePanier articlePanier;
		Utils.trace("cheatcode");

		IUserCtrl userCtrl = new UserCtrl();
		try {
			userRetreive = userCtrl.getUserById(12);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Utils.trace(" user retreibe %s",userRetreive);
		userRetreive.setProfile(Profile.COSTUMER);
		Utils.trace("cheatcode");
		this.getLoginBean().setUser(userRetreive);
		Utils.trace("cheatcode");
		
		for (int index = 10; index < maxIndex; index++) {
			article = getArticle(index);
			articlePanier = new ArticlePanier(1, article);
			this.getLoginBean().getUser().addCartItem(articlePanier);
			Utils.trace("cheatcode");
		}
		Utils.trace("cheatcode");
	}

}
