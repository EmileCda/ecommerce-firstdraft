package fr.ecommerce.Ctrl.interfaces;

import java.util.List;

import fr.ecommerce.entity.User;

public interface IUserCtrl {
	
	public void addUser(User user) throws Exception ;
	public User getUserById(int id) throws Exception;
	public User getUserByEmail(String email) throws Exception;
	public List<User> getUsers() throws Exception;
	public void updateUser(User user) throws Exception; 
	public int deleteUser(User user) throws Exception ;
	
	
}
