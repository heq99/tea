package qianghe.teasales.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import qianghe.teasales.model.ProductLevel;
import qianghe.teasales.service.ProductAttributeService;

@FacesConverter(value="productLevelConverter")
public class ProductLevelConverter implements Converter {

	ProductAttributeService prodAttrService;
	
	public ProductLevelConverter() throws NamingException {
		super();
		InitialContext ic = new InitialContext();
		prodAttrService = (ProductAttributeService) ic.lookup("java:module/ProductAttributeService");
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return prodAttrService.getProductLevelByName(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if ("".equalsIgnoreCase(value.toString())) {
			return "";
		} else {
			ProductLevel prodLevel = (ProductLevel) value;
			return prodLevel.getName();
		}
	}

}
