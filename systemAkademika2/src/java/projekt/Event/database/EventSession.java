/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Event.database;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author marekszymanski
 */
@Stateless
public class EventSession {

    @PersistenceContext
    private EntityManager manager;
    
    public void newEvent(EventDatabase event)
    {
        if(event.getStartDate().before(event.getEndDate()))
            manager.persist(event);
    }
    
    public void editEv(EventDatabase event)
    {
        
            manager.merge(event);
    }
    
    public List<EventDatabase> allEv() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(EventDatabase.class));
        return manager.createQuery(cq).getResultList();
    }
    
    public EventDatabase byId(int id)
    {
        return manager.find(EventDatabase.class, id);
    }
    
    public List<EventDatabase> byName(String name)
    {
        // Select the employees and the mailing addresses that have the same address.
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(EventDatabase.class);
        cq.multiselect(e);
        cq.where(cb.equal(e.get("name"), name));
        Query query = manager.createQuery(cq);
        List<EventDatabase> result = query.getResultList();
        return result;
    }
}
