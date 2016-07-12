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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "stage_infra_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StageInfraDetailsEntity.findAll", query = "SELECT s FROM StageInfraDetailsEntity s"),
 //   @NamedQuery(name = "StageInfraDetailsEntity.findById", query = "SELECT s FROM StageInfraDetailsEntity s WHERE s.id = :id"),
 //   @NamedQuery(name = "StageInfraDetailsEntity.findByInfraDistributions", query = "SELECT s FROM StageInfraDetailsEntity s WHERE s.infraDistributions = :infraDistributions"),
 //   @NamedQuery(name = "StageInfraDetailsEntity.findByPlatform", query = "SELECT s FROM StageInfraDetailsEntity s WHERE s.platform = :platform")
    })
public class StageInfraDetailsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "InfraDistributions")
    private String infraDistributions;
    
    @Size(max = 20)
    @Column(name = "Platform")
    private String platform;
    
//    @JoinColumn(name = "stageid", referencedColumnName = "id")
//    @ManyToOne
//    private StageEntity stageid;

    public StageInfraDetailsEntity() {
    }

    public StageInfraDetailsEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfraDistributions() {
        return infraDistributions;
    }

    public void setInfraDistributions(String infraDistributions) {
        this.infraDistributions = infraDistributions;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
//
//    public StageEntity getStageid() {
//        return stageid;
//    }
//
//    public void setStageid(StageEntity stageid) {
//        this.stageid = stageid;
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
        if (!(object instanceof StageInfraDetailsEntity)) {
            return false;
        }
        StageInfraDetailsEntity other = (StageInfraDetailsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.StageInfraDetailsEntity[ id=" + id + " ]";
    }
    
}
