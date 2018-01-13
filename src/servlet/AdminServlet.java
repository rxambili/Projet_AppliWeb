package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servelet regroupant toutes les actions necessitant d'être connecté en tant qu'administrateur.
 *      La vérification que l'utilisateur est connecté est automatique.
 */

@WebServlet("/Admin")
public class AdminServlet extends Serv {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String op = request.getParameter("op");
        if (op != null) {
            switch (op) {

                default:
                    DisplayErrorPage("Requete non reconnue par Admin", "", request, response);
            }
        } else {
            request.getRequestDispatcher("").forward(request, response);
        }
    }
}

