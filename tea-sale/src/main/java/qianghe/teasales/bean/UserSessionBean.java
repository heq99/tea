package qianghe.teasales.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import qianghe.teasales.manager.UserManager;
import qianghe.teasales.model.User;

@ManagedBean
@SessionScoped
public class UserSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserManager userManager;

	private User user = new User();
	
	private String username;
	
	private String password;
	
	public String login() {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误", "用户名和密码不能为空！"));
			return "login";
		} else {
			user = userManager.loginUser(username, password);
			if (user == null) {
				FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误", "找不到对应的用户名和密码！"));
				return "login";
			} else {
				return "mainPage?faces-redirect=true";
			}
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=ture";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
