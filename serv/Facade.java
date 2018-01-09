import Entities.Message;
import Entities.Topic;
import Entities.Utilisateur;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Singleton
public class Facade {
    @PersistenceContext
    EntityManager em;

    public Facade(){}

    public void ajoutUtilisateur(){}

    public void SupprimerUtilisateur(){}

    public Utilisateur RechercherUtilisateur(){}

    /**
     * Liste les topics visibles
     */
    public List<Topic> ListerTopics(){}

    public void ajoutTopic(String titre){}

    public void supprimerTopic(){}

    /**
     * Liste les message d'un topic donné
     */
    public List<Message> ListerTopicMessages(){}

    public void ajoutMessage(String utilisateur, int jour, int mois, int an, String contenu) {}

    public void supprimerMessage() {}
}
