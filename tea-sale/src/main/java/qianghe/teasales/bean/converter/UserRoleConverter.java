package qianghe.teasales.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import qianghe.teasales.model.UserRole;

@FacesConverter(value="userRoleConverter")
public class UserRoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			return UserRole.valueOf(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value==null || "".equalsIgnoreCase(value.toString())) {
			return "";
		} else {
			UserRole userRole = (UserRole) value;
			return userRole.name();
		}
	}
}
