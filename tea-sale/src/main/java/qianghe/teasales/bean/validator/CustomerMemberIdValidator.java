package qianghe.teasales.bean.validator;

import qianghe.teasales.service.CustomerService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Qiang He on 02/07/2015.
 */
@FacesValidator(value="customerMemberIdValidator")
public class CustomerMemberIdValidator implements Validator {

    CustomerService customerService;

    public CustomerMemberIdValidator() throws NamingException {
        super();
        InitialContext ic = new InitialContext();
        customerService = (CustomerService) ic.lookup("java:module/CustomerService");
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String customerMemberId = (String) value;
        if (customerService.existCustomerMemberId(customerMemberId)) {
            FacesMessage msg = new FacesMessage("会员编号错误", "会员编号已经被使用过了。");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
