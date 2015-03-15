package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import qianghe.teasales.model.ProductAttribute;
import qianghe.teasales.model.ProductLevel;
import qianghe.teasales.model.ProductSpec;
import qianghe.teasales.model.ProductUnit;

@Stateless
public class ProductAttributeDao extends AbstractDao {

	public List<ProductLevel> getAllProductLevels() {
		return getEntityManager().createNamedQuery("ProductLevel.getAllProductLevels", ProductLevel.class)
				.getResultList();
	}
	
	public List<ProductUnit> getAllProductUnits() {
		return getEntityManager().createNamedQuery("ProductUnit.getAllProductUnits", ProductUnit.class)
				.getResultList();
	}
	
	public List<ProductSpec> getAllProductSpecs() {
		return getEntityManager().createNamedQuery("ProductSpec.getAllProductSpecs", ProductSpec.class)
				.getResultList();
	}
	
	public ProductLevel getProductLevelByName(String name) {
		try {
			return getEntityManager().createNamedQuery("ProductLevel.getProductLevelByName", ProductLevel.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public ProductUnit getProductUnitByName(String name) {
		try {
			return getEntityManager().createNamedQuery("ProductUnit.getProductUnitByName", ProductUnit.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public ProductSpec getProductSpecByName(String name) {
		try {
			return getEntityManager().createNamedQuery("ProductSpec.getProductSpecByName", ProductSpec.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void saveProductAttribute(ProductAttribute prodAttr) {
		EntityManager em = getEntityManager();
		if (prodAttr.getId() != null) {
			prodAttr = em.merge(prodAttr);
		}
		em.persist(prodAttr);
	}
	
	public void deleteProductAttribute(ProductAttribute prodAttr) {
		EntityManager em = getEntityManager();
		prodAttr = em.merge(prodAttr);
		em.remove(prodAttr);
	}

}
