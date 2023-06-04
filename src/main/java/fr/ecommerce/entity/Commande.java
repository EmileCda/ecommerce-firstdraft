package fr.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import fr.ecommerce.common.IConstant;

import fr.ecommerce.utils.Utils;

@Entity
@Table(name = "commande")
public class Commande implements IConstant, Serializable {

	

	private static final long serialVersionUID = 446226188523864329L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Column(name = "order_number")
	private String orderNumber;

	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "total_discount")
	private float totalDiscount; // in value (not in%)
	@Column(name = "shipping_costs")
	private float shippingCosts;
	@Column(name = "grand_total")
	private float grandTotal;

	@OneToOne
	@JoinColumn(name = "adresse_id", nullable = false, insertable = false, updatable = false)
	private Adresse deliveryAddress;

	@OneToOne
	@JoinColumn(name = "adresse_id", nullable = false, insertable = false, updatable = false)
	private Adresse billingAddress;

	@OneToOne
	@JoinColumn(name = "carte_paiement_id", nullable = false, insertable = false, updatable = false)
	private CartePaiement bankCardUsed;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
	private List<LigneDeCommande> orderLineList;

	public Commande() {
		this(DEFAULT_ID, DEFAULT_ORDER_NUMBER, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_FLOAT_VALUE, DEFAULT_FLOAT_VALUE,
				DEFAULT_FLOAT_VALUE, null, null, null, null);
	}

	public Commande(String orderNumber, Date deliveryDate, float totalDiscount, float shippingCosts, float grandTotal) {

		this(DEFAULT_ID, orderNumber, DATE_NOW, deliveryDate, totalDiscount, shippingCosts, grandTotal, null, null,
				null, null);

	}

	public Commande(int id, String orderNumber, Date createDate, Date deliveryDate, float totalDiscount,
			float shippingCosts, float grandTotal, Adresse deliveryAddress, Adresse billingAddress,
			CartePaiement bankCardUsed, User user) {
		this.setId(id);
		this.setOrderNumber(orderNumber);
		this.setCreateDate(createDate);
		this.setDeliveryDate(deliveryDate);
		this.setTotalDiscount(totalDiscount);
		this.setShippingCosts(shippingCosts);
		this.setGrandTotal(grandTotal);
		this.setDeliveryAddress(deliveryAddress);
		this.setBillingAddress(billingAddress);
		this.setBankCardUsed(bankCardUsed);
		this.setUser(user);

		if (this.getOrderLineList() == null)
			this.setOrderLineList(new ArrayList<LigneDeCommande>());

	}

	public void addOrderLine(LigneDeCommande orderLine) {
		initOrderLineList();
		this.getOrderLineList().add(orderLine);

	}

	public void initOrderLineList() {
		if (this.getOrderLineList() == null) {
			this.setOrderLineList(new ArrayList<LigneDeCommande>());
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public float getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public float getShippingCosts() {
		return shippingCosts;
	}

	public void setShippingCosts(float shippingCosts) {
		this.shippingCosts = shippingCosts;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Adresse getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Adresse deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Adresse getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Adresse billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CartePaiement getBankCardUsed() {
		return bankCardUsed;
	}

	public void setBankCardUsed(CartePaiement bankCardUsed) {
		this.bankCardUsed = bankCardUsed;
	}

	public List<LigneDeCommande> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<LigneDeCommande> orderLineList) {
		this.orderLineList = orderLineList;
	}

	public User getUser() {
		return user;
	}
//-------------------------------------------------------------------------------------------------
	public void setUser(User user) {
		if (user != null) {
			if (user.getBankCardList().size() > 0) {
				this.setBankCardUsed(user.getBankCardList().get(1));
			}
			if (user.getAddressList().size() > 0) {
				this.setDeliveryAddress(user.getAddressList().get(1));
			}
			if (user.getAddressList().size() > 0) {
				this.setBillingAddress(user.getAddressList().get(1)); // could be differents address
			}
		}

		this.user = user;
	}

	
	@Override
	public String toString() {
		String deliveryAddress = "no-delivery-address";
		String billingAddress = "no-billing-address";
		if (getDeliveryAddress() != null)
			deliveryAddress = getDeliveryAddress().toString();
		if (getBillingAddress() != null)
			billingAddress = getBillingAddress().toString();

		return String.format(
				"Id[%d], %s, crea:%s, livraison le:%s, " + "réduction: -%.2f€,  frais de port(): %.2f€, Total: %.2f€ "
						+ "livraison: %s, facture: %s",
				getId(), getOrderNumber(), Utils.date2String(getCreateDate()), Utils.date2String(getDeliveryDate()),
				getTotalDiscount(), getShippingCosts(), getGrandTotal(), deliveryAddress, billingAddress);
	}


}
