package Entities;

public class Message {
	
	private int numero; // position du message dans le topic
	private Utilisateur auteur;
	private int jour;
	private int mois;
	private int an;
	private String contenu;
	
	public Message(Utilisateur auteur, int jour, int mois, int an, String contenu) {
		this.auteur = auteur;
		this.jour = jour;
		this.mois = mois;
		this.an = an;
		this.contenu = contenu;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public Utilisateur getUtilisateur() {
		return this.auteur;
	}
	
	public int getJour() {
		return this.jour;
	}
	public int mgetMois() {
		return this.mois;
	}
	public int getAn() {
		return this.an;
	}
	
	public String getContenu() {
		return this.contenu;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public void setAn(int an) {
		this.an = an;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}
