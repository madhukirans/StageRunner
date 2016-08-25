/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Cacheable(false)
@Table(name = "stage_upperstack_shiphomes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StageUpperstackShiphomes.findAll", query = "SELECT s FROM StageUpperstackShiphomes s"),
    @NamedQuery(name = "StageUpperstackShiphomes.findByStage", query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.id=:stage"),
    @NamedQuery(name = "StageUpperstackShiphomes.findByReleaseStageName", 
                query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.release.name = :releaseName AND s.stage.stageName=:stageName"),
    @NamedQuery(name = "StageUpperstackShiphomes.findByReleaseStageNameProductName", 
                query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.release.name = :releaseName AND s.stage.stageName=:stageName AND s.product.name=:productName "),
    
    @NamedQuery(name = "StageUpperstackShiphomes.findByReleaseStageNameProductNamePlatformName", 
                query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.release.name = :releaseName AND s.stage.stageName=:stageName AND s.product.name=:productName AND s.platform.name=:platformName"),
    
    @NamedQuery(name = "StageUpperstackShiphomes.findByReleaseStage", 
            query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.release.id=:release AND s.stage.id = :stage"),
    
    @NamedQuery(name = "StageUpperstackShiphomes.findByStageProduct", 
                    query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.stageName = :stageName AND s.product.name = :productName"),
    
    @NamedQuery(name = "StageUpperstackShiphomes.findByStageProductPlatform", 
                    query = "SELECT s FROM StageUpperstackShiphomes s WHERE s.stage.stageName = :stageName AND s.product.name = :productName AND s.platform.name = :platformName" ),
    
    @NamedQuery(name = "StageUpperstackShiphomes.findProductsStage", 
                    query = "SELECT DISTINCT s.product FROM StageUpperstackShiphomes s WHERE s.stage.id = :stage"),
    })
public class StageUpperstackShiphomes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "shiphomenames", length = 400)
    private String shiphomenames;
    @Size(max = 400)
    @Column(name = "shiphomeloc", length = 400)
    private String shiphomeloc;
    @Size(max = 400)
    @Column(name = "manifest", length = 400)
    private String manifest;
    @Size(max = 400)
    @Column(name = "comment", length = 400)
    private String comment;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;
    @JoinColumn(name = "platform", referencedColumnName = "id")
    @ManyToOne
    private Platform platform;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "stage", referencedColumnName = "id")
    @ManyToOne
    private Stage stage;

    public StageUpperstackShiphomes() {
    }

    public StageUpperstackShiphomes(Integer id) {
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

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
        if (!(object instanceof StageUpperstackShiphomes)) {
            return false;
        }
        StageUpperstackShiphomes other = (StageUpperstackShiphomes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StageUpperstackShiphomes[ id=" + id + "," +  shiphomeloc +  " ]";
    }
    
}
