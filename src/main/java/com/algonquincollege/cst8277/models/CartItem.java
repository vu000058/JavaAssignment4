package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.algonquincollege.cst8277.utils.Assignment4Constants;

@Entity
@EntityListeners({AuditListener.class})
@Table(name=Assignment4Constants.CART_ITEM_TABLE_NAME)
public class CartItem extends ModelBase implements Serializable {
    
    private Cart cart;
    private Item item;
    private int quantity;
    
    public CartItem() {
        
    }

    @JsonbTransient
    @ManyToOne
    @JoinColumn
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne
    @JoinColumn
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
