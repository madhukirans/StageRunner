/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

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
    @NamedQuery(name = "ShiphomeNames.findAll", query = "SELECT s FROM ShiphomeNames s"),
    @NamedQuery(name = "ShiphomeNames.findByReleaseAndProduct", query = "SELECT s FROM ShiphomeNames s WHERE s.release.id = :release AND s.product.id = :product")
})
public class ShiphomeNames implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;
    @JoinColumn(name = "platform", referencedColumnName = "id")
    @ManyToOne
    private Platform platform;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "releaseid", referencedColumnName = "releaseid")
    @ManyToOne
    private Releases release;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;

    public ShiphomeNames() {
    }

    public ShiphomeNames(Integer id) {
        this.id = id;
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

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiphomeNames)) {
            return false;
        }
        ShiphomeNames other = (ShiphomeNames) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShiphomeNames[ id=" + id + name + " ]";
    }
    
}
