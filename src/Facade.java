
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.rmi.CORBA.Util;

import Entities.Message;
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
        Topic t = new Topic(titre, null);
        em.persist(t);
    }

    public void supprimerTopic(int identifer){
        this.em.createNativeQuery("remove * from Topic t where t.id = :identifier").setParameter("identifier", identifer);
    }

    /**
     * Liste les message d'un topic donné
     */
    public List<Message> ListerTopicMessages(){return null;}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void ajoutMessage(int topicId, String pseudoAuteur, int jour, int mois, int an, String contenu) {
        Topic t = getTopic(topicId);
        Utilisateur auteur = rechercherUtilisateur(pseudoAuteur);
        Message m = new Message(auteur, jour, mois, an, contenu);
        em.persist(m);
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
}