<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Profil</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
    	<%@ include file="deconnexion.jsp" %>
        <h1>Bienvenue ${requestScope.nom}</h1>
        <h2>Voiçi vos informations:</h2>
        <p>Adresse mail: ${requestScope.email}</p>
        <p>Date d'inscription: ${requestScope.dateInscription}</p>
                
    </body>
</html>