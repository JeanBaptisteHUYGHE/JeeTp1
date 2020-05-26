package com.exemple.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.bdd.UtilisateurDAO;
import com.exemple.beans.Utilisateur;

@WebServlet(name="Administrateur", urlPatterns="/administrateur")
public class Administrateur extends HttpServlet {
    public static final String ATT_USER_NAME    			= "nom";
    public static final String ATT_USERS_LIST      			= "usersList";
    private static final String CHAMP_DELETE 				= "removeList";
    public static final String VUE              			= "/WEB-INF/administrateur.jsp";


    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        /* Stockage des information de session dans l'objet request */
    	Utilisateur utilisateur = (Utilisateur) session.getAttribute(Connexion.ATT_SESSION_USER);
    	request.setAttribute( ATT_USER_NAME, utilisateur.getNom());

    	/* Recuppere la liste des utilisateurs */
    	ArrayList<Utilisateur> users = UtilisateurDAO.getUtilisateursList();
    	System.out.println(users);
    	request.setAttribute( ATT_USERS_LIST, users);
    	
        /* Affichage de la page de l'utilisateur classique */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	String mailsList[] = request.getParameterValues(CHAMP_DELETE);
    	for (String email : mailsList) {
    		UtilisateurDAO.removeUtilisateurByEmail(email);
    	}
    	
    	/* Faire la meme choe que la methode get */
    	doGet(request, response);
    }
}