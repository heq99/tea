/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qianghe.teasales.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Qiang He
 */
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
	@NamedQuery(name = "Product.getAllProducts", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "Product.getDistinctProductNames", query = "SELECT DISTINCT p.shortName FROM Product p"),
	@NamedQuery(name = "Product.getProductsByName", query = "SELECT p FROM Product p WHERE p.shortName = :prodName"),
	@NamedQuery(name = "Product.getProductByAttributes", query = "SELECT p FROM Product p "
			+ "WHERE p.shortName = :prodName AND p.productLevel.name = :levelName "
			+ "AND p.productUnit.name = :unitName AND p.productSpec.name = :specName")
})
public class Product implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PRODUCT_NUMBER", length = 64)
    private String productNumber;
    
    @Column(name = "SHORT_NAME", length = 128)
    private String shortName;
    
    @Column(name = "LONG_NAME", length = 1024)
    private String longName;
    
    @Column(name = "ACTIVE")
    private Boolean active;
    
    @ManyToOne
    @JoinColumn(name="PRODUCT_LEVEL_ID")
    private ProductLevel productLevel;
    
    @ManyToOne
    @JoinColumn(name="PRODUCT_UNIT_ID")
    private ProductUnit productUnit;
    
    @ManyToOne
    @JoinColumn(name="PRODUCT_SPEC_ID")
    private ProductSpec productSpec;
    
    @Column(name = "PRICE", precision=10, scale=2)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    
    public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

    public ProductLevel getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(ProductLevel productLevel) {
		this.productLevel = productLevel;
	}

	public ProductUnit getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(ProductUnit productUnit) {
		this.productUnit = productUnit;
	}

	public ProductSpec getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(ProductSpec productSpec) {
		this.productSpec = productSpec;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qianghe.teasales.model.Product[ id=" + id + " ]";
    }
    
}
