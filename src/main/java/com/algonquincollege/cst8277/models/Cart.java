package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@EntityListeners({AuditListener.class})
public class Cart extends ModelBase implements Serializable {
    
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    private Customer customer;
    
    public Cart() {
        
    }
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
