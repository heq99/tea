package qianghe.teasales.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private User user;

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
        userService.saveUser(user);
        loadAllUsers();
    }

    public void deleteUser() {
        userService.deleteUser(user);
        user = null;
        loadAllUsers();
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
