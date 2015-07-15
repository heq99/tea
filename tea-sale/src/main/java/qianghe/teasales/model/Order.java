package qianghe.teasales.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 
 * @author Qiang He
 *
 */
@Entity
@Table(name = "SALE_ORDER")
@NamedQueries({
	@NamedQuery(name = "Order.getAllOrders", query = "SELECT o FROM Order o"),
	@NamedQuery(name = "Order.getOrdersFromSeller", query = "SELECT o FROM Order o WHERE o.seller.id = :sellerId"),
	@NamedQuery(name = "Order.getOrdersFromSellerByDates", query = "SELECT o FROM Order o WHERE o.seller.id = :sellerId "
			+ "AND o.placementDate BETWEEN :startDate AND :endDate"),
	@NamedQuery(name = "Order.getOrdersFromCustomer", query = "SELECT o FROM Order o WHERE o.customer.id = :customerId"),
	@NamedQuery(name = "Order.getOrdersFromCustomerByDates", query = "SELECT o FROM Order o WHERE o.customer.id = :customerId "
			+ "AND o.placementDate BETWEEN :startDate AND :endDate")
})
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ORDER_NUMBER", length = 20)
	private String orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	@Column(name = "SALE_CHANNEL", length = 40)
	private String saleChannel;
	
	@Column(name = "CONTACT_TEL_NO", length = 20)
	private String contactTelNo;
	
	@Column(name = "INVOICE_REQUIRED")
	private Boolean invoiceRequired;
	
	@Column(name = "CUSTOMER_COMMENT", length = 1024)
	private String customerComment;
	
	@Column(name = "PAYMENT_METHOD", length = 50)
	private String paymentMethod;
	
	@Column(name = "DISPATCH_NUMBER", length = 20)
	private String dispatchNumber;
	
	@Column(name = "DISPATCH_FEE")
	private BigDecimal dispatchFee;
	
	@Column(name = "TAX")
	private BigDecimal tax;
	
	@Column(name = "DELIVERY_ADDRESS", length = 1024)
	private String deliveryAddress;
	
	@Column(name = "STUB_NUMBER", length = 20)
	private String stubNumber;
	
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;
	
	@Column(name = "ACTUAL_AMOUNT")
	private BigDecimal actualAmount;
	
	@ManyToOne
	@JoinColumn(name = "SELLER")
	private User seller;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PLACEMENT_DATE")
	private Date placementDate; 

	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	
	@Column(name = "ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	public void recalculateOrder() {
		if (getOrderItems() != null) {
			BigDecimal totalOrderAmount = new BigDecimal("0");
			BigDecimal actualOrderAmount = new BigDecimal("0");
			for (OrderItem orderItem : getOrderItems()) {
				totalOrderAmount = totalOrderAmount.add(orderItem.getTotalAmount());
				actualOrderAmount = actualOrderAmount.add(orderItem.getActualAmount());
			}
			setTotalAmount(totalOrderAmount);
			setActualAmount(actualOrderAmount);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public String getContactTelNo() {
		return contactTelNo;
	}

	public void setContactTelNo(String contactTelNo) {
		this.contactTelNo = contactTelNo;
	}

	public Boolean getInvoiceRequired() {
		return invoiceRequired;
	}

	public void setInvoiceRequired(Boolean invoiceRequired) {
		this.invoiceRequired = invoiceRequired;
	}

	public String getCustomerComment() {
		return customerComment;
	}

	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public BigDecimal getDispatchFee() {
		return dispatchFee;
	}

	public void setDispatchFee(BigDecimal dispatchFee) {
		this.dispatchFee = dispatchFee;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getStubNumber() {
		return stubNumber;
	}

	public void setStubNumber(String stubNumber) {
		this.stubNumber = stubNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Date getPlacementDate() {
		return placementDate;
	}

	public void setPlacementDate(Date placementDate) {
		this.placementDate = placementDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductSummary() {
		String productSummary = "";
		for (OrderItem orderItem : getOrderItems()) {
			if (productSummary.length() < 20) {
				productSummary += orderItem.getProduct().getShortName();
			} else {
				productSummary += "...";
				break;
			}
		}
		return productSummary;
	}
}
