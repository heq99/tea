package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import qianghe.teasales.model.Customer;

@Stateless
public class CustomerDao extends AbstractDao {

	public List<Customer> getAllCustomer() {
		return getEntityManager().createNamedQuery("Customer.getAllCustomers", Customer.class).getResultList();
	}
	
	public Customer getCustomerById(Long id) {
		return getEntityManager().find(Customer.class, id);
	}
	
	public Customer getCustomerByMemberId(String memberId) {
		try {
			return getEntityManager().createNamedQuery("Customer.getCustomerByMemberId", Customer.class)
					.setParameter("memberId", memberId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Customer> searchCustomer(String name, String telNo, String email) {
		return getEntityManager().createNamedQuery("Customer.searchCustomer", Customer.class)
				.setParameter("name", name)
				.setParameter("phoneNo", telNo)
				.setParameter("email", email)
				.getResultList();
	}
	
	public void saveCustomer(Customer customer) {
		EntityManager em = getEntityManager();
		if (customer.getId() != null) {
			customer = em.merge(customer);
		}
		em.persist(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		EntityManager em = getEntityManager();
		customer = em.merge(customer);
		em.remove(customer);
	}
}
