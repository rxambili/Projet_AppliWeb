<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, facade.*, Entities.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mon compte</title>
	</head>
	
	<body>
		<form method="get" action="Serv">
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="deconnexion"></p>
		</form>
		<form method="get" action="Serv">
		<p><input type="submit" value="< page Accueil"></p>
		<p><input type="hidden" name="op" value="accueil"></p>
		</form><br>
		<%
		Utilisateur p = (Utilisateur) request.getAttribute("sessionUser");%>
		Mon nom : <i><%= p.getNom() %></i>
		Mon prénom : <i><%= p.getPrenom() %></i>
		Mon pseudonyme : <i><%= p.getPseudo() %></i>
		Mon mot de passe : <i><%= p.getMdp() %></i>
	</body>
</html>