/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "stage")//, uniqueConstraints = {@UniqueConstraint(columnNames = {"stage_name", "release"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stage.findAll", query = "SELECT s FROM Stage s"),
    @NamedQuery(name = "Stage.getRecent", query = "SELECT s FROM Stage s WHERE s.datecreated = (select max(t.datecreated) from Stage t)"),    
    @NamedQuery(name = "Stage.findByRelease", query = "SELECT s FROM Stage s WHERE s.release.id = :srelease"),
})
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "stage_name", nullable = false, length = 45)
    private String stageName;
    @Size(max = 200)
    @Column(name = "comments", length = 200)
    private String comments;
    
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    
    @JoinColumn(name = "releaseid", referencedColumnName = "releaseid")
    @ManyToOne(optional = false)
    private Releases release;
    
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;
    @OneToMany(mappedBy = "stage")
    private List<StageUpperstackShiphomes> stageUpperstackShiphomesList;
    @OneToMany(mappedBy = "stage")
    private List<RegressDetails> regressDetailsList;

    public Stage() {
    }

    public Stage(Integer id) {
        this.id = id;
    }

    public Stage(Integer id, String stageName) {
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

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Releases getRelease() {
        return release;
    }

    public void setRelease(Releases release) {
        this.release = release;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "Stage[ id=" + id + "," + stageName + " ]";
    }
    
}
