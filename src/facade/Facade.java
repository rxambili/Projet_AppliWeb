package facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Invitation;
import Entities.LogPayement;
import Entities.Message;
import Entities.Permission;
import Entities.Topic;
import Entities.Utilisateur;


@Singleton
public class Facade {
    @PersistenceContext
    EntityManager em;


    public Facade(){
        //Utilisateur defautAdmin = new Utilisateur("admin", "admin", "admin", "admin"); // TODO supprimer en prod
        //this.em.persist(defautAdmin);
    }

    public void ajoutUtilisateur(String nom, String prenom, String pseudo, String mdp) {
        ajoutUtilisateurAndReturn(nom, prenom, pseudo, mdp);
    }
    public Utilisateur ajoutUtilisateurAndReturn(String nom, String prenom, String pseudo, String mdp) {
        Utilisateur u = new Utilisateur(nom, prenom, pseudo, mdp);
        this.em.persist(u);
        return u;
    }

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void SupprimerUtilisateur(String pseudo) {
        this.em.createNativeQuery("remove * from Utilisateur u where u.pseudo = :pseudo").setParameter("pseudo", pseudo);
    }
    public void SupprimerUtilisateur(int identifier){
        this.em.createNativeQuery("remove * from Utilisateur u where u.id = :id").setParameter("id", identifier);
    }

    public Utilisateur rechercherUtilisateur(String pseudo){
        TypedQuery<Utilisateur> req = em.createQuery("select u from Utilisateur u where u.pseudo = :pseudo",
                Utilisateur.class).setParameter("pseudo", pseudo).setMaxResults(1);
        List<Utilisateur> results = req.getResultList();
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }
    
    public Utilisateur rechercherUtilisateur(int id){
        TypedQuery<Utilisateur> req = em.createQuery("select u from Utilisateur u where u.id = :id",
                Utilisateur.class).setParameter("id", id).setMaxResults(1);
        List<Utilisateur> results = req.getResultList();
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    public List<Utilisateur> listerUtilisateurs(){
        TypedQuery<Utilisateur> req = em.createQuery("select u from Utilisateur u",
                Utilisateur.class);
        return req.getResultList();
    }

    /**
     * Liste les topics visibles
     */
    public List<Topic> ListerTopics(){return em.createQuery("select t from Topic t",
            Topic.class).getResultList();}
    public List<Topic> ListerTopics(Utilisateur user){
        if (user.isVip()){
            return ListerTopics();
        }
        return em.createQuery("select distinct t from Topic t, Permission p " +
                        "where (t.createur.id = :userId or (t.id = p.topic.id and p.utilisateur.id = :userId))", // or t.isPublic
            Topic.class)
                .setParameter("userId", user.getId())
                .setParameter("userId", user.getId())
                .getResultList();
    }

    public Topic getTopic(int topicId){
        TypedQuery<Topic> req = em.createQuery("select t from Topic t where t.id = :identifier",
                Topic.class).setParameter("identifier", topicId).setMaxResults(1);
        List<Topic> results = req.getResultList();
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }
    public void ajoutTopic(String titre){
        ajoutTopic(titre, null);
    }
    public void ajoutTopic(String titre, Utilisateur proprietaire){
        ajoutTopic(titre, proprietaire, true);
    }
    public void ajoutTopic(String titre, Utilisateur proprietaire, boolean isPublic){
        Topic t = new Topic(titre, proprietaire);
        t.setPublic(isPublic);
        em.persist(t);

        Permission p = new Permission();
        em.persist(p);
        p.setTopic(t);
        p.setUtilisateur(proprietaire);
        p.setDroit_invitation(true);
        //... TODO
    }

    public void supprimerTopic(int identifer){
        this.em.createNativeQuery("remove * from Topic t where t.id = :identifier").setParameter("identifier", identifer);
    }

    /**
     * Liste les message d'un topic donné
     */
    public List<Message> ListerTopicMessages(int topicId){
        return getTopic(topicId).getMessages();
    }

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void ajoutMessage(int topicId, String pseudoAuteur, int jour, int mois, int an, String contenu) {
        Topic t = getTopic(topicId);
        Utilisateur auteur = rechercherUtilisateur(pseudoAuteur);
        Message m = new Message(null, jour, mois, an, contenu);
        em.persist(m);
        m.setAuteur(auteur);
        m.setTopic(t);
    }

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void supprimerMessage(int identifier) {
        this.em.createNativeQuery("remove * from Message m where m.id = :identifier").setParameter("identifier", identifier);
    }
    public void supprimerMessage(int topicIdentifier, int numero) {
        this.em.createNativeQuery("remove * from Message m where m.topic.id = :topicId and m.numero = :num")
                .setParameter("topicId", topicIdentifier)
                .setParameter("num", numero);
    }

    public void invite(Utilisateur inviteUser, Topic t) {
        // Vérifier que la personne n'a pas déjà été invitée
        for (Permission p : t.getPermissions()){
            if (p.getUtilisateur().getId() == inviteUser.getId()) return;
        }
        for (Invitation i : t.getInvitations()){
            if (i.getUtilisateur().getId() == inviteUser.getId()) return;
        }
        Invitation i = new Invitation(t, inviteUser);
        this.em.persist(i);
        i.setTopic(t);

    }

    public List<Utilisateur> getInvitations(Topic t) {
        return em.createQuery("select distinct u from Utilisateur u, Topic t, Invitation i " +
                        "where i.utilisateur.id = u.id and i.topic.id = t.id and t.id = :topicId",
                Utilisateur.class)
                .setParameter("topicId", t.getId())
                .getResultList();
    }

    public List<Utilisateur> getPermissions(Topic t) {
        return em.createQuery("select distinct u from Utilisateur u, Topic t, Permission p " +
                        "where p.utilisateur.id = u.id and p.topic.id = t.id and t.id = :topicId",
                Utilisateur.class)
                .setParameter("topicId", t.getId())
                .getResultList();
    }
    
    public void ajoutLogPayement(Utilisateur u, Calendar d, int v) {
    	LogPayement log = new LogPayement(u, d, v);
    	em.persist(log);
    }
    
    public void setFinVIP(Utilisateur u, Calendar d) {
    	u.setFinVIP(d);
    }
}