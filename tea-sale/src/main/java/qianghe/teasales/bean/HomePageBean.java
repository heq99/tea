package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import qianghe.teasales.model.Customer;
import qianghe.teasales.model.Order;
import qianghe.teasales.model.OrderItem;
import qianghe.teasales.model.OrderStatus;
import qianghe.teasales.service.OrderService;
import qianghe.teasales.service.TeaSalesException;

@Named
@ViewScoped
public class HomePageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserSessionBean userSessionBean;

	@Inject
	private OrderService orderService;
	
	private List<Order> orders;
	
	private Order order = null;
	
	private OrderItem orderItem = null;
	
	@PostConstruct
	public void init() {
		loadOrders();
		order = new Order();
		order.setOrderItems(new ArrayList<OrderItem>());
	}
	
	private void loadOrders() {
		orders = orderService.getOrdersForSeller(userSessionBean.getUser());
	}

	public void addOrder() {
		order = new Order();
		order.setCustomer(new Customer());
		order.setOrderItems(new ArrayList<OrderItem>());
		order.setOrderStatus(OrderStatus.ORDER);
		order.setPlacementDate(new Date());
		order.setSeller(userSessionBean.getUser());
	}
	
	public void saveUser() {
		try {
			orderService.saveOrder(order);
			order = null;
			loadOrders();
			RequestContext.getCurrentInstance().execute("PF('orderWidget').hide()");
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "保存销售单出错"));
		}
	}

	public void deleteOrder() {
		try {
			orderService.deleteOrder(order, userSessionBean.getUser());
			order = null;
			loadOrders();
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "删除销售单出错"));
		}
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
