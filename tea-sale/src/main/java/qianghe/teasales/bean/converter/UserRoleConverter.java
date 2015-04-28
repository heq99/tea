package qianghe.teasales.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import qianghe.teasales.model.UserRole;

@FacesConverter(value="userRoleConverter")
public class UserRoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return UserRole.valueOf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if ("".equalsIgnoreCase(value.toString())) {
			return "";
		} else {
			UserRole userRole = (UserRole) value;
			return userRole.name();
		}
	}
}
