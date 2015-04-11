/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetk.kontroler;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import projekt.messages.messageKontroler;

/**
 *
 * @author Komputer
 */
@ManagedBean
@SessionScoped
public class kontroler {
    //Zmienne !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    //@ManagedProperty(value="#{pearsonkontroler}")
    //private PearsonKontroler pearsonKontroler;
    
    private messageKontroler message;
    public boolean zalogowany = false;
    public kontroler() {
        this.message = new messageKontroler();
        
        
    }
    
}
