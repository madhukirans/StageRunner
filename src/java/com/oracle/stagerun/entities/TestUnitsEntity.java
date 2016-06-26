/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "test_units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestUnitsEntity.findAll", query = "SELECT t FROM TestUnitsEntity t"),
   // @NamedQuery(name = "TestUnitsEntity.findById", query = "SELECT t FROM TestUnitsEntity t WHERE t.id = :id"),
   // @NamedQuery(name = "TestUnitsEntity.findByProducts", query = "SELECT t FROM TestUnitsEntity t WHERE t.productName.productName = :pname"),
   // @NamedQuery(name = "TestUnitsEntity.findByTestUnitName", query = "SELECT t FROM TestUnitsEntity t WHERE t.testUnitName = :testUnitName"),
   // @NamedQuery(name = "TestUnitsEntity.findByDescription", query = "SELECT t FROM TestUnitsEntity t WHERE t.description = :description")
})
public class TestUnitsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 40)
    @Column(name = "test_unit_name")
    private String testUnitName;
    @Size(max = 40)
    @Column(name = "description")
    private String description;
    
//    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
//    @ManyToOne
//    private ProductsEntity productName;
//
    public TestUnitsEntity() {
    }

    public TestUnitsEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestUnitName() {
        return testUnitName;
    }

    public void setTestUnitName(String testUnitName) {
        this.testUnitName = testUnitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public ProductsEntity getProductName() {
//        return productName;
//    }
//
//    public void setProductName(ProductsEntity productName) {
//        this.productName = productName;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestUnitsEntity)) {
            return false;
        }
        TestUnitsEntity other = (TestUnitsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.TestUnitsEntity[ id=" + id + " ]";
    }
    
}
