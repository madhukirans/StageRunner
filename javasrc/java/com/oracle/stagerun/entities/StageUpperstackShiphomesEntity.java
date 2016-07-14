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
@Table(name = "stage_upperstack_shiphomes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StageUpperstackShiphomesEntity.findAll", query = "SELECT s FROM StageUpperstackShiphomesEntity s"),
    @NamedQuery(name = "StageUpperstackShiphomesEntity.findByStageId", 
            query = "SELECT s FROM StageUpperstackShiphomesEntity s WHERE s.stageid.id = :stageid"),
    @NamedQuery(name = "StageUpperstackShiphomesEntity.findByStageAndProduct", 
                    query = "SELECT s FROM StageUpperstackShiphomesEntity s WHERE s.stageid.id = :stageid AND s.product.productName = :productname"),
    @NamedQuery(name = "StageUpperstackShiphomesEntity.findProductsStageId", 
                    query = "SELECT DISTINCT s.product FROM StageUpperstackShiphomesEntity s WHERE s.stageid = :stageid"),
 //   @NamedQuery(name = "StageUpperstackShiphomesEntity.findByShiphomeloc", query = "SELECT s FROM StageUpperstackShiphomesEntity s WHERE s.shiphomeloc = :shiphomeloc"),
 //   @NamedQuery(name = "StageUpperstackShiphomesEntity.findByManifest", query = "SELECT s FROM StageUpperstackShiphomesEntity s WHERE s.manifest = :manifest"),
 //   @NamedQuery(name = "StageUpperstackShiphomesEntity.findByComment", query = "SELECT s FROM StageUpperstackShiphomesEntity s WHERE s.comment = :comment")
    })
public class StageUpperstackShiphomesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 400)
    @Column(name = "shiphomenames")
    private String shiphomenames;
    
    @Size(max = 400)
    @Column(name = "shiphomeloc")
    private String shiphomeloc;
    
    @Size(max = 400)
    @Column(name = "manifest")
    private String manifest;
    
    @Size(max = 400)
    @Column(name = "comment")
    private String comment;
    
    @JoinColumn(name = "platform", referencedColumnName = "NAME")
    @ManyToOne
    private PlatformEntity platform;
    
    @JoinColumn(name = "product", referencedColumnName = "product_name")
    @ManyToOne
    private ProductsEntity product;
    
    @JoinColumn(name = "stageid", referencedColumnName = "id")
    @ManyToOne
    private StageEntity stageid;

    public StageUpperstackShiphomesEntity() {
    }

    public StageUpperstackShiphomesEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShiphomenames() {
        return shiphomenames;
    }

    public void setShiphomenames(String shiphomenames) {
        this.shiphomenames = shiphomenames;
    }

    public String getShiphomeloc() {
        return shiphomeloc;
    }

    public void setShiphomeloc(String shiphomeloc) {
        this.shiphomeloc = shiphomeloc;
    }

    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEntity platform) {
        this.platform = platform;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public StageEntity getStageid() {
        return stageid;
    }

    public void setStageid(StageEntity stageid) {
        this.stageid = stageid;
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
        if (!(object instanceof StageUpperstackShiphomesEntity)) {
            return false;
        }
        StageUpperstackShiphomesEntity other = (StageUpperstackShiphomesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oracle.stagerun.entities.StageUpperstackShiphomesEntity[ id=" + id + "," + shiphomenames + "," + shiphomeloc + "]";
    }
    
}
