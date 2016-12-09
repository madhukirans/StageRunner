/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "regress_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegressDetailsGtlfFileHelper.findById",
            query = "SELECT r FROM RegressDetailsGtlfFileHelper r WHERE r.id = :id")
})
public class RegressDetailsGtlfFileHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "gtlf_file")
    private byte[] gtlfFile;
    
    public RegressDetailsGtlfFileHelper() {
        
    }
    
    public RegressDetailsGtlfFileHelper(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getGtlfFile() {
        return gtlfFile;
    }

    public void setGtlfFile(byte[] gtlfFile) {
        this.gtlfFile = gtlfFile;
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
        if (!(object instanceof RegressDetailsGtlfFileHelper)) {
            return false;
        }
        RegressDetailsGtlfFileHelper other = (RegressDetailsGtlfFileHelper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entity.RegressDetailsGtlfFileHelper[ id=" + id + " ]";
    }

}
