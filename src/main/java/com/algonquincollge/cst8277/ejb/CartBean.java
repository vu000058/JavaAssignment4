package com.algonquincollge.cst8277.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import com.algonquincollge.cst8277.model.Cart;
import com.algonquincollge.cst8277.model.CartItem;
import com.algonquincollge.cst8277.model.Item;

@Stateless
public class CartBean {
    
    @PersistenceContext(unitName = "assignment4")
    protected EntityManager em;
    
    public Cart getCart(int id) {
        Cart cart = em.find(Cart.class, id);
        return cart;
    }
    
    public void addItemToCart(int cartId, int itemId, CartItem cartItem) {
        Cart cart = em.find(Cart.class, cartId);
        Item item = em.find(Item.class, itemId);
        
        if (cart == null) {
            throw new IllegalArgumentException("Cart with id: " + cartId + " does not exist.");
        }
        
        if (item == null) {
            throw new IllegalArgumentException("Item with id: " + itemId + " does not exist.");
        }
        
        boolean existed = false;
        
        for (CartItem ci : cart.getCartItems()) {
            if (ci.getItem().getId() == item.getId()) {
                ci.setQuantity(cartItem.getQuantity());
                em.merge(ci);
                existed = true;
            }
        }
        
        if (!existed) {
            cart.getCartItems().add(cartItem);
            cartItem.setItem(item);
            cartItem.setCart(cart);
            em.persist(cartItem);
        }
           
        em.merge(cart);
    }
    
    public void deleteItemInCart(int cartId, int itemId) {
        Cart cart = em.find(Cart.class, cartId);
        CartItem deleteItem = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getItem().getId() == itemId) {
                deleteItem = cartItem;
            }
        }
        
        if (deleteItem != null) {
            cart.getCartItems().remove(deleteItem);
            em.merge(cart);
        }
    }
    
    public void deleteCart(int id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Cart> delete = builder.createCriteriaDelete(Cart.class);
        Root<Cart> root = delete.from(Cart.class);
        delete.where(builder.and(builder.equal(root.get("id"), id)));
        em.createQuery(delete).executeUpdate();
    }
}
