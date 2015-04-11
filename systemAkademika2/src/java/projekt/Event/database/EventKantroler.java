/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Event.database;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author marekszymanski
 */
@ManagedBean(name = "eventkontroler")
@SessionScoped
public class EventKantroler implements Serializable{
    
    @EJB
    EventSession eventManager;
    private Date date1 = new Date();
    private Date date2 = new Date();
    private EventDatabase eventDB = new EventDatabase();
    private List<EventDatabase> eventList = new ArrayList<>();
    
    public void onDate1Select(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        date1= (Date) event.getObject();
        eventDB.setStartDate(date1); 
        date1 = new Date();
    }
    
    public void onDate2Select(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        date2 = (Date) event.getObject();
        eventDB.setEndDate(date2);
        date2 = new Date();
    }
    
    public void save()
    {
        eventDB.setAccepted(true);
        eventManager.newEvent(eventDB);
        eventDB = new EventDatabase();
    }
    
    public List<EventDatabase> allEv()
    {
        return eventManager.allEv();
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public EventDatabase getEvent() {
        return eventDB;
    }

    public void setEvent(EventDatabase event) {
        this.eventDB = event;
    }   

    public List<EventDatabase> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventDatabase> eventList) {
        this.eventList = eventList;
    }
}
