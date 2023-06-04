package fr.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.ecommerce.common.IConstant;


@Entity
@Table(name = "categorie")
               
public class Categorie  implements IConstant, Serializable {

	

	private static final long serialVersionUID = 7120417570986735436L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private int discount;
	@Column(name = "is_discount_Cumulative")
	private boolean isDiscountCumulative;
	private String picture;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.LAZY)
	private List<Article> itemList;

	public Categorie() {
		this(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DISCOUNT, false, DEFAULT_PIC);
	}

	public Categorie(String name, int discount, boolean isDiscountCumulative, String picture) {
		this(DEFAULT_ID, name, discount, isDiscountCumulative, picture);
	}

	public Categorie(int id, String name, int discount, boolean isDiscountCumulative, String picture) {
		this.setId(id);
		this.setName(name);
		this.setDiscount(discount);
		this.setDiscountCumulative(isDiscountCumulative);
		this.setPicture(picture);
		initItemList();

	}

	public void addItem(Article item) {
		initItemList();
		this.getItemList().add(item);
	}

	public void initItemList() {
		if (this.getItemList() == null) {
			this.setItemList(new ArrayList<Article>());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isDiscountCumulative() {
		return isDiscountCumulative;
	}

	public boolean getIsDiscountCumulative() {
		return this.isDiscountCumulative();
	}

	public void setDiscountCumulative(boolean isDiscountCumulative) {
		this.isDiscountCumulative = isDiscountCumulative;
	}

	public void setIsDiscountCumulative(boolean isDiscountCumulative) {
		this.setDiscountCumulative(isDiscountCumulative);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Article> getItemList() {
		return this.itemList;
	}

	public void setItemList(List<Article> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {

		String stringReturn = "";
		stringReturn += String.format("Id[%d], %s -%d%% %scumulable pic:%s\n", getId(), getName(), getDiscount(),
				isDiscountCumulative() ? "" : "non-", getPicture());
		if (this.getItemList() != null) {
			for (Article item : this.getItemList()) {
				stringReturn += "\t" + item.toString() + "\n";
			}
		}
		return stringReturn;
	}

}
