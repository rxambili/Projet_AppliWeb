<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mon compte</title>
	</head>
	
	<body>
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="bienvenue"></p>
		<p><input type="submit" value="< page Accueil"></p>
		<p><input type="hidden" name="op" value="accueil"></p><br>
		<%
		Personne p = ...%>
		Mon nom : <i><%= p.nom %></i>
		Mon prénom : <i><%= p.prenom %></i>
		Mon pseudonyme : <i><%= p.pseudonyme %></i>
		Mon mot de passe : <i><%= p.mdp %></i>
	</body>
</html>