<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Administrateur</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
    	<%@ include file="deconnexion.jsp" %>
        <h1>Bienvenue ${requestScope.nom}</h1>
        
        <h2>Liste des utilisateurs:</h2>
        <form method="post" action="administrateur">
	        <table>
	        	<thead>
	        		<tr>
	        			<th>Id</th>
	        			<th>Nom</th>
	        			<th>Adresse Mail</th>
	        			<th>Date Inscription</th>
	        			<th>Utilisateur à supprimer</th>
	       			</tr>
	    		</thead>
	    		<tbody>
		    		<c:forEach var="user" items="${requestScope.usersList}">
						<tr>
							<td><c:out value="${user.id}"/></td>
							<td><c:out value="${user.nom}"/></td>
							<td><c:out value="${user.email}"/></td>
							<td><c:out value="${user.dateInscription}"/></td>
							<td>
								<c:if test="${not user.admin}">
								<input type="checkbox" name = "removeList" value = "${user.email}">
								</c:if>
							</td>
						</tr>
					</c:forEach>
	    		</tbody>
	        </table>
	        <input type="submit" value="Supprimer les utilisateur selectionnés" />
        </form>
        	
    </body>
</html>