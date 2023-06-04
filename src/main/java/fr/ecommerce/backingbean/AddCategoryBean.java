package fr.ecommerce.backingbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ecommerce.Ctrl.implement.UserCtrl;
import fr.ecommerce.Ctrl.interfaces.IUserCtrl;
import fr.ecommerce.common.IConstant;
import fr.ecommerce.entity.Categorie;
import fr.ecommerce.enums.Profile;
import fr.ecommerce.model.dao.implement.CategorieDao;
import fr.ecommerce.model.dao.interfaces.ICategorieDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class AddCategoryBean extends MasterBean implements IConstant {

	Categorie categorie;
	
	private boolean trueValue ;
	private boolean falseValue ;
	private int		cumulativeDiscount;
	

	public AddCategoryBean() {
		
		this.setCategorie(new Categorie() );
		this.setFalseValue(false);
		this.setTrueValue(true);


	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public String addCategorie() {
		Utils.trace("addCategorie() %s \n",this.getCategorie());
		Utils.trace("addCategorie() %s \n",this.getCumulativeDiscount());
		this.getCategorie().setDiscountCumulative(getCumulativeDiscount()>0?true : false);
			
		Utils.trace("addCategorie() %s \n",this.getCategorie());
		
		String landingWebPage = STOREKEEPER_HOME;
		ICategorieDao categorieDao = new CategorieDao();

		try {
			categorieDao.addCategorie(this.getCategorie());
		} catch (Exception e) {
			Utils.trace("catch addcategorie %s\n", e.toString());
			return HOME;
		}
		return landingWebPage;
	}

// %%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public String cheatCode() {
		
		this.setCategorie(DataTest.genCategorie());

		if (this.getCategorie() != null)
			Utils.trace("cheatCode user %s\n", this.getCategorie());
		else
			Utils.trace("cheatCode Categorie null \n");
		return null;
	}
//--------------------------------------------------------------------------------
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isTrueValue() {
		return trueValue;
	}

	public void setTrueValue(boolean trueValue) {
		this.trueValue = trueValue;
	}

	public boolean isFalseValue() {
		return falseValue;
	}

	public void setFalseValue(boolean falseValue) {
		this.falseValue = falseValue;
	}

	public int getCumulativeDiscount() {
		return cumulativeDiscount;
	}

	public void setCumulativeDiscount(int cumulativeDiscount) {
		this.cumulativeDiscount = cumulativeDiscount;
	}

}
