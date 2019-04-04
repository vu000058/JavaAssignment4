package com.algonquincollge.cst8277.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class ModelBase implements Auditable {

    protected int id;
    protected int version;
    protected Audit audit = new Audit();

    public ModelBase() {
        audit.setCreatedDate(LocalDateTime.now());
        audit.setUpdatedDate(this.audit.getCreatedDate());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }

    public Audit getAudit() {
        return audit;
    }
    
    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
