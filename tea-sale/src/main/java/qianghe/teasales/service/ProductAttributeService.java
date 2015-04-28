package qianghe.teasales.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qianghe.teasales.dao.ProductAttributeDao;
import qianghe.teasales.model.ProductAttribute;
import qianghe.teasales.model.ProductLevel;
import qianghe.teasales.model.ProductSpec;
import qianghe.teasales.model.ProductUnit;

@Stateless
public class ProductAttributeService {

	private static Logger logger = LoggerFactory.getLogger(ProductAttributeService.class);
	
	@Inject
	private ProductAttributeDao productAttributeDao;
	
	public List<ProductLevel> getAllProductLevels() {
		logger.trace("Start to get all product levels...");
		try {
			return productAttributeDao.getAllProductLevels();
		} finally {
			logger.trace("Finished to get all product levels.");
		}
	}
	
	public List<ProductUnit> getAllProductUnits() {
		logger.trace("Start to get all product units...");
		try {
			return productAttributeDao.getAllProductUnits();
		} finally {
			logger.trace("Finished to get all product units.");
		}
	}
	
	public List<ProductSpec> getAllProductSpecs() {
		logger.trace("Start to get all product specs...");
		try {
			return productAttributeDao.getAllProductSpecs();
		} finally {
			logger.trace("Finished to get all product specs");
		}
	}
	
	public ProductLevel getProductLevelByName(String name) {
		return productAttributeDao.getProductLevelByName(name);
	}
	
	public ProductUnit getProductUnitByName(String name) {
		return productAttributeDao.getProductUnitByName(name);
	}
	
	public ProductSpec getProductSpecByName(String name) {
		return productAttributeDao.getProductSpecByName(name);
	}

	public boolean existPoductLevelName(String name) {
		logger.trace("Start to check if product level exists for name: " + name);
		try {
			ProductLevel productLevel = getProductLevelByName(name);
			return productLevel != null;
		} finally {
			logger.trace("Finished to check if product level exists for name: " + name);
		}
	}
	
	public boolean existProductUnitName(String name) {
		logger.trace("Start to check if product unit exists for name: " + name);
		try {
			ProductUnit productUnit = getProductUnitByName(name);
			return productUnit != null;
		} finally {
			logger.trace("Finished to check if product unit exists for name: " + name);
		}
	}
	
	public boolean existProductSpecName(String name) {
		logger.trace("Start to check if product spec exists for name: " + name);
		try {
			ProductSpec productSpec = getProductSpecByName(name);
			return productSpec != null;
		} finally {
			logger.trace("Finished to check if product spec exists for name: " + name);
		}
	}
	
	public void saveProductAttribute(ProductAttribute prodAttr) {
		logger.trace("Start to save product attribute: " + prodAttr.getName());
		try {
			productAttributeDao.saveProductAttribute(prodAttr);
		} finally {
			logger.trace("Finished to save product attribute: " + prodAttr.getName());
		}
	}
	
	public void deleteProductAttribute(ProductAttribute prodAttr) {
		logger.trace("Start to delete product attribute, name: " + prodAttr.getName());
		try {
			productAttributeDao.deleteProductAttribute(prodAttr);
		} finally {
			logger.trace("Finished to delete product attribute, name: " + prodAttr.getName());
		}
	}

}
