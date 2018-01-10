package Entities;

import java.util.HashMap;

public class Topic {
	
	private String titre;
	private Utilisateur createur;
	private HashMap<Integer, Message> messages; // liste des messages avec leurs numeros
	
	public Topic(String titre, Utilisateur createur) {
		this.titre = titre;
		this.createur = createur;
		messages = new HashMap<Integer, Message>();
	}
	
	public String getTitre() {
		return this.titre;
	}
	public Utilisateur getCreateur() {
		return this.createur;
	}
	public HashMap<Integer, Message> getMessages() {
		return this.messages;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}
	public void ajoutMessage(Message message) {
		int n = messages.size();
		messages.put(n+1, message);
	}
	
	public Message getMessage(int numero) {
		return messages.get(numero);
	}
	
	public int getNbMessages() {
		return messages.size();
	}
}
