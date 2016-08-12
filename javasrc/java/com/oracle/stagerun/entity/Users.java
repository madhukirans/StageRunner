/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")   
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "username", length = 45)
    private String username;
    @Size(max = 45)
    @Column(name = "password", length = 45)
    private String password;
    @Size(max = 45)
    @Column(name = "group", length = 45)
    private String group;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    
    @OneToMany(mappedBy = "users")
    private List<Component> componentList;
    @OneToMany(mappedBy = "users")
    private List<Product> productList;
    @OneToMany(mappedBy = "users")
    private List<Testunit> testunitList;
    @OneToMany(mappedBy = "users")
    private List<ShiphomeNames> shiphomeNamesList;
    @OneToMany(mappedBy = "users")
    private List<Stage> stageList;
    @OneToMany(mappedBy = "users")
    private List<StageUpperstackShiphomes> stageUpperstackShiphomesList;
    @OneToMany(mappedBy = "users")
    private List<RegressDetails> regressDetailsList;
    @OneToMany(mappedBy = "users")
    private List<Platform> platformList;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    @XmlTransient
    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @XmlTransient
    public List<Testunit> getTestunitList() {
        return testunitList;
    }

    public void setTestunitList(List<Testunit> testunitList) {
        this.testunitList = testunitList;
    }

    @XmlTransient
    public List<ShiphomeNames> getShiphomeNamesList() {
        return shiphomeNamesList;
    }

    public void setShiphomeNamesList(List<ShiphomeNames> shiphomeNamesList) {
        this.shiphomeNamesList = shiphomeNamesList;
    }

    @XmlTransient
    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    @XmlTransient
    public List<StageUpperstackShiphomes> getStageUpperstackShiphomesList() {
        return stageUpperstackShiphomesList;
    }

    public void setStageUpperstackShiphomesList(List<StageUpperstackShiphomes> stageUpperstackShiphomesList) {
        this.stageUpperstackShiphomesList = stageUpperstackShiphomesList;
    }

    @XmlTransient
    public List<RegressDetails> getRegressDetailsList() {
        return regressDetailsList;
    }

    public void setRegressDetailsList(List<RegressDetails> regressDetailsList) {
        this.regressDetailsList = regressDetailsList;
    }

    @XmlTransient
    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entity.Users[ id=" + id + " ]";
    }
    
}
