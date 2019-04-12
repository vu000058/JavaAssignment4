package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.PU_NAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_ROLENAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformRole_;
import com.algonquincollege.cst8277.models.PlatformUser;


@Stateless
public class UserBean {
    
    @PersistenceContext(unitName = PU_NAME)
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
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addUserCart(Cart cart) {
        em.persist(cart);
        em.flush();
        return cart.getId();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addUser(PlatformUser user) {
        user.setPwHash(pbAndjPasswordHash.generate(user.getPassword().toCharArray()));
         
        PlatformRole userRole = getRoleByName(USER_ROLENAME);
        if (userRole == null) {
            // add USER_ROLENAME if it does not exist
            userRole = new PlatformRole();
            userRole.setRoleName(USER_ROLENAME);
            em.persist(userRole);
        }
        
        // the default role of a new user is USER_ROLENAME
        user.getPlatformRoles().add(userRole);
      
        em.persist(user);
        em.flush();
        return user.getId();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUser(PlatformUser user) {
        em.merge(user);
    }
    
    private PlatformRole getRoleByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PlatformRole> cq = cb.createQuery(PlatformRole.class);
        Root<PlatformRole> root = cq.from(PlatformRole.class);
        CriteriaQuery<PlatformRole> all = cq.select(root).where(cb.equal(root.get(PlatformRole_.roleName), name));
        TypedQuery<PlatformRole> query = em.createQuery(all);
        List<PlatformRole> roles = query.getResultList();
        
        return roles == null || roles.size() == 0 ? null : roles.get(0);
    }
}
