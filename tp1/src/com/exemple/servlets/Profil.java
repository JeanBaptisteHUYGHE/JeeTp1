package com.exemple.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.ConnexionForm;

@WebServlet(name="Profil", urlPatterns="/profil")
public class Profil extends HttpServlet {
    public static final String ATT_USER_NAME    			= "nom";
    public static final String ATT_USER_EMAIL   			= "email";
    public static final String ATT_USER_INSCRIPTION_DATE	= "dateInscription";
    public static final String VUE              			= "/WEB-INF/profil.jsp";


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
    	request.setAttribute( ATT_USER_EMAIL, utilisateur.getEmail());
    	request.setAttribute( ATT_USER_INSCRIPTION_DATE, utilisateur.getDateInscription());
    	
        /* Affichage de la page de l'utilisateur classique */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	/* Affichage de la page de l'utilisateur classique */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}