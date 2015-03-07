package qianghe.teasales.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.ProductDao;
import qianghe.teasales.model.Product;
import qianghe.teasales.model.ProductLevel;

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
	
	public List<ProductLevel> getAllProductLevels() {
		logger.debug("Start to get all product levels...");
		try {
			return productDao.getAllProductLevels();
		} finally {
			logger.debug("Finished to get all product levels.");
		}
	}
	
	public boolean existPoductLevelName(String name) {
		logger.debug("Start to check if product level exists for name: " + name);
		try {
			ProductLevel productLevel = productDao.getProductLevelByName(name);
			return productLevel != null;
		} finally {
			logger.debug("Finished to check if product level exists for name: " + name);
		}
	}
	
	public void saveProductLevel(ProductLevel prodLevel) {
		logger.debug("Start to save product level: " + prodLevel.getName());
		try {
			productDao.saveProductLevel(prodLevel);
		} finally {
			logger.debug("Finished to save product level: " + prodLevel.getName());
		}
	}
}
