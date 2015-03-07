package qianghe.teasales.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AbstractDao {
	
	@PersistenceUnit
	protected EntityManagerFactory emf;
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
