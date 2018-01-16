package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Topic;
import Entities.Utilisateur;

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
        int userID=(int)session.getAttribute("sessionUserID");
        Utilisateur user = f.rechercherUtilisateur(userID);
        if(op!=null) {
            switch (op) {
                case "accueil":
                    DisplayTopicList(user, request, response);
                    break;
                case "deconnexion":
                    session.setAttribute("sessionUserID", null);
                    session.setAttribute("admin", null);
                    request.getRequestDispatcher("bienvenue.html").forward(request, response);
                    break;
                case "Vcreationtopic":
                    String titre = request.getParameter("titre");
                    boolean isPublic = request.getParameter("isPublic").equals("true");
                    f.ajoutTopic(titre, user, isPublic);
                    DisplayTopicList(user, request, response);
                    break;
                case "moncompte":
                    request.setAttribute("sessionUser", user);
                    request.getRequestDispatcher("mon_compte.jsp").forward(request, response);
                    break;
                case "creationtopic":
                    request.getRequestDispatcher("creation_topic.html").forward(request, response);
                    break;
                case "afficherTopic":
                    topicId = Integer.parseInt(request.getParameter("topicId"));
                    DisplayTopic(topicId, user, request, response);
                    break;
                case "Vcommentaire":
                    //Date date = null;
                    Calendar cal = Calendar.getInstance();
                    //cal.setTime(date);
                    int an = cal.get(Calendar.YEAR);
                    int mois = cal.get(Calendar.MONTH);
                    int jour = cal.get(Calendar.DAY_OF_MONTH);
                    String contenu = request.getParameter("contenu");
                    topicId = Integer.parseInt(request.getParameter("topicId"));
                    f.ajoutMessage(topicId, user.getPseudo(), jour, mois + 1, an, contenu);
                    DisplayTopic(topicId, user, request, response);
                    break;

                case "invite":
                    Utilisateur inviteUser = f.rechercherUtilisateur(request.getParameter("pseudo"));
                    Topic t = f.getTopic(Integer.parseInt(request.getParameter("topicId")));
                    if (t.canInvite(user) && inviteUser!=null) {
                        f.invite(inviteUser, t);
                    }else{
                        //TODO fail, pas le droit ou le pseudo invité n'existe pas
                    }
                    DisplayTopic(t.getId(), user, request, response);
                case "devenirVIP":
                	request.getRequestDispatcher("devenir_VIP.html").forward(request, response);
                case "Vpaiement":
                	request.getRequestDispatcher("mon_compte.jsp").forward(request, response);
                	String nom_u = request.getParameter("utilisateur");
                	Utilisateur u = f.rechercherUtilisateur(nom_u);
                    Calendar d = Calendar.getInstance();
                	int v = Integer.parseInt(request.getParameter("duree"));
                	f.ajoutLogPayement(u, d, v);
                	Calendar fin = d;
                	fin.add(Calendar.DATE, v);
                	f.setFinVIP(u, fin);
                default:
                    DisplayErrorPage("Requete non reconnue par Public", "", request, response);
            }
        }else {
            DisplayTopicList(user, request,response);
        }

    }
}
