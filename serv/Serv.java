package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		case "Vinscription" :
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("mdp");
			f.ajoutUtilisateur(nom, prenom, pseudo, mdp);
			request.getRequestDispatcher("bienvenue.html").forward(request, response);
			break;
		case "Vconnexion" :
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("mdp");
			if (mdp == f.getUtilisateur(pseudo).getMotdepasse()) {
				connected = true;
				utilisateur = pseudo;
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("bienvenue.html").forward(request, response);
			}
			break;
		case "Vcreationtopic" :
			String titre = request.getParameter("titre");
			f.ajoutTopic(titre);
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
			break;
		case "Vcommentaire" :
			Date date;
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int an = cal.get(Calendar.YEAR);
			int mois = cal.get(Calendar.MONTH);
			int jour = cal.get(Calendar.DAY_OF_MONTH);
			String contenu = request.getParameter("contenu");
			f.ajoutCommentaire(utilisateur, jour, mois, an, contenu);
			request.getRequestDispatcher("topic.jsp").forward(request, response);
	}

}
