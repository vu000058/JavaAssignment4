package com.algonquincollge.cst8277.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;

@Entity
@EntityListeners({AuditListener.class})
public class Item extends ModelBase implements Serializable {
    private String name;
    private String description;
    private double price;
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    
    public Item() {
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonbTransient
    @OneToMany(mappedBy = "item")
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
