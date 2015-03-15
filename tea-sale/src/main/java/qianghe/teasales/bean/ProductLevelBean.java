package qianghe.teasales.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import qianghe.teasales.model.ProductLevel;

@Named
@ViewScoped
public class ProductLevelBean extends ProductAttributeBean<ProductLevel> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		refreshProductAttrs();
	}
	
	@Override
	protected void refreshProductAttrs() {
		setProductAttrs(prodAttrService.getAllProductLevels());
	}
	
	@Override
	protected ProductLevel createProductAttribute(String name) {
		ProductLevel prodLevel = new ProductLevel();
		prodLevel.setName(name);
		return prodLevel;
	}
	
}
