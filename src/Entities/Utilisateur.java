package Entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	
	private String nom;
	private String prenom;
	private String pseudo;
	private String mdp;
	private boolean isAdmin;
	private Calendar finVIP;
	private boolean VIP = finVIP.after(Calendar.getInstance());


	@OneToMany(mappedBy="auteur")
	private List<Message> messages;
    @OneToMany(mappedBy="createur")
    private List<Topic> topics;
	@OneToMany(mappedBy="utilisateur")
	private List<Permission> permissions;
	@OneToMany(mappedBy="utilisateur")
	private List<Invitation> invitations;


	public Utilisateur(){}

	public Utilisateur(String nom, String prenom, String pseudo, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.isAdmin = this.pseudo.equals("admin");
		this.VIP = this.isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public List<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}
	public Permission getPermission(int topicId) {
		for(Permission p : this.getPermissions()){
			if (p.topic.id == topicId){
				return p;
			}
		}
		return null;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public boolean isVip() {
		return VIP || isAdmin;
	}

	public void setVip(boolean vip) {
		this.VIP = vip;
	}

	public Calendar getFinVIP() {
		return this.finVIP;
	}
	
	public void setFinVIP(Calendar d) {
		this.finVIP = d;
	}
}
