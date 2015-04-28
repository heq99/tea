package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import qianghe.teasales.model.User;
import qianghe.teasales.model.UserRole;

@Stateless
public class UserDao extends AbstractDao {
	
	public User getUserByLogin(String login) {
		try {
			return getEntityManager().createNamedQuery("User.getUserByLogin", User.class)
				.setParameter("username", login)
				.getSingleResult();
		} catch (NoResultException e) {
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
		
	public List<User> getAllUsers() {
		return getEntityManager().createNamedQuery("User.getAllUsers", User.class).getResultList();
	}
	
	public void saveUser(User user) {
		EntityManager em = getEntityManager();
		if (user.getId() != null) {
			user = em.merge(user);
		}
		em.persist(user);
	}
	
	public void deleteUser(User user) {
		EntityManager em = getEntityManager();
		user = em.merge(user);
		em.remove(user);
	}
}
