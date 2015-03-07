package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import qianghe.teasales.model.User;
import qianghe.teasales.model.UserRole;

@Stateless
public class UserDao extends AbstractDao {
	
	public User loginUser(String username, String password) throws NoResultException {
		List<User> users = getEntityManager().createNamedQuery("loginUser", User.class)
			.setParameter("username", username)
			.setParameter("password", password)
			.getResultList();
		if (users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * This method is risky, it needs to be removed. 
	 */
	public void checkOrCreateAdminUser() {
		EntityManager em = getEntityManager();
		List<User> users = em.createQuery("SELECT u FROM User u WHERE u.login=:adminName", User.class)
				.setParameter("adminName", "admin").getResultList();
		if (users != null && users.size() == 1) {
			return;
		} else {
			User user = new User();
			user.setLogin("admin");
			user.setName("系统管理员");
			user.setPassword("password");
			user.setUserRole(UserRole.ADMIN);
			
			em.persist(user);
			  
		}
	}
		
}
