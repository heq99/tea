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
	
	public boolean existUserLogin(String login) {
		logger.trace("Start to check if user login exists for: " + login);
		try {
			User user = getUserByLogin(login);
			return user != null;
		} finally {
			logger.trace("Finished to check if user login exists for: " + login);
		}
	}
	
	public void saveUser(User user) {
		logger.trace("Start to save user: " + user.getLogin());
		try {
			userDao.saveUser(user);
		} finally {
			logger.trace("Finished to save user: " + user.getLogin());
		}
	}
	
	public void deleteUser(User user) {
		logger.trace("Start to delete user: " + user.getLogin());
		try {
			userDao.deleteUser(user);
		} finally {
			logger.trace("Finished to delete user: " + user.getLogin());
		}
	}
}
