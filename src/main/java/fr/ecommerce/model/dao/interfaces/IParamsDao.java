package fr.ecommerce.model.dao.interfaces;
import java.util.List;

import fr.ecommerce.utils.Params;




public interface IParamsDao {
	
	public void addParams (Params Params)  throws Exception;

	public Params getParamsById(int id) throws Exception;
	public Params getParamsFunctionCode(int id) throws Exception;
	public List<Params> getParamss() throws Exception;

	public void updateParams(Params Params) throws Exception; 
	public void deleteParams (Params Params)  throws Exception;
	

}
