/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qianghe.teasales.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Qiang He
 */
@Entity
@Table(name = "PRODUCT")
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
    
    @ManyToMany
    @JoinTable(
        name="PRODUCT_LEVEL_MAPPING",
        joinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="LEVEL_ID", referencedColumnName="ID")})
    private List<ProductLevel> productLevels;
    
    @ManyToMany
    @JoinTable(
        name="PRODUCT_UNIT_MAPPING",
        joinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="UNIT_ID", referencedColumnName="ID")})
    private List<ProductUnit> productUnits;
    
    @ManyToMany
    @JoinTable(
        name="PRODUCT_SPEC_MAPPING",
        joinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="SPEC_ID", referencedColumnName="ID")})
    private List<ProductSpec> productSpecs;
    
    @Column(name = "PRICE")
    private Double price;

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

    public List<ProductLevel> getProductLevels() {
        return productLevels;
    }

    public void setProductLevels(List<ProductLevel> productLevels) {
        this.productLevels = productLevels;
    }

    public List<ProductUnit> getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(List<ProductUnit> productUnits) {
        this.productUnits = productUnits;
    }

    public List<ProductSpec> getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(List<ProductSpec> productSpecs) {
        this.productSpecs = productSpecs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
