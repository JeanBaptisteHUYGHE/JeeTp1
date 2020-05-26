package com.exemple.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.servlets.Connexion;

@WebFilter(filterName="LoggedInUserFilter", urlPatterns= {"/profil", "/administrateur"})
public class LoggedInUserFilter implements Filter {
	
	public static final String REDIRECT_ON_CONNECTION_PAGE = "/";
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoggedInUserFilter");
		
		/* Cast des objets request et response */
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
		/* Récupération de la session depuis la requête */
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute(Connexion.ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
            request.getRequestDispatcher(REDIRECT_ON_CONNECTION_PAGE).forward( request, response );
		}
		else {
			chain.doFilter(request, response);
		}
		
	}

}
