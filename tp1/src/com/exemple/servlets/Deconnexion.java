package com.exemple.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;


@WebServlet(name="Deconnexion", urlPatterns="/deconnexion")
public class Deconnexion extends HttpServlet {
    public static final String REDIRECT_ON_CONNEXION_PAGE = "/";

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	doPost(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        /* Récupération de la session pour la supprimer */
        HttpSession session = request.getSession();
        session.invalidate(); 
        response.sendRedirect(request.getContextPath() + REDIRECT_ON_CONNEXION_PAGE);
        
    }
}