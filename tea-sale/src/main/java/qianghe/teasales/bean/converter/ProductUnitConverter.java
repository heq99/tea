package qianghe.teasales.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import qianghe.teasales.model.ProductUnit;
import qianghe.teasales.service.ProductAttributeService;

@FacesConverter(value="productUnitConverter")
public class ProductUnitConverter implements Converter {
	
	ProductAttributeService prodAttrService;
	
	public ProductUnitConverter() throws NamingException {
		super();
		InitialContext ic = new InitialContext();
		prodAttrService = (ProductAttributeService) ic.lookup("java:module/ProductAttributeService");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return prodAttrService.getProductUnitByName(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if ("".equalsIgnoreCase(value.toString())) {
			return null;
		} else {
			ProductUnit prodUnit = (ProductUnit) value;
			return prodUnit.getName();
		}
	}

}
