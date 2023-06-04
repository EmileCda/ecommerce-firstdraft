package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.Article;
import fr.ecommerce.entity.ArticlePanier;
import fr.ecommerce.entity.User;
import fr.ecommerce.enums.Profile;
import fr.ecommerce.model.dao.implement.ArticleDao;
import fr.ecommerce.model.dao.implement.UserDao;
import fr.ecommerce.model.dao.interfaces.IArticleDao;
import fr.ecommerce.model.dao.interfaces.IUserDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

public class TUser {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		addArticlePanier();
//		initUserTest();
//		createOne();
//		createMany();
//		readOne(1);
//		readMany();
//		update();
//		delete();

		Utils.trace("*************************** end ************************************\n");

	}

	// -------------------------------------------------------------------------------------------------
	public static void initUserTest() {
		String StringArray[] = {"a","c","m"};
		User user = null ;
		int id;
		for (String string : StringArray) {
			user = getUser(string);
			if (user !=null) {
				remove(user.getId());
			}
			id = createOne();
			user = getUser(id);
			user.setEmail(string);
			user.setPassword(string);
			
			switch (string) {
			case "a" : user.setProfile(Profile.MANAGER); break ; 
			case "m" : user.setProfile(Profile.STORE_KEEPER); break ;
			default  : user.setProfile(Profile.COSTUMER); break ; 
			}
			
			update(user);
		}
//		

	}

	// -------------------------------------------------------------------------------------------------
	public static void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public static void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne(1);

	}

	// -------------------------------------------------------------------------------------------------
	public static void update(User user) {
		
		User usrCheck ; 

		IUserCtrl userCtrl = new UserCtrl();
		try {
			usrCheck = userCtrl.getUserById(user.getId());
			if (usrCheck == null)
				Utils.trace("User null \n");
			else {
				Utils.trace("Before  %s\n", usrCheck);
				userCtrl.updateUser(user);
				user = userCtrl.getUserById(user.getId());
				if (user != null)
					Utils.trace("After %s\n", user);
				else
					Utils.trace("Address null\n");
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------
	public static void update() {
		Utils.trace("=========================== Update ===========================\n");
		int userId = 5;
		User user = null;
		IUserCtrl userCtrl = new UserCtrl();
		try {
			user = userCtrl.getUserById(userId);
			if (user == null)
				Utils.trace("User null \n");
			else {
				Utils.trace("Before  %s\n", user);
				user.setFirstname("luulu");
				userCtrl.updateUser(user);
				user = userCtrl.getUserById(userId);
				if (user != null)
					Utils.trace("After %s\n", user);
				else
					Utils.trace("Address null\n");
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// -------------------------------------------------------------------------------------------------
	public static void delete() {
		int userId = 1;
		remove(userId);

	}

	// -------------------------------------------------------------------------------------------------
	public static void remove(int userId) {
		User user = new User();
		IUserCtrl userCtrl = new UserCtrl();
		IUserDao userDao = new UserDao();
		try {
			user = userCtrl.getUserById(userId);
			if (user == null)
				Utils.trace("Error : l'user n'existe pas\n");
			else {
				userDao.deleteUser(user);

				user = userCtrl.getUserById(userId);

				if (user != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// -------------------------------------------------------------------------------------------------

	public static int createOne() {

		User user = new User();
		user = DataTest.genUser();
		Utils.trace("%s\n", user);

		IUserCtrl userCtrl = new UserCtrl();

		try {
			userCtrl.addUser(user);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", user);
		return user.getId();
	}
	// -------------------------------------------------------------------------------------------------

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;

		User user = new User();
		Utils.trace("%s\n", user);

		IUserCtrl userCtrl = new UserCtrl();

		try {
			for (int index = 0; index < maxIndex; index++) {
				user = DataTest.genUser();
				userCtrl.addUser(user);
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}

	// -------------------------------------------------------------------------------------------------
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<User> userList = new ArrayList<User>();
		IUserCtrl userCtrl = new UserCtrl();
		try {
			userList = userCtrl.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((userList.size() > 0) && (userList != null)) {
			for (User user : userList) {
				Utils.trace("%s\n", user);
			}
		} else
			Utils.trace("user null");
	}

	// -------------------------------------------------------------------------------------------------
	public static User getUser(int userId) {

		User user = new User();
		IUserCtrl userCtrl = new UserCtrl();
		try {
			user = userCtrl.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	// -------------------------------------------------------------------------------------------------
	public static User getUser(String email) {

		User user = new User();
		IUserCtrl userCtrl = new UserCtrl();
		try {
			user = userCtrl.getUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	// -------------------------------------------------------------------------------------------------
	public static void readOne(String email) {
		Utils.trace("=========================== read One by email  ===========================\n");

		User user = getUser(email);
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

	}

	
	// -------------------------------------------------------------------------------------------------
	public static void readOne(int userId) {
		Utils.trace("=========================== read One  by Id===========================\n");
		
		User user = getUser( userId);
		if (user != null)
			Utils.trace("%s\n", user);
		else
			Utils.trace("user null\n");

	}
// -------------------------------------------------------------------------------------------------
		public static void addArticlePanier() {
			Utils.trace("%s\n", "ici");
			User user = new User();
			Utils.trace("%s\n",user);
			Article article = new Article();

			for (int index = 10; index < 20; index ++) {
				user = getUser(index);
				if (user != null ) break; 
				
			}
			
			Utils.trace("%s\n",user);
		
			ArticlePanier  articlePanier;
			for (int index= 10 ; index < 20 ; index++) {
				
				article= getArticle(index);
				Utils.trace("%s\n",article);
				
				articlePanier = new ArticlePanier(100+index, article);
				
				user.addCartItem(articlePanier);
				
			}
			Utils.trace("%s\n",user);
			Utils.trace("%d\n",user.getCartItemList().size());
			
			for (ArticlePanier aPanier : user.getCartItemList()) {
				Utils.trace("%s\n",
						aPanier.getArticle().getName());
				
				Utils.trace("%s \n",
						aPanier.getUser().getEmail());
			}
			
		}


		
//-------------------------------------------------------------------------------------------------		
		public static Article getArticle(int ArticleId) {
			Article article = new Article();
			IArticleDao articleDao = new ArticleDao();
			try {
				article = articleDao.getArticleById(ArticleId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return article ; 

		}

}
