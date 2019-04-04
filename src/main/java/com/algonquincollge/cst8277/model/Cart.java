package com.algonquincollge.cst8277.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private PlatformUser user;
    
    public Cart() {
        
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public PlatformUser getUser() {
        return user;
    }

    public void setUser(PlatformUser user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
