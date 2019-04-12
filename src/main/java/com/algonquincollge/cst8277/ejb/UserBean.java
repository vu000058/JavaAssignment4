package com.algonquincollge.cst8277.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollge.cst8277.model.Cart;
import com.algonquincollge.cst8277.model.PlatformUser;

@Stateless
public class UserBean {
    @PersistenceContext(unitName = "assignment4")
    protected EntityManager em;
    
    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;
    
    public List<PlatformUser> getUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PlatformUser> cq = cb.createQuery(PlatformUser.class);
        Root<PlatformUser> rootEntry = cq.from(PlatformUser.class);
        CriteriaQuery<PlatformUser> all = cq.select(rootEntry);
        TypedQuery<PlatformUser> allQuery = em.createQuery(all);
        
        return allQuery.getResultList();
    }
    
    public PlatformUser getUser(int id) {
        PlatformUser user = em.find(PlatformUser.class, id);
        return user;
    }
    
    
    public int addUserCart(Cart cart) {
        em.persist(cart);
        em.flush();
        return cart.getId();
    }
    
    public int addUser(PlatformUser user) {
        user.setPwHash(pbAndjPasswordHash.generate(user.getPassword().toCharArray()));
        em.persist(user);
        em.flush();
        return user.getId();
    }
    
    public void updateUser(PlatformUser user) {
        em.merge(user);
    }
}
