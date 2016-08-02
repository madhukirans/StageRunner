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
@Table(name = "shiphome_names")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShiphomeNamesEntity.findAll", query = "SELECT s FROM ShiphomeNamesEntity s")
   // @NamedQuery(name = "ShiphomeNamesEntity.findById", query = "SELECT s FROM ShiphomeNamesEntity s WHERE s.id = :id"),
    //@NamedQuery(name = "ShiphomeNamesEntity.findByShiphomeName", query = "SELECT s FROM ShiphomeNamesEntity s WHERE s.shiphomeName = :shiphomeName")
})

public class ShiphomeNamesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(max = 200)
    @Column(name = "shiphome_name")
    private String shiphomeName;
    
    @ManyToOne
    @JoinColumn (name = "release_name" , referencedColumnName = "release_name")
    private ReleasesEntity releaseEntity;
    
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
    private ProductsEntity product;
    
    @ManyToOne 
    @JoinColumn(name = "platform", referencedColumnName = "name")
    private PlatformEntity platformEntity;   
    
            
    public ShiphomeNamesEntity() {
    }

    public ShiphomeNamesEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }    

    public PlatformEntity getPlatformEntity() {
        return platformEntity;
    }

    public void setPlatformEntity(PlatformEntity platformEntity) {
        this.platformEntity = platformEntity;
    }       

    public ReleasesEntity getReleaseEntity() {
        return releaseEntity;
    }

    public void setReleaseEntity(ReleasesEntity releaseEntity) {
        this.releaseEntity = releaseEntity;
    }
    
    public String getShiphomeName() {
        return shiphomeName;
    }

    public void setShiphomeName(String shiphomeName) {
        this.shiphomeName = shiphomeName;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof ShiphomeNamesEntity)) {
//            return false;
//        }
//        ShiphomeNamesEntity other = (ShiphomeNamesEntity) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.ShiphomeNames[ id=" + id + ", " + shiphomeName + " ]";
    }
    
}
