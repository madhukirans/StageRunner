/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NamedQueries({
    @NamedQuery(name = "Releases.findAll", query = "SELECT r FROM Releases r")   
    
    })
public class Releases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "releaseid", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "release")
    private List<Stage> stageList;
    
    @Column(name = "users")
    private Integer users;
    
    @OneToMany(mappedBy = "release")    
    private List<Testunit> testunitList;
    
    @OneToMany(mappedBy = "release")
    private List<ShiphomeNames> shiphomeNamesList;
    
    

    public Releases() {
    }

    public Releases(Integer id) {
        this.id = id;
    }

    public Releases(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
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
        if (!(object instanceof Releases)) {
            return false;
        }
        Releases other = (Releases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "Releases[ id=" + id +  name + " ]";
    }
    
}
