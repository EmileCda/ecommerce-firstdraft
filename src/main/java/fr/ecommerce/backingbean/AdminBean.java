package fr.ecommerce.backingbean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.common.IConstant;
import fr.ecommerce.entity.User;


@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean  extends MasterBean implements IConstant {

	private List<User> userList = null;
	private List<User> reverseList  = null;
	public AdminBean() {

		RetreiveUserList();
	}
//-------------------------------------------------------------------------------------------------	
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String updateUser(User user) {

		this.cleanPromptStatus();
		this.setPromptStatus("Update user pour la prochaine version ");
		
		
		return null;
	}
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String deleteUser(User user) {
		
		this.cleanPromptStatus();
		
		User userCheck = null ; 
		this.setPromptStatus(null);
		String pageReturn = null ; 
		if (user == null) {
			return pageReturn;
		}
		// a partir d'ici, on traite le sujet car user != null  
		String email = user.getEmail();
		User userCopie = user ; 		// user de copie pour effacer aussi dans la liste
		int id = user.getId(); 
		
		IUserCtrl userCtrl = new UserCtrl();		// passer par le Ctrl pour nettoyer les objets lié 
		try {
			user = userCtrl.getUserById(user.getId());		// dès fois que l'utilisateur soit supprimé entre temps (acces concurentiel)
			if (user == null)
				this.setPromptStatus("Error:l'utilisateur n'existe pas\n");
			else {
				
				userCtrl.deleteUser(user);

				userCheck = userCtrl.getUserById(user.getId());// check après delete si le user existe toujours

				if (userCheck!= null) 
					this.setPromptStatus(String.format("Erreur:le sujet [%d] {%s}n'a pas pu être effacé",
							user.getId(),user.getEmail()));
 				else {
 					this.getUserList().remove(userCopie);		// suppression aussi dans la liste courant 
					this.setPromptStatus(String.format("sujet %d %s effacé", 
							id, email));
 				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) 
			user.clean();
		if (userCheck != null) 
			userCheck.clean();
		return pageReturn;
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//-------------------------------------------------------------------------------------------------	

		public void RetreiveUserList() {

			if (this.getUserList() == null) {
				this.setUserList(new ArrayList<User>()); 
			}else
				this.getUserList().clear();
			
			
			IUserCtrl userCtrl = new UserCtrl();
			try {
				userList = userCtrl.getUsers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//-------------------------------------------------------------------------------------------------	

		public List<User> getUserList() {
			return userList;
		}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public void initUserList() {

		if (this.getUserList() == null) 
			this.setUserList(new ArrayList<User>());
		
		IUserCtrl userCtrl = new UserCtrl();
		try {
			userList = userCtrl.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public List<User> getReverseList() {
		this.reverseList = this.getUserList();
		Collections.reverse(this.reverseList);
		return this.reverseList ;
	}

	public void setReverseList(List<User> reverseList) {
		this.reverseList = this.getUserList();
		Collections.reverse(this.reverseList);
	}

	


}
