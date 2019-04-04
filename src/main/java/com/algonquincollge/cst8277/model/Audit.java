package com.algonquincollge.cst8277.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit {
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    
    public Audit() {
    }
    
    @Column(name="created_date")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime date) {
        createdDate = date;
    }
    
    @Column(name="updated_date")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime date) {
        updatedDate = date;
    }
}
