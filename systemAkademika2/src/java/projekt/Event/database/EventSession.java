/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Event.database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        if(event.getStartDate().after(event.getEndDate()))
            manager.persist(event);
    }
}
