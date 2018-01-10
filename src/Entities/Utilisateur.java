package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	
	private String nom;
	private String prenom;
	private String pseudo;
	private String mdp;

	@OneToMany(mappedBy="auteur")
	private List<Message> messages;
    @OneToMany(mappedBy="createur")
    private List<Topic> topics;


	public Utilisateur(){}

	public Utilisateur(String nom, String prenom, String pseudo, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.mdp = mdp;
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public String getMdp() {
		return this.mdp;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
