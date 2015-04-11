/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.osoba.baza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jboss.weld.context.ejb.Ejb;
import projekt.messages.messageKontroler;
import projekt.osoba.baza.BazaPearson;
import projekt.osoba.baza.OsobaSes;

/**
 *
 * @author marekszymanski
 */
@ManagedBean (name = "pearsonkontroler")
@SessionScoped
public class PearsonKontroler implements Serializable{
    @EJB
    OsobaSes pearsonManager;
    
    private boolean wyswietl;
    private BazaPearson osoba;
    private BazaPearson osobaTemp;
    private List<BazaPearson> listaOsob = new ArrayList<>();
    private List<BazaPearson> listaOsobTemp = new ArrayList<>();
    boolean zalogowany;
    
    private messageKontroler message = new messageKontroler();

    public PearsonKontroler() {
        osoba=new BazaPearson();
        osobaTemp = new BazaPearson();
        zalogowany = false;
        wyswietl = false;
    }
    
    public void addPearson()
    {
        pearsonManager.createOsoba(osoba);
    }
    
    
    public String logowanie()
    {
        BazaPearson temp=osobaPoLogin(osoba.getLogin());
        
        System.out.println(temp.getImie());
        if (temp!=null&&temp.getLogin().equals(osoba.getLogin())&&temp.getHaslo().equals(osoba.getHaslo()))
        {
            osoba=temp;
            String result=przejdzDo(osoba.getStatus());
            return result;
        }
        else
        {
            message.logwanieError();
        }
        return "index.xhtml";
    }
    
    public String wyloguj()
    {
        zalogowany=false;
        osoba = new BazaPearson();
        return "home";
    }
    
    public BazaPearson osobaPoLogin(String log)
    {
        listaOsobTemp=pearsonManager.findLog(log);
        if(listaOsobTemp.isEmpty())
        {
            message.logwanieError();
            return null;
        }
        BazaPearson temp=listaOsobTemp.get(0);
        return temp;
    }
    
    public void editAllPeople()
    {
        for (int i = 0; i < listaOsob.size(); i++) 
        {
            pearsonManager.update(listaOsob.get(i));
        }
        wyswietl = false;
    }
    
    public BazaPearson osobaPoId(int id)
    {
        osoba=pearsonManager.findID(id);
        return osoba;
    }
    
    public String wszystkieOsoby()
    {
        listaOsob=pearsonManager.allPeople();
        return null;
    }
    
    public String przejdzDo(String status)
    {
        if(status.equals("admin"))
        { 
            zalogowany=true;
            return "STARTadmin";
        }
            
        if(status=="brak")
            return null;
        if(status.equals("mieszkaniec"))
        {
            zalogowany = true;
            return "mieszkaniecSTART";
        }     
        if(status=="pracownikStaly")
            return null;
        if(status=="recepcjonista")
            return null;
        if(status=="zablokowany")
            return null;
        return "index";
    }

    public BazaPearson getOsoba() {
        return osoba;
    }

    public void setOsoba(BazaPearson osoba) {
        this.osoba = osoba;
    }

    public List<BazaPearson> getListaOsob() {
        return listaOsob;
    }

    public void setListaOsob(List<BazaPearson> listaOsob) {
        this.listaOsob = listaOsob;
    }

    public List<BazaPearson> getListaOsobTemp() {
        return listaOsobTemp;
    }

    public void setListaOsobTemp(List<BazaPearson> listaOsobTemp) {
        this.listaOsobTemp = listaOsobTemp;
    }

    public boolean isZalogowany() {
        return zalogowany;
    }

    public void setZalogowany(boolean zalogowany) {
        this.zalogowany = zalogowany;
    }

    public messageKontroler getMessage() {
        return message;
    }

    public void setMessage(messageKontroler message) {
        this.message = message;
    }

    public BazaPearson getOsobaTemp() {
        return osobaTemp;
    }

    public void setOsobaTemp(BazaPearson osobaTemp) {
        this.osobaTemp = osobaTemp;
    }

    public boolean isWyswietl() {
        return wyswietl;
    }

    public void setWyswietl(boolean wyswietl) {
        this.wyswietl = wyswietl;
    }
    
}
