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
@Table(name = "platform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlatformEntity.findAll", query = "SELECT p FROM PlatformEntity p"),
    @NamedQuery(name = "PlatformEntity.findByName", query = "SELECT p FROM PlatformEntity p WHERE p.name = :name"),
    @NamedQuery(name = "PlatformEntity.findByDescription", query = "SELECT p FROM PlatformEntity p WHERE p.description = :description"),
    @NamedQuery(name = "PlatformEntity.findByIsdefault", query = "SELECT p FROM PlatformEntity p WHERE p.isdefault = :isdefault")})
public class PlatformEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME")
    private String name;

    @Size(max = 30)
    @Column(name = "DESCRIPTION")
    private String description;

    @Size(max = 1)
    @Column(name = "ISDEFAULT")
    private String isdefault;

    @OneToMany(mappedBy = "platform")
    private List<StageUpperstackShiphomesEntity> stageUpperstackShiphomesEntityList;

    @OneToMany(mappedBy = "platform")
    private List<TestUnitsEntity> testUnitsEntityList;

    @OneToMany(mappedBy = "platformEntity")
    private List<ShiphomeNamesEntity> shiphomeNamesList;

    public PlatformEntity() {
    }

    public PlatformEntity(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<TestUnitsEntity> getTestUnitsEntityList() {
        return testUnitsEntityList;
    }

    public void setTestUnitsEntityList(List<TestUnitsEntity> testUnitsEntityList) {
        this.testUnitsEntityList = testUnitsEntityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @XmlTransient
    public List<ShiphomeNamesEntity> getShiphomeNamesList() {
        return shiphomeNamesList;
    }

    public void setShiphomeNamesList(List<ShiphomeNamesEntity> shiphomeNamesList) {
        this.shiphomeNamesList = shiphomeNamesList;
    }

    @XmlTransient
    public List<StageUpperstackShiphomesEntity> getStageUpperstackShiphomesEntityList() {
        return stageUpperstackShiphomesEntityList;
    }

    public void setStageUpperstackShiphomesEntityList(List<StageUpperstackShiphomesEntity> stageUpperstackShiphomesEntityList) {
        this.stageUpperstackShiphomesEntityList = stageUpperstackShiphomesEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlatformEntity)) {
            return false;
        }
        PlatformEntity other = (PlatformEntity) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlatformEntity[ name=" + name + " ]";
    }

}
