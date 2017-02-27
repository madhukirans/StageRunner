/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import com.oracle.stagerun.tool.AbstractStageRun;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@Entity
@Cacheable(false)
@Table(name = "regress_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegressDetails.findAll", query = "SELECT r FROM RegressDetails r"),
    @NamedQuery(name = "RegressDetails.findByStage", query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage"),
    @NamedQuery(name = "RegressDetails.findByStageProduct", query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage AND r.product.id=:product"),
    //@NamedQuery(name = "RegressDetails.findByStageId", query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage AND r.product.id=:product"),

    @NamedQuery(name = "RegressDetails.findByStageProductComponent",
            query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage AND r.product.id=:product AND r.component.id = :component"),
    @NamedQuery(name = "RegressDetails.findByStageProductTestUnit",
            query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage AND r.product.id=:product AND r.testunit.id = :testunit"),
    @NamedQuery(name = "RegressDetails.findByStageProductComponentTestUnit",
            query = "SELECT r FROM RegressDetails r WHERE r.stage.id = :stage AND r.product.id=:product AND r.component.id = :component AND r.testunit.id = :testunit"),
    @NamedQuery(name = "RegressDetails.findByStatus", query = "SELECT r FROM RegressDetails r WHERE r.status = :notstartedstatus OR r.status = :runningstatus")
})
public class RegressDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "farmrun_id")
    private Integer farmrunId;
    //@Size(max = 11)
    @Column(name = "status", length = 11)
    @Enumerated(EnumType.STRING)
    private RegressStatus status;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;
    @Column(name = "endtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endtime;
    @Lob
    @Size(max = 16777215)
    @Column(name = "work_loc", length = 16777215)
    private String workLoc;
    @Column(name = "suc_count")
    private Integer sucCount;
    @Column(name = "dif_count")
    private Integer difCount;
    @Size(max = 10)
    @Column(name = "sapphire_upload_status", length = 10)
    private String sapphireUploadStatus;
    @Lob
    @Size(max = 16777215)
    @Column(name = "gtlf_file_loc", length = 16777215)
    private String gtlfFileLoc;

    //@Lob
    //@Column(name ="gtlf_file")
    //private byte[] gtlfFile;
    @JoinColumn(name = "component", referencedColumnName = "id")
    @ManyToOne
    private Component component;
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "stage", referencedColumnName = "id")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "testunit", referencedColumnName = "id")
    @ManyToOne
    private Testunit testunit;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;

    transient private Logger logger;

    private transient File fileToRun;

    public RegressDetails() {

    }

    public RegressDetails(Long id) {
        this();
        this.id = id;
    }

    public String getLocation() {
        String loc = AbstractStageRun.ROOT_FOLDER + "/" + getStage().getRelease().getName() + "/" + getStage().getStageName()
                + "/" + getProduct().getName() + "/" + getComponent().getName() + "/" + getTestunit().getPlatform().getName() + "/" + getTestunit().getTestunitName();
        File f = new File(loc);
        if (!f.exists()) {
            f.mkdirs();
        }
        return loc;
    }

    public Logger getLogger() {
        synchronized (this) {
            if (logger != null) {
                return logger;
            } else {
                logger = Logger.getLogger("stageruner_log" + id);
                Handler consoleHandler = null;
                Handler fileHandler = null;
                try {
                    //Creating consoleHandler and fileHandler
                    consoleHandler = new ConsoleHandler();
                    fileHandler = new FileHandler(getLocation() + "/" + id + ".log");

                    //Assigning handlers to LOGGER object
                    logger.addHandler(consoleHandler);
                    logger.addHandler(fileHandler);

                    //Setting levels to handlers and LOGGER
                    consoleHandler.setLevel(Level.ALL);
                    fileHandler.setLevel(Level.ALL);

                    SimpleFormatter simpleFormatter = new SimpleFormatter();
                    fileHandler.setFormatter(simpleFormatter);
                    logger.setLevel(Level.ALL);
                    logger.config("Configuration done.");

                } catch (IOException e) {
                    e.printStackTrace();
                    logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
                }
                return logger;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFarmrunId() {
        return farmrunId;
    }

    public void setFarmrunId(Integer farmrunId) {
        this.farmrunId = farmrunId;
    }

    public RegressStatus getStatus() {
        return status;
    }

    public void setStatus(RegressStatus status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public String getGtlfFileLoc() {
        return gtlfFileLoc;
    }

    public void setGtlfFileLoc(String gtlfFileLoc) {
        this.gtlfFileLoc = gtlfFileLoc;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Testunit getTestunit() {
        return testunit;
    }

    public void setTestunit(Testunit testunit) {
        this.testunit = testunit;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public File getFileToRun() {
        return fileToRun;
    }

    public void setFileToRun(File fileToRun) {
        this.fileToRun = fileToRun;
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
        return "RegressDetails[ id=" + id + "," + stage + product + component + testunit + " ]";
    }

}
