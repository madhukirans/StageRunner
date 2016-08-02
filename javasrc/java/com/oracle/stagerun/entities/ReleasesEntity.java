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
@Table(name = "releases")
@XmlRootElement
@NamedQueries(
        @NamedQuery(name = "ReleasesEntity.findAll", query = "SELECT r FROM ReleasesEntity r")//,
//@NamedQuery(name = "ReleasesEntity.findByReleaseName", query = "SELECT r FROM ReleasesEntity r WHERE r.releaseName = :rname"),
//@NamedQuery(name = "ReleasesEntity.findByDesc", query = "SELECT r FROM ReleasesEntity r WHERE r.desc = :desc")
)
public class ReleasesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "release_name")
    private String releaseName;

    @Size(max = 45)
    @Column(name = "description")
    private String desc1;

    @OneToMany(mappedBy = "releaseEntity")
    private List<StageEntity> stageEntityList;

    @OneToMany(mappedBy = "releaseEntity")
    private List<TestUnitsEntity> testUnitEntityList;

    @OneToMany(mappedBy = "releaseEntity")
    private List<ShiphomeNamesEntity> shiphomeNamesList;

    public ReleasesEntity() {
    }

    public ReleasesEntity(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    @XmlTransient
    public List<StageEntity> getStageEntityList() {
        return stageEntityList;
    }

    public void setStageEntityList(List<StageEntity> stageEntityList) {
        this.stageEntityList = stageEntityList;
    }

    @XmlTransient
    public List<TestUnitsEntity> getTestUnitEntityList() {
        return testUnitEntityList;
    }

    public void setTestUnitEntityList(List<TestUnitsEntity> testUnitEntityList) {
        this.testUnitEntityList = testUnitEntityList;
    }

    @XmlTransient
    public List<ShiphomeNamesEntity> getShiphomeNamesList() {
        return shiphomeNamesList;
    }

    public void setShiphomeNamesList(List<ShiphomeNamesEntity> shiphomeNamesList) {
        this.shiphomeNamesList = shiphomeNamesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (releaseName != null ? releaseName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleasesEntity)) {
            return false;
        }
        ReleasesEntity other = (ReleasesEntity) object;
        if ((this.releaseName == null && other.releaseName != null) || (this.releaseName != null && !this.releaseName.equals(other.releaseName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.ReleasesEntity[ releaseName=" + releaseName + " ]";
    }

}
