package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import qianghe.teasales.manager.ProductService;
import qianghe.teasales.model.Product;
import qianghe.teasales.model.ProductLevel;

@Named
@ViewScoped
public class ProductBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductService productService;
	
	private List<ProductLevel> productLevels;
	
	private ProductLevel productLevel;
	
	private String productLevelName;
	
	private List<String> distinctProductNames;
	
	private String selectedProductName;
	
	private Product newProduct;
	
	@PostConstruct
	public void init() {
		refreshProductLevels();
		productLevel = new ProductLevel();
		distinctProductNames = productService.getDistinctProductNames();
		newProduct = new Product();
		newProduct.setProductLevel(new ProductLevel());
	}
	
	private void refreshProductLevels() {
		productLevels = productService.getAllProductLevels();
	}
	
	public void manageProductLevels() {
		refreshProductLevels();
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('prodLevelDlg').show();");
	}
	
	public void addProductLevel() {
		if (StringUtils.isNotEmpty(productLevelName)) {
			if (!productService.existPoductLevelName(productLevelName)) {
				ProductLevel prodLevel = new ProductLevel();
				prodLevel.setName(productLevelName);
				productService.saveProductLevel(prodLevel);
			}
		}
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


	
	
	public List<ProductLevel> getProductLevels() {
		return productLevels;
	}

	public void setProductLevels(List<ProductLevel> productLevels) {
		this.productLevels = productLevels;
	}

	public ProductLevel getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(ProductLevel productLevel) {
		this.productLevel = productLevel;
	}

	public String getProductLevelName() {
		return productLevelName;
	}

	public void setProductLevelName(String productLevelName) {
		this.productLevelName = productLevelName;
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
