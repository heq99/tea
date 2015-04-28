package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import qianghe.teasales.model.Customer;
import qianghe.teasales.model.Order;
import qianghe.teasales.model.OrderItem;
import qianghe.teasales.service.CustomerService;
import qianghe.teasales.service.OrderService;
import qianghe.teasales.service.TeaSalesException;

@Named
@ViewScoped
public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserSessionBean userSessionBean;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private CustomerService customerService;
	
	private String custSearchText;
	
	private Customer customer;
	
	private Order order;
	
	private OrderItem orderItem;

	public List<Customer> searchCustomers() {
		try {
			return customerService.searchCustomer(custSearchText);
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("搜索客户时出错！"));
			return new ArrayList<Customer>();
		}
	}
	
	public String getCustSearchText() {
		return custSearchText;
	}

	public void setCustSearchText(String custSearchText) {
		this.custSearchText = custSearchText;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
