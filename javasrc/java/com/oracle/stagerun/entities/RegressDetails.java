/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Generated;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@Entity
@Table(name = "regress_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegressDetails.findAll", query = "SELECT r FROM RegressDetails r"),
    @NamedQuery(name = "RegressDetails.findById", query = "SELECT r FROM RegressDetails r WHERE r.id = :id"),
    @NamedQuery(name = "RegressDetails.findByFarmrunId", query = "SELECT r FROM RegressDetails r WHERE r.farmrunId = :farmrunId"),
    @NamedQuery(name = "RegressDetails.findBySapphireUploadStatus", query = "SELECT r FROM RegressDetails r WHERE r.sapphireUploadStatus = :sapphireUploadStatus"),
    @NamedQuery(name = "RegressDetails.findByStage", query = "SELECT r FROM RegressDetails r WHERE r.stageId.id = :stageId"),
    @NamedQuery(name = "RegressDetails.findByStageAndProduct", query = "SELECT r FROM RegressDetails r WHERE r.stageId.id = :stageId AND r.product.productName=:product"),
    @NamedQuery(name = "RegressDetails.findByStageAndProductAndTestUnit", 
            query = "SELECT r FROM RegressDetails r WHERE r.stageId.id = :stageId AND r.product.productName=:product AND r.testunitId.id = :testunitId"),
    @NamedQuery(name = "RegressDetails.findByStarttime", query = "SELECT r FROM RegressDetails r WHERE r.starttime = :starttime"),
    @NamedQuery(name = "RegressDetails.findByEndtime", query = "SELECT r FROM RegressDetails r WHERE r.endtime = :endtime"),
    @NamedQuery(name = "RegressDetails.findByStatus", query = "SELECT r FROM RegressDetails r WHERE r.status = :status")})
public class RegressDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "farmrun_id")
    private Integer farmrunId;
    
    @Lob
    @Size(max = 16777215)
    @Column(name = "work_loc")
    private String workLoc;
    
    
    @Column(name = "suc_count")
    private Integer sucCount;    
    
    @Column(name = "dif_count")
    private Integer difCount;
    
    @Size(max = 10)
    @Column(name = "sapphire_upload_status")    
    private String sapphireUploadStatus;
    
    @Column(name = "starttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar starttime;
    
    @Column(name = "endtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endtime;
    
    @Size(max = 11)
    @Column(name = "status")
    //@Enumerated (EnumType.STRING) 
    private String status;
    
    @Lob
    @Size(max = 16777215)
    @Column(name = "gtlf_file_loc")
    private String gtlfFileLoc;
    
    @JoinColumn(name = "product", referencedColumnName = "product_name")
    @ManyToOne
    private ProductsEntity product;
    
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    @ManyToOne
    private StageEntity stageId;
    
    @JoinColumn(name = "testunit_id", referencedColumnName = "id")
    @ManyToOne
    private TestUnitsEntity testunitId;

    public RegressDetails() {
    }

    public RegressDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFarmrunId() {
        return farmrunId;
    }

    public void setFarmrunId(Integer farmrunId) {
        this.farmrunId = farmrunId;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    public Integer getSucCount() {
        return sucCount;
    }

    public void setSucCount(Integer sucCount) {
        this.sucCount = sucCount;
    }

    public Integer getDifCount() {
        return difCount;
    }

    public void setDifCount(Integer difCount) {
        this.difCount = difCount;
    }   

    public String getSapphireUploadStatus() {
        return sapphireUploadStatus;
    }

    public void setSapphireUploadStatus(String sapphireUploadStatus) {
        this.sapphireUploadStatus = sapphireUploadStatus;
    }

    public Calendar getStarttime() {
        return starttime;
    }

    public void setStarttime(Calendar starttime) {
        this.starttime = starttime;
    }

    public Calendar getEndtime() {
        return endtime;
    }

    public void setEndtime(Calendar endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGtlfFileLoc() {
        return gtlfFileLoc;
    }

    public void setGtlfFileLoc(String gtlfFileLoc) {
        this.gtlfFileLoc = gtlfFileLoc;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public StageEntity getStageId() {
        return stageId;
    }

    public void setStageId(StageEntity stageId) {
        this.stageId = stageId;
    }

    public TestUnitsEntity getTestunitId() {
        return testunitId;
    }

    public void setTestunitId(TestUnitsEntity testunitId) {
        this.testunitId = testunitId;
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
        if (!(object instanceof RegressDetails)) {
            return false;
        }
        RegressDetails other = (RegressDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RegressDetails[ id=" + id + " ] farmrunId: " + farmrunId + " StartTime:" + starttime + 
                " EndTime:" + endtime + " status:" + status + "\nProduct: " + product + "\nstageId: " + stageId + 
                "\nTestUnitId:" + testunitId;
    }    
}
