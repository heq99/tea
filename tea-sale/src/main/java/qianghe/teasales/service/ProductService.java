package qianghe.teasales.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.ProductDao;
import qianghe.teasales.model.Product;

@Stateless
public class ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Inject
	private ProductDao productDao;
	
	public List<Product> getAllProducts() {
		logger.debug("Start to get all products...");
		try {
			return productDao.getAllProducts();
		} finally {
			logger.debug("Finished to get all products.");
		}
	}
	
	public List<String> getDistinctProductNames() {
		logger.debug("Start to get all distinct product names...");
		try {
			return productDao.getDistinctProductNames();
		} finally {
			logger.debug("Finished to get all distinct product names.");
		}
	}
	
	public List<Product> getProductsByName(String productName) {
		logger.debug("Start to get all products with short name: " + productName);
		try {
			return productDao.getProductByName(productName);
		} finally {
			logger.debug("Finished to get all products with short name: " + productName);
		}
	}
	
	public boolean existProduct(String prodName, String levelName, String unitName, String specName) {
		logger.debug("Start to check if product with attributes exists: " + prodName + " " + levelName + " " + unitName + " " + specName);
		try {
			Product product = productDao.getProductByAttributes(prodName, levelName, unitName, specName);
			return product != null;
		} finally {
			logger.debug("Finished to check if product with attributes exists: " + prodName + " " + levelName + " " + unitName + " " + specName);
		}
	}
	
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}
	
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}
}
