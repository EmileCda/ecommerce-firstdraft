package fr.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
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

import fr.ecommerce.common.IConstant;


@Entity

@Table(name="ligne_de_commande")
public class LigneDeCommande implements IConstant, Serializable {
	
	
	private static final long serialVersionUID = -23153027186502371L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int quantity;
		
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Commande order;
	
	@OneToOne
	@JoinColumn(name = "article_id", nullable = false, insertable = false, updatable = false)
	private Article item;
	
	public LigneDeCommande() {
		this(DEFAULT_ID,
				DEFAULT_QUANTITY,null,null);
		
	}
	public LigneDeCommande(int id, int quantity, Article item, Commande order) {
		this.setId ( id);
		this.setQuantity ( quantity);
		this.setItem ( item);
		this.setOrder (order);
	}
	
	
	
	
	
	
	public Commande getOrder() {
		return order;
	}
	public void setOrder(Commande order) {
		this.order = order;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Article getItem() {
		return item;
	}
	public void setItem(Article item) {
		this.item = item;
	}
	
	public String toString() {
		float unitPrice = getItem().getPrice();
		float totalPrice =unitPrice * getQuantity();
		float totalDiscount =totalPrice * getItem().getDiscount()/100;
		float toPay=totalPrice -totalDiscount;
		
		return String.format("%s : %d x %s   %.2f€  réduction :-%.2f€  to pay %.2f€", 
				getOrder().getOrderNumber(), 
				getQuantity(),getItem().getName(), 
				totalPrice,totalDiscount,toPay);
	}
	
	

}
