
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Message;
import Entities.Topic;
import Entities.Utilisateur;


@Singleton
public class Facade {
    @PersistenceContext
    EntityManager em;


    public Facade(){}

    public void ajoutUtilisateur(String nom, String prenom, String pseudo, String mdp) {
        Utilisateur u = new Utilisateur(nom, prenom, pseudo, mdp);
        this.em.persist(u);
    }

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void SupprimerUtilisateur(String pseudo) {
        this.em.createNativeQuery("remove * from utilisateurs where pseudo = 'pseudo'");
    }
    public void SupprimerUtilisateur(int identifier){}

    public Utilisateur rechercherUtilisateur(String pseudo){ return null; }

    /**
     * Liste les topics visibles
     */
    public List<Topic> ListerTopics(){return null;}
    public Topic getCurrentTopic(){return null;}
    public void ajoutTopic(String titre){}

    public void supprimerTopic(int identifer){}

    /**
     * Liste les message d'un topic donné
     */
    public List<Message> ListerTopicMessages(){return null;}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void ajoutMessage(int topicId, String pseudo, int jour, int mois, int an, String contenu) {}
    public void ajoutMessage(int topicId, int UtilisateurId, Date date, String contenu) {}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void supprimerMessage(int identifier) {}
    public void supprimerMessage(int topicIdentifier, int index) {}
}