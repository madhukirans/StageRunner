/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsEntity.findAll", query = "SELECT p FROM ProductsEntity p"),
    @NamedQuery(name = "ProductsEntity.findByProductName", query = "SELECT p FROM ProductsEntity p WHERE p.productName = :productName"),
    @NamedQuery(name = "ProductsEntity.findByProductDesc", query = "SELECT p FROM ProductsEntity p WHERE p.productDesc = :productDesc")})
public class ProductsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "product_name")
    private String productName;
    
    @Size(max = 45)
    @Column(name = "product_desc")
    private String productDesc;
   
    @OneToMany(mappedBy = "productName")
    private List<TestUnitsEntity> testUnitsEntityList;

    @OneToMany(mappedBy = "product")
    private List<RegressDetails> regressDetailsList;
    
    @OneToMany (mappedBy = "product")
    private List<ShiphomeNamesEntity> shiphomeNamesList;
    
    @OneToMany(mappedBy = "product")
    private List<StageUpperstackShiphomesEntity> stageUpperstackShiphomesEntityList;

    public ProductsEntity() {
    }

    public ProductsEntity(String productName) {
        this.productName = productName;
    }

    public List<ShiphomeNamesEntity> getShiphomeNamesList() {
        return shiphomeNamesList;
    }

    public void setShiphomeNamesList(List<ShiphomeNamesEntity> shiphomeNamesList) {
        this.shiphomeNamesList = shiphomeNamesList;
    }

    public List<StageUpperstackShiphomesEntity> getStageUpperstackShiphomesEntityList() {
        return stageUpperstackShiphomesEntityList;
    }

    public void setStageUpperstackShiphomesEntityList(List<StageUpperstackShiphomesEntity> stageUpperstackShiphomesEntityList) {
        this.stageUpperstackShiphomesEntityList = stageUpperstackShiphomesEntityList;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    
    @XmlTransient
    public List<TestUnitsEntity> getTestUnitsEntityList() {
        return testUnitsEntityList;
    }

    public void setTestUnitsEntityList(List<TestUnitsEntity> testUnitsEntityList) {
        this.testUnitsEntityList = testUnitsEntityList;
    }

    @XmlTransient
    public List<RegressDetails> getRegressDetailsList() {
        return regressDetailsList;
    }

    public void setRegressDetailsList(List<RegressDetails> regressDetailsList) {
        this.regressDetailsList = regressDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productName != null ? productName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsEntity)) {
            return false;
        }
        ProductsEntity other = (ProductsEntity) object;
        if ((this.productName == null && other.productName != null) || (this.productName != null && !this.productName.equals(other.productName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.ProductsEntity[ productName=" + productName + " ]";
    }
}
