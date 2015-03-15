package qianghe.teasales.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import qianghe.teasales.model.ProductSpec;

@Named
@ViewScoped
public class ProductSpecBean extends ProductAttributeBean<ProductSpec> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
		refreshProductAttrs();
	}
	
	@Override
	protected void refreshProductAttrs() {
		setProductAttrs(prodAttrService.getAllProductSpecs());
	}
	
	@Override
	protected ProductSpec createProductAttribute(String name) {
		ProductSpec prodSpec = new ProductSpec();
		prodSpec.setName(name);
		return prodSpec;
	}

}
