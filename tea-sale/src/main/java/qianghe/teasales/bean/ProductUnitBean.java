package qianghe.teasales.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import qianghe.teasales.model.ProductUnit;

@Named
@ViewScoped
public class ProductUnitBean extends ProductAttributeBean<ProductUnit> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
		refreshProductAttrs();
	}
	
	@Override
	protected void refreshProductAttrs() {
		setProductAttrs(prodAttrService.getAllProductUnits());
	}
	
	@Override
	protected ProductUnit createProductAttribute(String name) {
		ProductUnit prodUnit = new ProductUnit();
		prodUnit.setName(name);
		return prodUnit;
	}
}
