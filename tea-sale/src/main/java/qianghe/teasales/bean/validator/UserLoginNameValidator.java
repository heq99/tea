package qianghe.teasales.bean.validator;

import org.apache.commons.lang3.StringUtils;
import qianghe.teasales.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Qiang He on 2015/6/14.
 */
@FacesValidator(value="userLoginNameValidator")
public class UserLoginNameValidator implements Validator {

    UserService userService;

    public UserLoginNameValidator() throws NamingException {
        super();
        InitialContext ic = new InitialContext();
        userService = (UserService) ic.lookup("java:module/UserService");
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String loginName = (String) value;
        if (userService.existUserLogin(loginName)) {
            FacesMessage msg = new FacesMessage("登录名错误", "登录名已经被使用过了。");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
