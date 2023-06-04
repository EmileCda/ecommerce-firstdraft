package fr.ecommerce.Ctrl.implement;

import java.util.ArrayList;
import java.util.List;

import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.entity.CartePaiement;
import fr.ecommerce.entity.Commentaire;
import fr.ecommerce.entity.User;
import fr.ecommerce.model.dao.implement.CommentaireDao;
import fr.ecommerce.model.dao.implement.UserDao;
import fr.ecommerce.model.dao.interfaces.ICommentaireDao;
import fr.ecommerce.model.dao.interfaces.IUserDao;
import fr.ecommerce.utils.Code;
import fr.ecommerce.utils.Dates;
import fr.ecommerce.utils.Utils;

public class UserCtrl implements IUserCtrl {

	public UserCtrl() {

	}

	@Override
	public void addUser(User user) throws Exception {

		user = preWrite(user);
		IUserDao userDao = new UserDao();
		userDao.addUser(user);

	}

//-------------------------------------------------------------------------------------------------
	@Override
	public User getUserById(int id) throws Exception {
		IUserDao userDao = new UserDao();
		User user = new User();
		user = userDao.getUserById(id);
		return postRead(user);
	}

	// -------------------------------------------------------------------------------------------------
	@Override
	public User getUserByEmail(String email) throws Exception {
		IUserDao userDao = new UserDao();
		User user = new User();
		user = userDao.getUserByEmail(email);
		return postRead(user);
	}

	// -------------------------------------------------------------------------------------------------
	@Override
	public List<User> getUsers() throws Exception {
		List<User> userList = new ArrayList<User>();
		List<User> userListReturn = new ArrayList<User>();

		IUserDao userDao = new UserDao();
		userList = userDao.getUsers();

		for (User user : userList) {

			userListReturn.add(postRead(user));

		}

		return userListReturn;
	}

	// -------------------------------------------------------------------------------------------------
	@Override
	public void updateUser(User user) throws Exception {

		user = preWrite(user);

		IUserDao userDao = new UserDao();
		userDao.updateUser(user);

	}

	// -------------------------------------------------------------------------------------------------
	private User preWrite(User user) {

		if (user != null) {
			user.setPasswordEncrpted(Code.encrypt(user.getPassword()));

			user.setBirthdateSql(Dates.convertDateUtilToSql(user.getBirthdate()));

			CartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();

			for (CartePaiement CartePaiement : user.getBankCardList()) {

				cartePaiementCtrl.preWrite(CartePaiement);
			}

		}
		return user;
	}

	// -------------------------------------------------------------------------------------------------
	private User postRead(User user) {

		if (user != null) {
			user.setPassword(Code.decrypt(user.getPasswordEncrpted()));
			user.setBirthdate(Dates.convertDateSqlToUtil(user.getBirthdateSql()));
			CartePaiementCtrl cartePaiementCtrl = new CartePaiementCtrl();

			for (CartePaiement CartePaiement : user.getBankCardList()) {

				cartePaiementCtrl.postRead(CartePaiement);
			}
		}
		return user;
	}

	// -------------------------------------------------------------------------------------------------
	private int deleteCommentr(User user) throws Exception {
		int status = -1;
		if (user == null)
			return status;

		List<Commentaire> commentList = new ArrayList<Commentaire>();

		ICommentaireDao commentaireDao = new CommentaireDao();
		Utils.trace("deleteUser(User user)\n");
		if (user.getCommentList() != null) {
			commentList = commentaireDao.getCommentaireByUser(user);
			status = commentList.size();
			if (commentList.size() > 0) {
				try {
					for (Commentaire commentaire : commentList) {
						commentaireDao.deleteCommentaire(commentaire);
						Utils.trace("delete comment : %s\n", commentaire);
					}
				} catch (Exception e) {
					Utils.trace("catch %s \n", e);
				}
			}
		}

		return status;
	}

	// -------------------------------------------------------------------------------------------------
	@Override
	public int deleteUser(User user) throws Exception {
		int status = -1;
		User userDeleted = null ; 
		status =  this.deleteCommentr(user) ;
				IUserDao userDao = new UserDao();
		try {
			int userId = user.getId();
			userDao.deleteUser(user);
			userDeleted = this.getUserById(userId);
			if (userDeleted != null)
				status-= 100;	
			else
				status +=100;
			
		}catch(	Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
