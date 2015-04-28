package qianghe.teasales.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.OrderDao;
import qianghe.teasales.model.Order;
import qianghe.teasales.model.User;
import qianghe.teasales.model.UserRole;

@Stateless
public class OrderService {

	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Inject
	OrderDao  orderDao;
	
	public List<Order> getOrdersForSeller(User seller) {
		logger.trace("Start to get all orders from seller: " + seller.getLogin());
		try {
			if (UserRole.ADMIN.equals(seller.getUserRole())) {
				logger.trace("Seller has ADMIN role, therefore return all orders");
				return orderDao.getAllOrders();
			} else {
				return orderDao.getOrdersFromSeller(seller);
			}
		} finally {
			logger.trace("Finished to get all orders from seller: " + seller.getLogin());
		}
	}
	
	public void saveOrder(Order order) throws TeaSalesException {
		logger.trace("Start to save order: " + order.getOrderNumber());
		try {
			orderDao.saveOrder(order);
		} catch (Exception e) {
			throw new TeaSalesException("保存销售单时出错。", e);
		} finally {
			logger.trace("Finished to save order: " + order.getOrderNumber());
		}
	}
	
	public void deleteOrder(Order order, User user) throws TeaSalesException {
		logger.trace("Start to delete order: " + order.getOrderNumber());
		if (UserRole.ADMIN.equals(user.getUserRole()) || UserRole.ACCOUNTANT.equals(user.getUserRole())) {
			try {
				orderDao.deleteOrder(order);
			} catch (Exception e) {
				throw new TeaSalesException("Can't delete order!", e);
			} finally {
				logger.trace("Finished to delete order: " + order.getOrderNumber());
			}
		} else {
			throw new TeaSalesException("没有删除销售单的权限。");
		}
	}
}
