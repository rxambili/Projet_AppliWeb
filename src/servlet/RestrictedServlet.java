package servlet;

import Entities.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

/**
 * Servelet regroupant toutes les actions necessitant d'être connecté.
 *      La vérification que l'utilisateur est connecté est automatique.
 */

@WebServlet("/Restricted")
public class RestrictedServlet extends Serv{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Vérification de la session de l'utilisateur
        HttpSession session = request.getSession();
        String op = request.getParameter("op");
        int topicId;
        int userID;
        Utilisateur user;
        if(op!=null) {
            switch (op) {
                case "accueil":
                    DisplayTopicList(request, response);
                    break;
                case "deconnexion":
                    session.setAttribute("sessionUserID", null);
                    session.setAttribute("admin", null);
                    request.getRequestDispatcher("bienvenue.html").forward(request, response);
                    break;
                case "Vcreationtopic":
                    //CheckUserConnected(session, request, response);
                    String titre = request.getParameter("titre");
                    userID = (int) session.getAttribute("sessionUserID");
                    Utilisateur createur = f.rechercherUtilisateur(userID);
                    f.ajoutTopic(titre, createur);
                    DisplayTopicList(request, response);
                    break;
                case "moncompte":
                    //CheckUserConnected(session, request, response);
                    userID = (int) session.getAttribute("sessionUserID");
                    user = f.rechercherUtilisateur(userID);
                    request.setAttribute("sessionUser", user);
                    request.getRequestDispatcher("mon_compte.jsp").forward(request, response);
                    break;
                case "creationtopic":
                    //CheckUserConnected(session, request, response);
                    request.getRequestDispatcher("creation_topic.html").forward(request, response);
                    break;
                case "afficherTopic":
                    //CheckUserConnected(session, request, response);
                    topicId = Integer.parseInt(request.getParameter("topicId"));
                    DisplayTopic(topicId, request, response);
                    break;
                case "Vcommentaire":
                    //CheckUserConnected(session, request, response);
                    //Date date = null;
                    Calendar cal = Calendar.getInstance();
                    //cal.setTime(date);
                    int an = cal.get(Calendar.YEAR);
                    int mois = cal.get(Calendar.MONTH);
                    int jour = cal.get(Calendar.DAY_OF_MONTH);
                    String contenu = request.getParameter("contenu");
                    topicId = Integer.parseInt(request.getParameter("topicId"));
                    userID = (int) session.getAttribute("sessionUserID");
                    user = f.rechercherUtilisateur(userID);
                    f.ajoutMessage(topicId, user.getPseudo(), jour, mois + 1, an, contenu);
                    DisplayTopic(topicId, request, response);
                    break;
                default:
                    DisplayErrorPage("Requete non reconnue par Public", "", request, response);
            }
        }else {
            DisplayTopicList(request,response);
        }

    }
}
