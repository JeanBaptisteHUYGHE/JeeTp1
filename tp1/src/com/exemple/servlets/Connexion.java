package com.exemple.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.bdd.UtilisateurDAO;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.ConnexionForm;

@WebServlet(name="Connexion", urlPatterns="/")
public class Connexion extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";
    public static final String REDIRECT_ON_ADMIN_PAGE = "/administrateur";
    public static final String REDIRECT_ON_USER_PAGE = "/profil";

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();
        
        

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
        	utilisateur = UtilisateurDAO.getUtilisateurByEmail(utilisateur.getEmail());
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            /* redirige vers la page de l'utilisateur (admin ou profil)*/
            if (utilisateur.getAdmin()) {
            	System.out.println("Admin connection: " + utilisateur.getNom());
            	response.sendRedirect(request.getContextPath() + REDIRECT_ON_ADMIN_PAGE);
            }
            else {
            	System.out.println("User connection: " + utilisateur.getNom());
            	response.sendRedirect(request.getContextPath() + REDIRECT_ON_USER_PAGE);
            }
        }
        /* si l'utilisateur n'est pas encore connecte */
        else {
            session.setAttribute( ATT_SESSION_USER, null );
            
            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, utilisateur );

            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

        
    }
}