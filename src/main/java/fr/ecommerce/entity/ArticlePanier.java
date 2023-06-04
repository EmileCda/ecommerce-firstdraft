package fr.ecommerce.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.ecommerce.common.IConstant;


@Entity
@Table(name="article_panier")
public class ArticlePanier implements IConstant, Serializable {
	
	
	
	private static final long serialVersionUID = -8285388363942947055L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article article;

	
	public ArticlePanier() {
		this(DEFAULT_ID,
				DEFAULT_QUANTITY,null,null
				);
		
	}
	public ArticlePanier(int quantity,User user , Article item) {
		this(DEFAULT_ID,quantity,user ,item);
	}

	public ArticlePanier(int quantity, Article item) {
		this(DEFAULT_ID,quantity,null,item);
	}

	public ArticlePanier(int id, int quantity, User user , Article article) {
		
		this.setId ( id);
		this.setQuantity ( quantity);
		this.setUser ( user);
		this.setArticle ( article);
	}



	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}

	
	@Override
	public String toString() {

		return String.format("Id[%d] %d x %s => %s",
				getId(),
				getQuantity(), 
				getArticle().getName(),
				getUser().getEmail());
				
	}
	

}
