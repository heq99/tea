package qianghe.teasales.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import qianghe.teasales.model.Customer;
import qianghe.teasales.model.Order;
import qianghe.teasales.model.User;

@Stateless
public class OrderDao extends AbstractDao {

	public List<Order> getAllOrders() {
		return getEntityManager().createNamedQuery("Order.getAllOrders", Order.class).getResultList();
	}
	
	public List<Order> getOrdersFromSeller(User seller) {
		return getEntityManager().createNamedQuery("Order.getOrdersFromSeller", Order.class)
				.setParameter("sellerId", seller.getId())
				.getResultList();
	}
	
	public List<Order> getOrdersFromSellerByDates(User seller, Date startDate, Date endDate) {
		return getEntityManager().createNamedQuery("Order.getOrdersFromSellerByDates", Order.class)
				.setParameter("sellerId", seller.getId())
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate)
				.getResultList();
	}
	
	public List<Order> getOrdersFromCustomer(Customer customer) {
		return getEntityManager().createNamedQuery("Order.getOrdersFromCustomer", Order.class)
				.setParameter("customerId", customer.getId())
				.getResultList();
	}
	
	public List<Order> getOrdersFromCustomerByDates(Customer customer, Date startDate, Date endDate) {
		return getEntityManager().createNamedQuery("Order.getOrdersFromCustomerByDates", Order.class)
				.setParameter("customerId", customer.getId())
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate)
				.getResultList();
	}
	
	public void saveOrder(Order order) {
		EntityManager em = getEntityManager();
		if (order.getId() != null) {
			order = em.merge(order);
		}
		em.persist(order);
	}
	
	public void deleteOrder(Order order) {
		EntityManager em = getEntityManager();
		order = em.merge(order);
		em.remove(order);
	}
}
