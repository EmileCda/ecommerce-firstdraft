package fr.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.ecommerce.common.IConstant;


@Entity
@Table(name = "comment")
public class Commentaire  implements IConstant, Serializable {

	
	
	private static final long serialVersionUID = -4532625283345323157L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String  text;
	private int   grade ; // from 0 to 5 

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Article article;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	public Commentaire() {
		this(DEFAULT_ID,DEFAULT_TEXT,DEFAULT_GRADE,null,null);
	}
	
	public Commentaire( String text, int grade) {
		this(DEFAULT_ID,text, grade, null, null);
	}
	
	public Commentaire(int id, String text, int grade, Article article, User user) {
		this.setId ( id);
		this.setText ( text);
		this.setGrade ( grade);
		this.setItem ( article);
		this.setUser( user);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getGrade() {
		return grade;
	}

	public String getStarGrade() {
		String stringReturn="";
		for (int index = 0; index < this.getGrade(); index++) {
			stringReturn +="*";
		}
		return stringReturn;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public Article getItem() {
		return article;
	}


	public void setItem(Article article) {
		this.article = article;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	
	@Override
	public String toString() {
		return String.format("Id[%d], %s %s, pour:%s:[%s], de :%s", 
				getId(), getStarGrade(),getText(), 
				getItem().getName(),getItem().getCategorie().getName(), 
				getUser().getEmail());
	}


}
