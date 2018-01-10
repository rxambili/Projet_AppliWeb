package Entities;

public class Utilisateur {
	
	private String nom;
	private String prenom;
	private String pseudo;
	private String mdp;
	
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
