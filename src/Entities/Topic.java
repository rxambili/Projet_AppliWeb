package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	
	private String titre;
	@ManyToOne(fetch=FetchType.EAGER)
	private Utilisateur createur;
	@OneToMany(mappedBy = "topic", fetch=FetchType.EAGER)
	private List<Message> messages;
	
	public Topic(String titre, Utilisateur createur) {
		this.titre = titre;
		this.createur = createur;
		messages = new ArrayList<>();
	}
	
	public String getTitre() {
		return this.titre;
	}
	public Utilisateur getCreateur() {
		return this.createur;
	}
	public List<Message> getMessages() {
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
		messages.add(message);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Message getMessage(int numero) {
		return messages.get(numero);
	}
	
	public int getNbMessages() {
		return messages.size();
	}

	public void ajoutMessage(String utilisateur, int jour, int mois, int an, String contenu){}

    public int getId() {
        return id;
    }
}
