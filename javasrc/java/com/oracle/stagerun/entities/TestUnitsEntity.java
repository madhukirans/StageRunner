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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "test_units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestUnitsEntity.findAll", query = "SELECT t FROM TestUnitsEntity t"),
    // @NamedQuery(name = "TestUnitsEntity.findById", query = "SELECT t FROM TestUnitsEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TestUnitsEntity.findByProducts",
            query = "SELECT t FROM TestUnitsEntity t WHERE t.productName = :pname"),

    @NamedQuery(name = "TestUnitsEntity.findByReleaseAndProducts",
            query = "SELECT t FROM TestUnitsEntity t WHERE t.releaseEntity.releaseName=:release AND t.productName.productName=:pname"), 
    @NamedQuery(name = "TestUnitsEntity.findByReleaseAndProductsAndPlatform",
            query = "SELECT t FROM TestUnitsEntity t WHERE t.releaseEntity.releaseName=:release AND t.productName.productName=:pname AND t.platform.name=:platform" ), 
// @NamedQuery(name = "TestUnitsEntity.findByTestUnitName", query = "SELECT t FROM TestUnitsEntity t WHERE t.testUnitName = :testUnitName"),
// @NamedQuery(name = "TestUnitsEntity.findByDescription", query = "SELECT t FROM TestUnitsEntity t WHERE t.description = :description")
})
public class TestUnitsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "test_unit_name")
    private String testUnitName;

    @Size(max = 40)
    @Column(name = "description")
    private String description;

    @Column(name = "topoid")
    private Integer topoid;

    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
    @ManyToOne
    private ProductsEntity productName;

    @JoinColumn(name = "platform_name", referencedColumnName = "NAME")
    @ManyToOne
    private PlatformEntity platform;

    @JoinColumn(name = "release_name", referencedColumnName = "release_name")
    @ManyToOne
    private ReleasesEntity releaseEntity;

    @OneToMany(mappedBy = "testunitId")
    private List<RegressDetails> regressDetailsList;

    @Lob
    @Size(max = 16777215)
    @Column(name = "JobreqAgentCommand")
    private String jobreqAgentCommand;

    public TestUnitsEntity() {
    }

    public TestUnitsEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    
    public PlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEntity platform) {
        this.platform = platform;
    }

    
    public ReleasesEntity getRelease() {
        return releaseEntity;
    }

    public void setRelease(ReleasesEntity release) {
        this.releaseEntity = release;
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

    public Integer getTopoid() {
        return topoid;
    }

    public void setTopoid(Integer topoid) {
        this.topoid = topoid;
    }

    public String getJobreqAgentCommand() {
        return jobreqAgentCommand;
    }

    public void setJobreqAgentCommand(String jobreqAgentCommand) {
        this.jobreqAgentCommand = jobreqAgentCommand;
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
        return "TestUnitsEntity[ id=" + id + " name:" + testUnitName + " productName:" + productName + " platform:" +
                platform + " release: " + releaseEntity + "]";
    }

    public ProductsEntity getProductName() {
        return productName;
    }

    public void setProductName(ProductsEntity productName) {
        this.productName = productName;
    }

    @XmlTransient
    public List<RegressDetails> getRegressDetailsList() {
        return regressDetailsList;
    }

    public void setRegressDetailsList(List<RegressDetails> regressDetailsList) {
        this.regressDetailsList = regressDetailsList;
    }

}
