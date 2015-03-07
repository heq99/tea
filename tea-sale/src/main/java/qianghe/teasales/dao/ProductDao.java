package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import qianghe.teasales.model.Product;
import qianghe.teasales.model.ProductLevel;

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
	
	public List<ProductLevel> getAllProductLevels() {
		return getEntityManager().createNamedQuery("ProductLevel.getAllProductLevels", ProductLevel.class)
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
	
	public void saveProductLevel(ProductLevel prodLevel) {
		if (prodLevel.getId() != null) {
			getEntityManager().merge(prodLevel);
		} else {
			getEntityManager().persist(prodLevel);
		}
	}
}
