package qianghe.teasales.bean;

import java.io.Serializable;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import qianghe.teasales.model.User;
import qianghe.teasales.service.UserService;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	private User user;
	
	private String username;
	
	private String password;
	
	public String login() {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误", "用户名或密码不能为空！"));
			return "login";
		} else {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			try {
				request.login(username, password);
			} catch (ServletException e) {
				FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误", "没有对应的用户名和密码！"));
				return "login";
			}
			Principal principal = request.getUserPrincipal();
			// user = userService.loginUser(username, password);
			user = userService.getUserByLogin(principal.getName());
			return "mainPage";
		}
	}
	
	public String logout() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "mainPage";
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
