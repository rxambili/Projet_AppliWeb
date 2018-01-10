

import Entities.Topic;
import Entities.Utilisateur;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import Facade;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
public class Serv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    Facade f;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        boolean connected = false; // utilisateur connecte ou non
        String utilisateur = ""; // nom de l'utilisateur connecte
        String op = request.getParameter("op");
        switch(op) {
            case "bienvenue" :
                request.getRequestDispatcher("bienvenue.html").forward(request, response);
                break;
            case "inscription" :
                request.getRequestDispatcher("inscription.html").forward(request, response);
                break;
            case "connexion" :
                request.getRequestDispatcher("connexion.html").forward(request, response);
                break;
            case "accueil" :
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
                break;
            case "moncompte" :
                request.getRequestDispatcher("mon_compte.jsp").forward(request, response);
                break;
            case "creationtopic" :
                request.getRequestDispatcher("creation_topic.html").forward(request, response);
                break;
            case "afficherTopic" :
                int topicId = Integer.parseInt(request.getParameter("topicId"));
                Topic t = f.getTopic(topicId);

                request.getRequestDispatcher("topic.jsp").forward(request, response);
                break;
            case "Vinscription" :
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String pseudo = request.getParameter("pseudo");
                String mdp = request.getParameter("mdp");
                Utilisateur u_cree = f.ajoutUtilisateurAndReturn(nom, prenom, pseudo, mdp);
                request.setAttribute("nom", u_cree.getNom());
                request.setAttribute("prenom", u_cree.getPrenom());
                request.setAttribute("pseudo", u_cree.getPseudo());
                request.setAttribute("mdp", u_cree.getMdp());
                request.getRequestDispatcher("confirmationInscription.jsp").forward(request, response);
                break;
            case "Vconnexion" :
                String pseudo2 = request.getParameter("pseudo");
                String mdp2 = request.getParameter("mdp");
                Utilisateur u = f.rechercherUtilisateur(pseudo2);
                if (u==null) {
                    // le compte n'existe pas
                    String s = "";
                    for (Utilisateur a_user : f.listerUtilisateurs()){
                        s = s + a_user.getPseudo() + "\n"; // POUR LE DEGUB UNIQUEMENT TODO supprimer en prod
                    }
                    DisplayErrorPage("Le compte n'existe pas\nliste des compte :\n"+s, "bienvenue.html", request, response);
                } else if (mdp2.equals(u.getMdp())) {
                    // connexion OK
                    connected = true;
                    request.getRequestDispatcher("accueil.jsp").forward(request, response);
                } else {
                    // Mauvais mot de passe
                    // affichage du vrai mdp : POUR LE DEGUB UNIQUEMENT TODO supprimer en prod
                    DisplayErrorPage("mauvais mot de passe, bon mot de passe :'"+u.getMdp()+"'", "connexion.html", request, response);
                }
                break;
            case "Vcreationtopic" :
                String titre = request.getParameter("titre");
                f.ajoutTopic(titre);
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
                break;
            case "Vcommentaire" :
                Date date = null;
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int an = cal.get(Calendar.YEAR);
                int mois = cal.get(Calendar.MONTH);
                int jour = cal.get(Calendar.DAY_OF_MONTH);
                String contenu = request.getParameter("contenu");
                //TODO recuperer l'id du topic actuel dans les attribut de la requete
                //f.getTopic().ajoutMessage(utilisateur, jour, mois + 1, an, contenu);
                //request.getRequestDispatcher("topic.jsp").forward(request, response);
        }

    }
    protected void DisplayErrorPage(String message, String redirection, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ErrorMessage", message);
        request.setAttribute("Redirection", redirection);
        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
    }
}