package qianghe.teasales.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import qianghe.teasales.model.ProductSpec;
import qianghe.teasales.service.ProductAttributeService;

@FacesConverter(value="productSpecConverter")
public class ProductSpecConverter implements Converter {

	ProductAttributeService prodAttrService;
	
	public ProductSpecConverter() throws NamingException {
		super();
		InitialContext ic = new InitialContext();
		prodAttrService = (ProductAttributeService) ic.lookup("java:module/ProductAttributeService");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return prodAttrService.getProductSpecByName(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if ("".equalsIgnoreCase(value.toString())) {
			return "";
		} else {
			ProductSpec prodSpec = (ProductSpec) value;
			return prodSpec.getName();
		}
	}
}
