package qianghe.teasales.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import qianghe.teasales.model.Product;
import qianghe.teasales.model.ProductLevel;
import qianghe.teasales.model.ProductSpec;
import qianghe.teasales.model.ProductUnit;
import qianghe.teasales.service.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductService productService;
	
	private List<String> distinctProductNames;
	
	private String selectedProductName;
	
	private List<Product> productsWithName;
	
	private Product selectedProduct;

	private String productNumber;
	private String productShortName;
	private String productLongName;
	private ProductLevel productLevel;
	private ProductUnit productUnit;
	private ProductSpec productSpec;
	private BigDecimal productPrice;
	private Boolean productActiveStatus;
	
	private String action;
	
	private void clearProductData() {
		productNumber = "";
		productShortName = "";
		productLongName = "";
		productLevel = null;
		productUnit = null;
		productSpec = null;
		productPrice = null;
	}
	
	private void refreshDistinctProductNames() {
		distinctProductNames = productService.getDistinctProductNames();
	}
	
	private void refreshProductsWithName() {
		productsWithName = productService.getProductsByName(selectedProductName);
	}
	
	@PostConstruct
	public void init() {
		refreshDistinctProductNames();
		clearProductData();
		action = "";
	}
	
	public void addProduct() {
		clearProductData();
		action = "Add";
		if (StringUtils.isNotEmpty(selectedProductName)) {
			productShortName = selectedProductName;
		}
		RequestContext.getCurrentInstance().execute("PF('prodDlg').show();");
	}
	
	public void editProduct() {
		action = "Edit";
		productNumber = selectedProduct.getProductNumber();
		productShortName = selectedProduct.getShortName();
		productLongName = selectedProduct.getLongName();
		productLevel = selectedProduct.getProductLevel();
		productUnit = selectedProduct.getProductUnit();
		productSpec = selectedProduct.getProductSpec();
		productPrice = selectedProduct.getPrice();
		RequestContext.getCurrentInstance().execute("PF('prodDlg').show();");
	}
	
	public void cancelProductForm() {
		clearProductData();
		action = "";
		RequestContext.getCurrentInstance().execute("PF('prodDlg').hide();");
	}
	
	private boolean save() {
		
		String levelName = productLevel != null ? productLevel.getName() : null;
		String unitName = productUnit != null ? productUnit.getName() : null;
		String specName = productSpec != null ? productSpec.getName() : null;
		
		if (!productService.existProduct(productShortName, levelName, unitName, specName)) {
			if ("Add".equalsIgnoreCase(action)) {
				Product product = new Product();
				product.setProductNumber(productNumber);
				product.setShortName(productShortName);
				product.setLongName(productLongName);
				product.setProductLevel(productLevel);
				product.setProductUnit(productUnit);
				product.setProductSpec(productSpec);
				product.setPrice(productPrice);
				product.setActive(productActiveStatus);
				
				productService.saveProduct(product);
				refreshProductsWithName();
				refreshDistinctProductNames();
			} else if ("Edit".equalsIgnoreCase(action)) {
				selectedProduct.setProductNumber(productNumber);
				selectedProduct.setShortName(productShortName);
				selectedProduct.setLongName(productLongName);
				selectedProduct.setProductLevel(productLevel);
				selectedProduct.setProductUnit(productUnit);
				selectedProduct.setProductSpec(productSpec);
				selectedProduct.setPrice(productPrice);
				selectedProduct.setActive(productActiveStatus);
				
				productService.saveProduct(selectedProduct);
				refreshProductsWithName();
				refreshDistinctProductNames();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误", "无效的操作！"));
				return false;
			}
			return true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误", "产品已经存在！"));
			return false;
		}
		
	}
	
	public void saveProduct() {
		if (save()) {
			action = "";
			selectedProduct = null;
			RequestContext.getCurrentInstance().execute("PF('prodDlg').hide();");
		}
	}
	
	public void deleteProduct() {
		productService.deleteProduct(selectedProduct);
		selectedProduct = null;
		refreshProductsWithName();
		refreshDistinctProductNames();
	}
	
	public void productNameSelected() {
		refreshProductsWithName();
	}
	
	public void productNameUnselected() {
		productsWithName = null;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public String getProductLongName() {
		return productLongName;
	}

	public void setProductLongName(String productLongName) {
		this.productLongName = productLongName;
	}

	public ProductLevel getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(ProductLevel productLevel) {
		this.productLevel = productLevel;
	}

	public ProductUnit getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(ProductUnit productUnit) {
		this.productUnit = productUnit;
	}

	public ProductSpec getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(ProductSpec productSpec) {
		this.productSpec = productSpec;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Boolean getProductActiveStatus() {
		return productActiveStatus;
	}

	public void setProductActiveStatus(Boolean productActiveStatus) {
		this.productActiveStatus = productActiveStatus;
	}

	public List<Product> getProductsWithName() {
		return productsWithName;
	}

	public void setProductsWithName(List<Product> productsWithName) {
		this.productsWithName = productsWithName;
	}
}
