/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
@Table(name = "stage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StageEntity.findAll", query = "SELECT s FROM StageEntity s"),
    //@NamedQuery(name = "StageEntity.findById", query = "SELECT s FROM StageEntity s WHERE s.id = :id"),
    //@NamedQuery(name = "StageEntity.findAllReleases", query = "SELECT DISTINCT s.releaseEntity FROM StageEntity"),
    @NamedQuery(name = "StageEntity.findByRelease", query = "SELECT s FROM StageEntity s WHERE s.releaseEntity.releaseName = :srelease"),    
   // @NamedQuery(name = "StageEntity.findByComments", query = "SELECT s FROM StageEntity s WHERE s.comments = :comments")
    })
public class StageEntity implements Serializable {

    @OneToMany(mappedBy = "stageId")
    private List<RegressDetails> regressDetailsList;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "stage_name")
    private String stageName;
    
    @Size(max = 200)
    @Column(name = "comments")
    private String comments;
    
    @JoinColumn(name = "release_name", referencedColumnName = "release_name")
    @ManyToOne
    private ReleasesEntity releaseEntity;

    public ReleasesEntity getReleaseEntity() {
        return releaseEntity;
    }

    public void setReleaseEntity(ReleasesEntity releaseEntity) {
        this.releaseEntity = releaseEntity;
    }
    
//    @OneToMany(mappedBy = "stageid")
//    private Collection<StageUpperstackShiphomesEntity> stageUpperstackShiphomesEntityCollection;
    
//    @OneToMany(mappedBy = "stageid")
//    private Collection<StageInfraDetailsEntity> stageInfraDetailsEntityCollection;

    public StageEntity() {
    }

    public StageEntity(Integer id) {
        this.id = id;
    }

    public StageEntity(Integer id, String stageName) {
        this.id = id;
        this.stageName = stageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        if (!(object instanceof StageEntity)) {
            return false;
        }
        StageEntity other = (StageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.StageEntity[ id=" + id + ", stageName:" + stageName + ", releaseEntity:" + releaseEntity +"]";
    }

    @XmlTransient
    public List<RegressDetails> getRegressDetailsList() {
        return regressDetailsList;
    }

    public void setRegressDetailsList(List<RegressDetails> regressDetailsList) {
        this.regressDetailsList = regressDetailsList;
    }
    
}
