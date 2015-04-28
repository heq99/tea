package qianghe.teasales.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.UserDao;
import qianghe.teasales.model.User;

@Stateless
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Inject
	private UserDao userDao;

	public List<User> getAllUsers() {
		logger.trace("Start to get all users...");
		try {
			return userDao.getAllUsers();
		} finally {
			logger.trace("Finished to get all users.");
		}
	}
	
	public User getUserByLogin(String login) {
		logger.trace("Start to get user by login: " + login + "...");
		try {
			return userDao.getUserByLogin(login);
		} finally {
			logger.trace("Finished to get user by login: " + login + "."); 
		}
	}
	
	private boolean existUserLogin(String login) {
		logger.trace("Start to check if user login exists for: " + login);
		try {
			User user = getUserByLogin(login);
			return user != null;
		} finally {
			logger.trace("Finished to check if user login exists for: " + login);
		}
	}
	
	public void saveUser(User user) throws TeaSalesException {
		logger.trace("Start to save user: " + user.getLogin());
		try {
			if (user.getId() == null) {
				if (existUserLogin(user.getLogin())) {
					throw new TeaSalesException("用户登录名已经被别人用了。");
				}
			}
			try {
				userDao.saveUser(user);
			} catch (Exception e) {
				throw new TeaSalesException("保存系统用户时出错。", e);
			}
		} finally {
			logger.trace("Finished to save user: " + user.getLogin());
		}
	}
	
	public void deleteUser(User user) throws TeaSalesException {
		logger.trace("Start to delete user: " + user.getLogin());
		try {
			userDao.deleteUser(user);
		} catch (Exception e) {
			throw new TeaSalesException("Can't delete user!", e);
		} finally {
			logger.trace("Finished to delete user: " + user.getLogin());
		}
	}
}
