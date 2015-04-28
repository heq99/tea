package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import qianghe.teasales.model.Product;

@Stateless
public class ProductDao extends AbstractDao {

	public List<Product> getAllProducts() {
		return getEntityManager().createNamedQuery("Product.getAllProducts", Product.class).getResultList();
	}
	
	public List<String> getDistinctProductNames() {
		return getEntityManager().createNamedQuery("Product.getDistinctProductNames", String.class).getResultList();
	}

	public List<Product> getProductByName(String prodName) {
		return getEntityManager().createNamedQuery("Product.getProductsByName", Product.class)
				.setParameter("prodName", prodName).getResultList();
	}
	
	public Product getProductByAttributes(String prodName, String levelName, String unitName, String specName) {
		try {
			return getEntityManager().createNamedQuery("Product.getProductByAttributes", Product.class)
					.setParameter("prodName", prodName)
					.setParameter("levelName", levelName)
					.setParameter("unitName", unitName)
					.setParameter("specName", specName)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void saveProduct(Product product) {
		EntityManager em = getEntityManager();
		if (product.getId() != null) {
			product = em.merge(product);
		}
		em.persist(product);
	}

	public void deleteProduct(Product product) {
		EntityManager em = getEntityManager();
		product = em.merge(product);
		em.remove(product);
	}
}
