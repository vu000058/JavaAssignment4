package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@EntityListeners({AuditListener.class})
public class Customer extends ModelBase implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private List<Cart> carts = new ArrayList<Cart>();
    private PlatformUser platformUser;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        this.carts.forEach(cart -> cart.setCustomer(this));
    }

    @JsonbTransient
    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    public PlatformUser getPlatformUser() {
        return platformUser;
    }

    public void setPlatformUser(PlatformUser platformUser) {
        this.platformUser = platformUser;
    }
}
