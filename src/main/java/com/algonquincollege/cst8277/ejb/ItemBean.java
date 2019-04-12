package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Item;
import com.algonquincollege.cst8277.models.Item_;


@Stateless
public class ItemBean {
    
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    public List<Item> getItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> rootEntry = cq.from(Item.class);
        CriteriaQuery<Item> all = cq.select(rootEntry);
        TypedQuery<Item> allQuery = em.createQuery(all);
        
        return allQuery.getResultList();
    }
    
    public int addItem(Item item) {
       em.persist(item);
       em.flush();
       
       return item.getId();
    }
    
    public Item getItem(int id) {
        Item item = em.find(Item.class, id);
        return item;
    }
    
    public void updateItem(Item item) {
        em.merge(item);
    }
    
    public void deleteItem(int id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Item> delete = builder.createCriteriaDelete(Item.class);
        Root<Item> root = delete.from(Item.class);
        delete.where(builder.and(builder.equal(root.get(Item_.id), id)));
        em.createQuery(delete).executeUpdate();
    }
}
