package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import qianghe.teasales.model.Product;
import qianghe.teasales.model.ProductLevel;
import qianghe.teasales.service.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductService productService;
	
	private List<String> distinctProductNames;
	
	private String selectedProductName;
	
	private Product newProduct;
	
	@PostConstruct
	public void init() {
		distinctProductNames = productService.getDistinctProductNames();
		newProduct = new Product();
		newProduct.setProductLevel(new ProductLevel());
	}
	
	public void addProduct() {
		newProduct = new Product();
		newProduct.setShortName("Test");
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('newProdDlg').show();");
	}
	
	public void cancelAddingProduct() {
		newProduct = null;
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('newProdDlg').hide();");
	}
	
	public void saveProduct() {
		
	}
	
	public void deleteProduct() {
		
	}

	public List<String> getDistinctProductNames() {
		return distinctProductNames;
	}

	public void setDistinctProductNames(List<String> distinctProductNames) {
		this.distinctProductNames = distinctProductNames;
	}

	public String getSelectedProductName() {
		return selectedProductName;
	}

	public void setSelectedProductName(String selectedProductName) {
		this.selectedProductName = selectedProductName;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}
		
}
