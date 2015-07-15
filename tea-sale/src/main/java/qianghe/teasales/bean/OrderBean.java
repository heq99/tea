package qianghe.teasales.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import qianghe.teasales.model.*;
import qianghe.teasales.service.CustomerService;
import qianghe.teasales.service.OrderService;
import qianghe.teasales.service.ProductService;
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

	@Inject
    private ProductService productService;

	@Inject
    private CustomerBean customerBean;
	
	private Customer customer;
	
	private Order order;
	
	private OrderItem orderItem;

    private List<String> allProductNames;

    private String selectedProductName;

    private List<Product> productsWithName;

    @PostConstruct
    public void init() {
        customer = new Customer();
        order = new Order();
        order.setOrderItems(new ArrayList<OrderItem>());
        orderItem = new OrderItem();
        allProductNames = productService.getDistinctProductNames();
    }

    /**
     * Get the selected customer from the <code>CustomerBean</code>
     */
	public void selectCustomer() {
        customer = customerBean.getCustomer();
    }

	public void addCustomer() {
        customer = new Customer();
    }

	public void saveAndSelectCustomer() {
        try {
            customerService.saveCustomer(customer);
        } catch (TeaSalesException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("不能增加新客户！"));
        }
	}

    public void addOrderItem() {
        orderItem = new OrderItem();
        selectedProductName = null;
        productsWithName = null;
    }

    public void editOrderItem() {
        selectedProductName = orderItem.getProduct().getShortName();
        productNameSelected();
    }

    public void deleteOrderItem() {
        order.getOrderItems().remove(orderItem);
    }

    public void saveOrderItem() {
        OrderItem existingOrderItem = null;
        for (OrderItem orderItemInOrder : order.getOrderItems()) {
            if (orderItemInOrder.getProduct().getId() == orderItem.getProduct().getId()) {
                existingOrderItem = orderItemInOrder;
                break;
            }
        }
        if (existingOrderItem == null) {
            order.getOrderItems().add(orderItem);
        }
        orderItem.setTotalAmount(orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
        orderItem.setPrice(orderItem.getProduct().getPrice());
        orderItem.setOrder(order);
    }

    public void productNameSelected() {
        productsWithName = productService.getProductsByName(selectedProductName);
    }

    public String saveOrder() {
        order.setSeller(userSessionBean.getUser());
        order.setOrderStatus(OrderStatus.ORDER);
        order.setPlacementDate(new Date());

        BigDecimal actualAmount = new BigDecimal("0");
        BigDecimal totalAmount = new BigDecimal("0");
        for (OrderItem orderItemInOrder : order.getOrderItems()) {
            totalAmount.add(orderItemInOrder.getTotalAmount());
            if (orderItemInOrder.getActualAmount() == null) {
                actualAmount.add(orderItemInOrder.getTotalAmount());
            } else {
                actualAmount.add(orderItemInOrder.getActualAmount());
            }
        }
        order.setActualAmount(actualAmount);
        order.setTotalAmount(totalAmount);

        order.setCustomer(customer);

        try {
            orderService.saveOrder(order);
        } catch (TeaSalesException e) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("报错订单时出错！"));
        }

        return "mainPage";
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

    public List<String> getAllProductNames() {
        return allProductNames;
    }

    public void setAllProductNames(List<String> allProductNames) {
        this.allProductNames = allProductNames;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public List<Product> getProductsWithName() {
        return productsWithName;
    }

    public void setProductsWithName(List<Product> productsWithName) {
        this.productsWithName = productsWithName;
    }

}
