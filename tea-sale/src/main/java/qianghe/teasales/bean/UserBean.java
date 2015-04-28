package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import qianghe.teasales.model.User;
import qianghe.teasales.model.UserRole;
import qianghe.teasales.service.TeaSalesException;
import qianghe.teasales.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserService userService;
	
	private List<User> allUsers;
	
	private User user = new User();
	
	private UserRole[] userRoles = UserRole.values();
	
	@PostConstruct
	public void init() {
		loadAllUsers();
	}
	
	private void loadAllUsers() {
		allUsers = userService.getAllUsers();
	}
	
	public void addUser() {
		user = new User();
	}
	
	public void saveUser() {
		try {
			userService.saveUser(user);
			user = null;
			loadAllUsers();
			RequestContext.getCurrentInstance().execute("PF('userWidget').hide()");
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "保存用户出错"));
		}
	}
	
	public void deleteUser() {
		try {
			userService.deleteUser(user);
			user = null;
			loadAllUsers();
		} catch (TeaSalesException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCause().getMessage(), "删除用户出错"));
		}
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRole[] userRoles) {
		this.userRoles = userRoles;
	}
	
}
