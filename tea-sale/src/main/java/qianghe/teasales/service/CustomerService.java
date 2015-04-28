package qianghe.teasales.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.CustomerDao;
import qianghe.teasales.model.Customer;

@Stateless
public class CustomerService {

	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Inject
	private CustomerDao customerDao;
	
	public List<Customer> getAllCustomer() {
		logger.trace("Start to get all customers...");
		try {
			return customerDao.getAllCustomer();
		} finally {
			logger.trace("Finished to get all customers.");
		}
	}
	
	public Customer getCustomerById(Long id) {
		logger.trace("Start to get customer by id: " + id);
		try {
			return customerDao.getCustomerById(id);
		} finally {
			logger.trace("Finished to get customer by id: " + id);
		}
	}
	
	public boolean existCustomer(String memberId, String custName, String telNo, String email) {
		if (StringUtils.isNotEmpty(memberId)) {
			Customer cust = customerDao.getCustomerByMemberId(memberId);
			return cust != null;
		} else {
			
			if (StringUtils.isEmpty(custName)) {
				custName = "%"; 
			}
			if (StringUtils.isEmpty(telNo)) {
				telNo = "%";
			}
			if (StringUtils.isEmpty(email)) {
				email = "%";
			}
			
			List<Customer> customers = customerDao.searchCustomer(custName, telNo, email);
			return customers != null && customers.size() > 0;
					
		}
	}
	
	public void saveCustomer(Customer customer) throws TeaSalesException {
		logger.trace("Start to save customer: " + customer.getName());
		try {
			if (customer.getId() == null) {
				if (existCustomer(customer.getMemberId(), customer.getName(), customer.getPhoneNumber(), customer.getEmail())) {
					throw new TeaSalesException("用户名，联系电话，或者电子邮件已经被别人用了。");
				}
			}
			try {
				customerDao.saveCustomer(customer);
			} catch (Exception e) {
				throw new TeaSalesException("报错客户时出错。", e);
			}
		} finally {
			logger.trace("Finished to save customer: " + customer.getName());
		}
	}
	
	public void deleteCustomer(Customer customer) throws TeaSalesException {
		logger.trace("Start to delete customer: " + customer.getName());
		try {
			customerDao.deleteCustomer(customer);
		} catch (Exception e) {
			throw new TeaSalesException("删除客户的时候出错.", e);
		} finally {
			logger.trace("Finished to delete customer: " + customer.getName());
		}
	}
	
	public List<Customer> searchCustomer(String searchText) throws TeaSalesException {
		logger.trace("Start to search customer: searchText = " + searchText);
		try {
			return customerDao.searchCustomer(searchText, searchText, searchText);
		} catch (Exception e) {
			throw new TeaSalesException("搜索客户时出错.", e);
		} finally {
			logger.trace("Finished to search cusotmer: searchText = " + searchText);
		}
	}
}
