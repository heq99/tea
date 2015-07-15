package qianghe.teasales.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public class AbstractDao {
	
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}

}
