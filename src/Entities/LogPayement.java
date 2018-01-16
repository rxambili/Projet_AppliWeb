package Entities;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogPayement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    
    Utilisateur acheteur;
    Calendar datePayement;
    int dureeValidite;
    
	public LogPayement(Utilisateur acheteur, Calendar datePayement, int dureeValidite) {
		this.acheteur = acheteur;
		this.datePayement = datePayement;
		this.dureeValidite = dureeValidite;
	}
	
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	public Calendar getDatePayement() {
		return datePayement;
	}
	public void setDatePayement(Calendar datePaiement) {
		this.datePayement = datePaiement;
	}
	public int getDureeValidite() {
		return dureeValidite;
	}
	public void setDureeValidite(int dureeValidite) {
		this.dureeValidite = dureeValidite;
	}
    
}
