package serv;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.Adresse;
import facade.Facade;
import facade.Personne;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade f;

    /**
     * Default constructor. 
     */
    public Serv() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// response.setContentType("text/html");
		String op = request.getParameter("op");
		switch(op) {
		case "ajoutp" :
			// ajout d'une personne
			request.getRequestDispatcher("ajoutp.html").forward(request, response);
			break;
		case "Vajoutp" :
			// valider l'ajout d'une personne
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			f.ajoutPersonne(nom, prenom);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "ajouta" :
			// ajout d'une adresse
			request.getRequestDispatcher("ajouta.html").forward(request, response);
			break;
		case "Vajouta" :
			// valider l'ajout d'une adresse
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			f.ajoutAdresse(rue, ville);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "associer" :
			// Associer une personne à une adresse
			Collection<Personne> tablePersonnes = f.listePersonnes();
			Collection<Adresse> tableAdresses = f.listeAdresses();
			request.setAttribute("tablePersonnes", tablePersonnes);
			request.setAttribute("tableAdresses", tableAdresses);
			request.getRequestDispatcher("associer.jsp").forward(request, response);
			break;
		case "Vassocier" :
			// valider l'association d'une personne à une adresse
			String idPersonne = request.getParameter("idPersonne");
			String idAdresse = request.getParameter("idAdresse");
			int idP = Integer.parseInt(idPersonne);
			int idA = Integer.parseInt(idAdresse);
			f.associer(idP, idA);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "lister" :
			// Lister les personnes et leurs adresses
			Collection<Personne> listePersonnes = f.listePersonnes();
			request.setAttribute("listePersonnes", listePersonnes);
			request.getRequestDispatcher("lister.jsp").forward(request, response);
		}
	}

}
