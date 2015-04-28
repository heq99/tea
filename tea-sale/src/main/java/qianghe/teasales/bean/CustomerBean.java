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
	
	public void saveCustomer() {
		try {
			customerService.saveCustomer(customer);
			customer = null;
			loadAllCustomers();
			RequestContext.getCurrentInstance().execute("PF('customerWidget').hide()");
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "保存客户出错"));
		}
	}
	
	public void deleteCustomer() {
		try {
			customerService.deleteCustomer(customer);
			customer = null;
			loadAllCustomers();
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "删除客户出错"));
		}
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
}
