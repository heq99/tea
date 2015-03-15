package qianghe.teasales.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import qianghe.teasales.model.ProductAttribute;
import qianghe.teasales.service.ProductAttributeService;

public abstract class ProductAttributeBean<T extends ProductAttribute> {
	
	@Inject
	protected ProductAttributeService prodAttrService;
	
	private List<T> productAttrs;
	
	private T selectedProdAttr;
	
	private String prodAttr;
	
	private String action;
	
	abstract protected void refreshProductAttrs();
	
	abstract protected T createProductAttribute(String name);

	public void addProductAttribute() {
		action = "Add";
		prodAttr = "";
		RequestContext.getCurrentInstance().execute("PF('prodAttrDlg').show()");
	}
	
	public void editProductAttribute() {
		action = "Edit";
		prodAttr = selectedProdAttr.getName();
		RequestContext.getCurrentInstance().execute("PF('prodAttrDlg').show()");
	}
	
	private boolean save() {
		if (!prodAttrService.existPoductLevelName(getProdAttr())) {
			if ("Add".equalsIgnoreCase(getAction())) {
				prodAttrService.saveProductAttribute(createProductAttribute(getProdAttr()));
			} else if ("Edit".equalsIgnoreCase(getAction())) {
				getSelectedProdAttr().setName(getProdAttr());
				prodAttrService.saveProductAttribute(getSelectedProdAttr());
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误", "无效的操作！"));
				return false;
			}
			refreshProductAttrs();
			return true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误", "输入的值已经存在！"));
			return false;
		}
	}

	public void saveProdAttr() {
		if(save()) {
			action = "";
			prodAttr = "";
			selectedProdAttr = null;
			RequestContext.getCurrentInstance().execute("PF('prodAttrDlg').hide()");
		}
	}
	
	public void cancel() {
		action = "";
		prodAttr = "";
		selectedProdAttr = null;
		RequestContext.getCurrentInstance().execute("PF('prodAttrDlg').hide()");
	}
	
	public void deleteProductAttribute() {
		if (this.getSelectedProdAttr() != null) {
			prodAttrService.deleteProductAttribute(getSelectedProdAttr());
			setProdAttr("");
			setSelectedProdAttr(null);
			refreshProductAttrs();
		}
	}
	
	public ProductAttributeService getProdAttrService() {
		return prodAttrService;
	}

	public void setProdAttrService(ProductAttributeService prodAttrService) {
		this.prodAttrService = prodAttrService;
	}

	public List<T> getProductAttrs() {
		return productAttrs;
	}

	public void setProductAttrs(List<T> productAttrs) {
		this.productAttrs = productAttrs;
	}

	public T getSelectedProdAttr() {
		return selectedProdAttr;
	}

	public void setSelectedProdAttr(T selectedProdAttr) {
		this.selectedProdAttr = selectedProdAttr;
	}

	public String getProdAttr() {
		return prodAttr;
	}

	public void setProdAttr(String prodAttr) {
		this.prodAttr = prodAttr;
	}
	
	public void onRowSelected() {
		setProdAttr(getSelectedProdAttr().getName());
	}

	public void onRowUnselected() {
		setProdAttr("");
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
