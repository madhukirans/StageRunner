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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "testunit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testunit.findAll", query = "SELECT t FROM Testunit t"),    
    @NamedQuery(name = "Testunit.findByReleaseProduct",
            query = "SELECT t FROM Testunit t WHERE t.release.id=:release AND t.product.id=:product"),
    
    @NamedQuery(name = "Testunit.findByReleaseProductPlatform", 
            query = "SELECT t FROM Testunit t WHERE t.release.id=:release AND t.product.id=:product AND t.platform.id=:platform"),
    @NamedQuery(name = "Testunit.findByReleaseProductComponent",
            query = "SELECT t FROM Testunit t WHERE t.release.id=:release AND t.product.id=:product AND t.component.id=:component"),
    @NamedQuery(name = "Testunit.findByReleaseProductComponentPlatform",
            query = "SELECT t FROM Testunit t WHERE t.release.id=:release AND t.product.id=:product AND t.platform.id=:platform"),
    
})

public class Testunit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "testunit_name", nullable = false, length = 40)
    private String testunitName;

    //@Size(max = 4)
    @Column(name = "isDte", length = 4)
    @Enumerated(EnumType.STRING)
    private TestUnitRunTypeEnum isDte;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "JobreqAgentCommand", length = 2147483647)
    private String jobreqAgentCommand;

    @Size(max = 200)
    @Column(name = "emails", length = 200)
    private String emails;

    @Size(max = 40)
    @Column(name = "description", length = 40)
    private String description;

    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;

    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product product;

    @JoinColumn(name = "platform", referencedColumnName = "id")
    @ManyToOne
    private Platform platform;

    @JoinColumn(name = "releaseid", referencedColumnName = "releaseid")
    @ManyToOne
    private Releases release;

    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;

    @OneToMany(mappedBy = "testunit")
    private List<RegressDetails> regressDetailsList;

    public Testunit() {
    }

    public Testunit(Integer id) {
        this.id = id;
    }

    public Testunit(Integer id, String testunitName) {
        this.id = id;
        this.testunitName = testunitName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestunitName() {
        return testunitName;
    }

    public void setTestunitName(String testunitName) {
        this.testunitName = testunitName;
    }

    public TestUnitRunTypeEnum getIsDte() {
        return isDte;
    }

    public void setIsDte(TestUnitRunTypeEnum isDte) {
        this.isDte = isDte;
    }

    public String getJobreqAgentCommand() {
        return jobreqAgentCommand;
    }

    public void setJobreqAgentCommand(String jobreqAgentCommand) {
        this.jobreqAgentCommand = jobreqAgentCommand;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
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
        if (!(object instanceof Testunit)) {
            return false;
        }
        Testunit other = (Testunit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Testunit[ id=" + id + "," + testunitName + platform +  " ]";
    }

}
