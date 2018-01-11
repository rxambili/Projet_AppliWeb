package servlet;


import Entities.Topic;
import Entities.Utilisateur;
import facade.Facade;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        String op = request.getParameter("op");
        int topicId;
        int userID;
        Utilisateur user;
        HttpSession session;
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
            case "deconnexion" :
            	session = request.getSession();
            	session.setAttribute("sessionUserID", null);
                request.getRequestDispatcher("bienvenue.html").forward(request, response);
                break;
            case "Vcreationtopic" :
                String titre = request.getParameter("titre");
                session = request.getSession();
                userID = (int) session.getAttribute("sessionUserID");
                Utilisateur createur = f.rechercherUtilisateur(userID);
                f.ajoutTopic(titre, createur);
                DisplayTopicList(request, response);
                break;
            case "accueil" :
                DisplayTopicList(request, response);
                break;
            case "moncompte" :
            	session = request.getSession();
            	userID = (int) session.getAttribute("sessionUserID");
            	user = f.rechercherUtilisateur(userID);
            	request.setAttribute("sessionUser", user);
                request.getRequestDispatcher("mon_compte.jsp").forward(request, response);
                break;
            case "creationtopic" :
                request.getRequestDispatcher("creation_topic.html").forward(request, response);
                break;
            case "afficherTopic" :
                topicId = Integer.parseInt(request.getParameter("topicId"));
                DisplayTopic(topicId, request, response);
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
                	session = request.getSession();
                	session.setAttribute("sessionUserID", u.getId());
                    request.getRequestDispatcher("accueil.jsp").forward(request, response);
                } else {
                    // Mauvais mot de passe
                    // affichage du vrai mdp : POUR LE DEGUB UNIQUEMENT TODO supprimer en prod
                    DisplayErrorPage("mauvais mot de passe, bon mot de passe :'"+u.getMdp()+"'", "connexion.html", request, response);
                }
                break;
            case "Vcommentaire" :
                //Date date = null;
                Calendar cal = Calendar.getInstance();
                //cal.setTime(date);
                int an = cal.get(Calendar.YEAR);
                int mois = cal.get(Calendar.MONTH);
                int jour = cal.get(Calendar.DAY_OF_MONTH);
                String contenu = request.getParameter("contenu");
                topicId = Integer.parseInt(request.getParameter("topicId"));
                session = request.getSession();
                userID = (int) session.getAttribute("sessionUserID");
                user = f.rechercherUtilisateur(userID);
                f.ajoutMessage(topicId, user.getPseudo(), jour, mois + 1, an, contenu);
                DisplayTopic(topicId, request, response);
        }

    }

    protected void DisplayTopicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** envoi la page accueil.jsp
         */
        request.setAttribute("ListeTopics", f.ListerTopics());
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }
    protected void DisplayTopic(int topicId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** envoi une page topic.jsp
         */
        Topic t = f.getTopic(topicId);
        if (t==null){
            // Topic innexistant
            request.setAttribute("ListeTopics", f.ListerTopics());
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        }else {
            request.setAttribute("topic", t);
            request.getRequestDispatcher("topic.jsp").forward(request, response);
        }
    }

    protected void DisplayErrorPage(String message, String redirection, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Envoi une page d'erreur
         */
        request.setAttribute("ErrorMessage", message);
        request.setAttribute("Redirection", redirection);
        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
    }
}