package fr.ecommerce.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.ecommerce.common.IConstant;
import fr.ecommerce.enums.Gender;
import fr.ecommerce.utils.Encryption;
import fr.ecommerce.utils.Utils;

@Entity
@Table(name = "carte_paiement")
public class CartePaiement  implements IConstant, Serializable {

	
	private static final long serialVersionUID = -2511328389174440485L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "owner_gender")
	private Gender ownerGender;
	@Column(name = "owner_firstname")
	private String ownerFirstname;
	@Column(name = "owner_lastname")
	private String ownerLastname;
	@Column(name = "card_number_encrypted")
	private byte[] cardNumberEncrypted; // encrypted card number
	@Transient
	private String cardNumber; // not encrypted card number
	@Column(name = "expiry_date_SQL")
	private java.sql.Date expiryDateSql; // java date
	@Transient
	private Date expiryDate; // java date

	@Column(name = "crypto_encrypted")
	private byte[] cryptoEncrypted; // encrypted cryptoGrame
	@Transient
	private String crypto; // not encrypted
	@Column(name = "is_valid")
	private boolean isValid;
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	

	public CartePaiement() {
		this(DEFAULT_ID, DEFAULT_BANK_CARD_NUMBER, DATE_NOW, DEFAULT_BANK_CARD_CRYPTO, true, false, null);
	}

	public CartePaiement(String cardNumber, Date expiryDate, String crypto, User user) {

		this(DEFAULT_ID, cardNumber, expiryDate, crypto, true, false, user);

	}

	public CartePaiement(int id, String cardNumber, Date expiryDate, String crypto, boolean isValid, boolean isDeleted,
			User user) {
		this.setId(id);
		this.setCardNumber(cardNumber);
		this.setExpiryDate(expiryDate);
		this.setCrypto(crypto);
		this.setIsValid(isValid);
		this.setIsDeleted(isDeleted);
		this.setUser(user);

		if (this.getUser() != null) {
			this.setOwnerGender(this.getUser().getGender());
			this.setOwnerFirstname(this.getUser().getFirstname());
			this.setOwnerLastname(this.getUser().getLastname());

		}
	}
	// ================= constructor
	// ==================================================================

	public void preWrite() {
		try {
			this.setCardNumberEncrypted(Encryption.encrypt(this.getCardNumber()));
			this.setCryptoEncrypted(Encryption.encrypt(this.getCrypto()));
		} catch (UnsupportedEncodingException e) {
			Utils.trace("catch test encrypt" + e.toString());
		}

		this.setExpiryDateSql(Utils.toSqlDate(this.getExpiryDate()));
	}

	public void postRead() {

		this.setCardNumber(Encryption.decrypt(this.getCardNumberEncrypted()));
		this.setCrypto(Encryption.decrypt(this.getCryptoEncrypted()));
		this.setExpiryDate(Utils.toSqlDate(this.getExpiryDateSql()));

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gender getOwnerGender() {
		return ownerGender;
	}

	public void setOwnerGender(Gender ownerGender) {
		this.ownerGender = ownerGender;
	}

	public String getOwnerFirstname() {
		return ownerFirstname;
	}

	public void setOwnerFirstname(String ownerFirstname) {
		this.ownerFirstname = ownerFirstname;
	}

	public String getOwnerLastname() {
		return ownerLastname;
	}

	public void setOwnerLastname(String ownerLastname) {
		this.ownerLastname = ownerLastname;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCrypto() {
		return crypto;
	}

	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}

	public boolean getIsValid() {
		return isValid();
	}

	public boolean isValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.setValid(isValid);
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean getIsDeleted() {
		return this.isDeleted();
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.setDeleted(isDeleted);
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public byte[] getCardNumberEncrypted() {
		return cardNumberEncrypted;
	}

	public void setCardNumberEncrypted(byte[] cardNumberEncrypted) {
		this.cardNumberEncrypted = cardNumberEncrypted;
	}

	public java.sql.Date getExpiryDateSql() {
		return expiryDateSql;
	}

	public void setExpiryDateSql(java.sql.Date expiryDateSql) {
		this.expiryDateSql = expiryDateSql;
	}

	public byte[] getCryptoEncrypted() {
		return cryptoEncrypted;
	}

	public void setCryptoEncrypted(byte[] cryptoEncrypted) {
		this.cryptoEncrypted = cryptoEncrypted;
	}

	@Override
	public String toString() {
		return String.format("Id[%d] %s%s %s %s exp:%s, {%s}  %s %s", getId(),
				getOwnerGender().getId() > 2 ? "" : getOwnerGender().getTitle() + " ", getOwnerFirstname(),
				getOwnerLastname(), getCardNumber(), Utils.date2String(getExpiryDate(), "MM/yy"), getCrypto(),
				(isValid() ? "" : "non-") + "valide", (isDeleted() ? "" : "non-") + "effacée");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (user != null) {
			this.setOwnerFirstname(user.getFirstname());
			this.setOwnerLastname(user.getLastname());
		} else {
			this.setOwnerFirstname("***");
			this.setOwnerLastname("***");
		}

		this.user = user;
	}

}
