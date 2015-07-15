package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import qianghe.teasales.model.Customer;
import qianghe.teasales.service.CustomerService;
import qianghe.teasales.service.TeaSalesException;

@Named
@ViewScoped
public class CustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;
	
	private List<Customer> allCustomers;
	
	private Customer customer;

	private String custSearchText;

	@PostConstruct
	public void init() {
		loadAllCustomers();
	}

	private void loadAllCustomers() {
		allCustomers = customerService.getAllCustomer();
	}
	
	public void addCustomer() {
		customer = new Customer();
	}

	public void searchCustomers() {
			allCustomers = customerService.searchCustomer(custSearchText);
	}

	public void saveCustomer() throws TeaSalesException {
		customerService.saveCustomer(customer);
		loadAllCustomers();
	}
	
	public void deleteCustomer() {
		customerService.deleteCustomer(customer);
		customer = null;
		loadAllCustomers();
	}

	public List<Customer> getAllCustomers() {
		return allCustomers;
	}

	public void setAllCustomers(List<Customer> allCustomers) {
		this.allCustomers = allCustomers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustSearchText() {
		return custSearchText;
	}

	public void setCustSearchText(String custSearchText) {
		this.custSearchText = custSearchText;
	}
}
