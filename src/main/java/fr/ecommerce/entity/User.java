package fr.ecommerce.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.ecommerce.common.IConstant;
import fr.ecommerce.enums.Gender;
import fr.ecommerce.enums.Profile;
import fr.ecommerce.utils.Utils;

@Entity
@Table(name = "user")
public final class User implements IConstant, Serializable {


	private static final long serialVersionUID = 6806127820490176944L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Profile profile;
	@Column(unique = true, length = 100, nullable = false)
	private String email;
	@Column(name = "password_encrypted", nullable = false)
	private byte[] passwordEncrpted;
	@Transient
	private String password;
	@Column(name = "is_actif", nullable = false)
	private Boolean isActif;
	private Gender gender;
	private String firstname;
	private String lastname;
	@Transient
	private Date birthdate;
	@Column(name = "birthdate_sql", nullable = false)
	private java.sql.Date birthdateSql;
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Adresse> addressList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<CartePaiement> bankCardList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<ArticlePanier> cartItemList; // meaning cart : item + quan

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Commande> orderList;

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Commentaire> commentList;

	public User() {
		this(DEFAULT_ID, DEFAULT_PROFILE, DEFAULT_EMAIL, DEFAULT_PASSWORD, true, DEFAULT_GENDER, DEFAULT_FIRSTNAME,
				DEFAULT_LASTNAME, DATE_NOW, DEFAULT_PHONE);

	}

	public User(Profile profile, String email, String password, Boolean isActif) {
		this(DEFAULT_ID, profile, email, password, isActif, DEFAULT_GENDER, DEFAULT_FIRSTNAME, DEFAULT_LASTNAME,
				DATE_NOW, DEFAULT_PHONE);
	}

	public User(int id, Profile profile, String email, String password, Boolean isActif, Gender gender,
			String firstname, String lastname, Date birthdate, String phoneNumber) {
		this.setId(id);
		this.setProfile(profile);
		this.setEmail(email);
		this.setPassword(password);
		this.setIsActif(isActif);
		this.setGender(gender);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setBirthdate(birthdate);
		this.setPhoneNumber(phoneNumber);
		initAddressList();
		initOrderList();
		initBankCardList();
		initCommentList();
		initCartItemList();

	}

	public void addAddress(Adresse address) {
		initAddressList();
		this.getAddressList().add(address);
	}

	public void addBankCard(CartePaiement bankCard) {
		initBankCardList();
		this.getBankCardList().add(bankCard);

	}

	public void addOrder(Commande order) {
		initOrderList();
		this.getOrderList().add(order);

	}

	public void addComment(Commentaire comment) {
		initCommentList();
		this.getCommentList().add(comment);

	}

	public void addCartItem(ArticlePanier cartItem) {
		initCartItemList();
		cartItem.setUser(this);
		this.getCartItemList().add(cartItem);

	}

	public void initBankCardList() {
		if (this.getBankCardList() == null)
			this.setBankCardList(new ArrayList<CartePaiement>());

	}

	public void initAddressList() {
		if (this.getAddressList() == null)
			this.setAddressList(new ArrayList<Adresse>());

	}

	public void initOrderList() {
		if (this.getOrderList() == null)
			this.setOrderList(new ArrayList<Commande>());

	}

	public void initCommentList() {

		if (this.getCommentList() == null)
			this.setCommentList(new ArrayList<Commentaire>());
	}

	public void initCartItemList() {
		Utils.trace("initCartItemList : %s \n", this.getCartItemList() == null ? "null" : "pas null");

		if (this.getCartItemList() == null) {
			Utils.trace("initCartItemList\n");
			this.setCartItemList(new ArrayList<ArticlePanier>());
			Utils.trace("initCartItemList : %s \n", this.getCartItemList() == null ? "null" : "pas null");
		}
	}


	public void clean() {
		this.setId(DEFAULT_ID);
		this.setEmail(null);
		this.setPassword(null);
		this.setFirstname(null);
		this.setLastname(null);
		this.setPhoneNumber("");
		this.setGender(null);
		this.setProfile(null);
		this.setBirthdate(null);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPasswordEncrpted() {
		return passwordEncrpted;
	}

	public void setPasswordEncrpted(byte[] passwordEncrpted) {
		this.passwordEncrpted = passwordEncrpted;
	}

	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Adresse> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Adresse> addressList) {
		this.addressList = addressList;
	}

	public List<CartePaiement> getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List<CartePaiement> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public List<ArticlePanier> getCartItemList() {
		return this.cartItemList;
	}

	public void setCartItemList(List<ArticlePanier> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public List<Commande> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Commande> orderList) {
		this.orderList = orderList;
	}

	public List<Commentaire> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Commentaire> commentList) {
		this.commentList = commentList;
	}

	public java.sql.Date getBirthdateSql() {
		return birthdateSql;
	}

	public void setBirthdateSql(java.sql.Date birthdateSql) {
		this.birthdateSql = birthdateSql;
	}

	@Override
	public String toString() {
		String stringReturn = "";
		stringReturn += String.format("Id[%d], %s, %s,%s, %s, %s, {%s}\n", getId(), getProfile().getName(),
				getFirstname(), getLastname(), getEmail(), getPassword(), (getIsActif() ? "" : "non-") + "actif");
		if (this.getAddressList().size() > 0) {
			stringReturn += String.format("\t%s\n", "Les adresses");
			for (Adresse adresse : this.getAddressList()) {
				stringReturn += String.format("\t%s\n", adresse);
			}
		}
		if (this.getBankCardList().size() > 0) {
			stringReturn += String.format("\t%s\n", "Les carte de paiement");
			for (CartePaiement bankCard : this.getBankCardList()) {
				stringReturn += String.format("\t%s\n", bankCard);
			}
		}

		return stringReturn;

	}

}
