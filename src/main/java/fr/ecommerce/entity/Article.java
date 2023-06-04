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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.ecommerce.common.IConstant;


@Entity
@Table(name = "article")
public class Article implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private float price;
	private int discount; // in %
	private int inventory;
	@Column(name = "is_sallable")
	private boolean isSalable;
	private String picture;
	private String video;

	@ManyToOne
	@JoinColumn(name = "categorie_id" ,nullable = false)
	private Categorie categorie;
	
	
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "article", fetch = FetchType.LAZY)
	private List<Commentaire> commentList;


	
	@Transient
	private List<ArticlePanier > cartItemList;

	@Transient
	private List<LigneDeCommande> orderLineList;

	public Article() {

		this(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESCRIPTION, DEFAULT_PRICE, DEFAULT_DISCOUNT, DEFAULT_INVENTORY, true,
				 DEFAULT_PICTURE, DEFAULT_VIDEO,null);
	}

	public Article( String name, String description, float price, int discount, int inventory, boolean isSalable,
			 String picture, String video) {
		this(DEFAULT_ID, name, description, price, discount, inventory, isSalable,
				 picture, video,null);
	}

	public Article(int id, String name, String description, float price, int discount, int inventory, boolean isSalable,
				String picture, String video,Categorie categorie ) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setDiscount(discount);
		this.setInventory(inventory);
		this.setSalable(isSalable);
		this.setPicture(picture);
		this.setVideo(video);
		this.setCategorie(categorie);
		initCommentList();
		initCartItemList();

	}
	public void addComment(Commentaire comment) {
		initCommentList();
		this.getCommentList().add(comment);
		
	}

	public void addCartItem(ArticlePanier cartItem) {
		initCartItemList();
		this.getCartItemList().add(cartItem);
		
	}
	public void addOrderLine(LigneDeCommande orderLine) {
		initOrderLineList();
		this.getOrderLineList().add(orderLine);
		
	}

	
	public void initOrderLineList() {
		if (this.getOrderLineList()== null) {
			this.setOrderLineList(new ArrayList<LigneDeCommande>())  ;
		}
		
	}
	
	public void initCommentList() {
		if (this.getCommentList()== null) {
			this.setCommentList(new ArrayList<Commentaire>())  ;
		}
		
	}
	
	
	public void initCartItemList() {
		if (this.getCartItemList()== null) {
			this.setCartItemList(new ArrayList<ArticlePanier>())  ;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public boolean isSalable() {
		return isSalable;
	}

	public boolean getIsSalable() {
		return isSalable();
	}

	public void setSalable(boolean isSalable) {
		this.isSalable = isSalable;
	}

	public void setIsSalable(boolean isSalable) {
		this.setSalable(isSalable);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}



	public List<Commentaire> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Commentaire> commentList) {
		this.commentList = commentList;
	}


	public void setCommentList(List<Commentaire> commentList) {
		this.commentList = commentList;
	}

	public List<ArticlePanier> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<ArticlePanier> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public List<LigneDeCommande> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<LigneDeCommande> orderLineList) {
		this.orderLineList = orderLineList;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return String.format("Id[%d] %s : %s %.2f€, -%d %%, stock:%d, %svendable, img:%s, vid :%s",
				getId(), getName(), getDescription(), getPrice(), getDiscount(), getInventory(), 
				isSalable()?"":"non-",
				getPicture(), getVideo());

	}

	public String toItemLabel() {
		return String.format("%s %.2f€, -%d%%, stock:%d, %svendable, img:%s, vid :%s",
				getName(),  getPrice(), getDiscount(), getInventory(), 
				isSalable()?"":"non-",
						getDescription(),
				getPicture(), getVideo());

	}



}
