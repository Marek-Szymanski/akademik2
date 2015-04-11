/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Komputer
 */
public class messageKontroler {
    
    public void rejestracja() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dokonano rejestracji", "Po weryfikacji przez administratora otrzymasz pełen dostęp do serwisu."
                + " Gdy proces zostanie zakończony otrzymasz maila.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void logwanieError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błędny login lub hasło!", "Skorzystaj z przypomnienia danych logowania, lub skontaktuj się z sdministratorem serwisu"
                + " Dane będąwysłane na podany mail.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void rejestracjaError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "NIE dokonano rejestracji", "Podany login już istnieje.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Wypełnij wszystkie pola!"));
    }
    
}
