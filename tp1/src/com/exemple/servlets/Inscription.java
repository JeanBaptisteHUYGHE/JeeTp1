package com.exemple.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exemple.bdd.DAOContext;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.InscriptionForm;

@WebServlet(name="Inscription", urlPatterns="/inscription")
public class Inscription extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE      = "/WEB-INF/inscription.jsp";
    public static final String REDIRECT_ON_SUCCES = "/connexion";

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm();

        /*
         * Appel au traitement et à la validation de la requête, et récupération
         * du bean en résultant
         */
        Utilisateur utilisateur = form.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        
        /* Si l'inscription c'est deroule avec succes */
        if (form.getResultat().equals("Succès de l'inscription.")) {
        	response.sendRedirect(request.getContextPath() + REDIRECT_ON_SUCCES);
        }
        else {
        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
        
    }
}