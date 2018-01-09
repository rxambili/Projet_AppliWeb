import Entities.Message;
import Entities.Topic;
import Entities.Utilisateur;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;


@Singleton
public class Facade {
    @PersistenceContext
    EntityManager em;

    public Facade(){}

    public void ajoutUtilisateur(String pseudo, String adresseMail){}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void SupprimerUtilisateur(String pseudo){}
    public void SupprimerUtilisateur(int identifier){}

    public Utilisateur RechercherUtilisateur(String pseudo){}

    /**
     * Liste les topics visibles
     */
    public List<Topic> ListerTopics(){}

    public void ajoutTopic(String titre){}

    public void supprimerTopic(int identifer){}

    /**
     * Liste les message d'un topic donné
     */
    public List<Message> ListerTopicMessages(){}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void ajoutMessage(String pseudo, int jour, int mois, int an, String contenu) {}
    public void ajoutMessage(int Identifier, Date date, String contenu) {}

    // A voir quel signature est la plus pratique selon la BDD (potentiellement les deux selon les cas)
    public void supprimerMessage(int identifier) {}
    public void supprimerMessage(int topicIdentifier, int index) {}
}
