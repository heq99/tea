package qianghe.teasales.dao;

import java.util.List;

import javax.ejb.Stateless;

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
	
}
