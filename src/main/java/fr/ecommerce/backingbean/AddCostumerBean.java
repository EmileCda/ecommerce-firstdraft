package fr.ecommerce.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ecommerce.common.IConstant;

import fr.ecommerce.entity.User;

@ManagedBean(name ="addCostumerBean")
@SessionScoped

public class AddCostumerBean implements Serializable,IConstant{
	
	private static final long serialVersionUID = 1L;
	
	private String passwordRepeat ;
	private String birthDateString ;
	private  User user ; 
	
	public AddCostumerBean() {
		this.setUser(user);
		initTestingCostumer(); // for testing to be remove in production 
		
	}
	
	
	public void initTestingCostumer() {
	}
		
	
	public String add() throws Exception {
		
		
		return null; 
		
	}
	
	
	public String generateUser() throws Exception {
				
		initTestingCostumer();
		return null; 

		
	}
	
	
	
	
	public String getPasswordRepeat() {
		return this.passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;

	}
	public String getBirthDateString() {
		return this.birthDateString;
	}

	public void setBirthDateString(String birthDateString) {
		this.birthDateString = birthDateString;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	

}
