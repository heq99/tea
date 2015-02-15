package qianghe.teasales.manager;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.UserDao;
import qianghe.teasales.model.User;

@Named
public class UserManager {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);

	@Inject
	private UserDao userDao;

	public User loginUser(String username, String password) {

		logger.debug("User: " + username + " is trying to log in.");

		User user = userDao.loginUser(username, password);
		if (user != null) {
			logger.info("User: " + username + " has logged in.");
			return user;
		} else {
			logger.info("User: " + username + " is not found.");
			userDao.checkOrCreateAdminUser();
			return null;
		}
	}
}
