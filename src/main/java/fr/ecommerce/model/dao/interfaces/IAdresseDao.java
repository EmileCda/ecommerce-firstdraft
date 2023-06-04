package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.entity.Adresse;



public interface IAdresseDao {
	
	public void addAdresse(Adresse adresse) throws Exception ;
	public Adresse getAdresseById(int id) throws Exception;
	public List<Adresse> getAdresses() throws Exception;
	public void updateAdresse(Adresse adresse) throws Exception; 
	public void deleteAdresse(Adresse adresse) throws Exception ;

}
