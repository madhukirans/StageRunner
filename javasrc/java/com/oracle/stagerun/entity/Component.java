/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
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
@Table(name = "component")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c"),
    @NamedQuery(name = "Component.findByProduct", query = "SELECT c FROM Component c WHERE c.product.id = :productid"),   
})
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;
  
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    @OneToMany(mappedBy = "component")
    private List<Testunit> testunitList;
    @OneToMany(mappedBy = "component")
    private List<ShiphomeNames> shiphomeNamesList;
    @OneToMany(mappedBy = "component")
    private List<StageUpperstackShiphomes> stageUpperstackShiphomesList;
    @OneToMany(mappedBy = "component")
    private List<RegressDetails> regressDetailsList;

    public Component() {
    }

    public Component(Integer id) {
        this.id = id;
    }

    public Component(Integer id, String name) {
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Component)) {
            return false;
        }
        Component other = (Component) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Component[ id=" + id + "," + name + " ]";
    }
    
}
