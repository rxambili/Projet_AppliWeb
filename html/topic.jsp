<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
	Topic t = ...%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Topic : <%= t.getTitre() %></title>
	</head>
	
	<body>
		<form method="get" action="Serv">
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="bienvenue"></p>
		</form>
		<form method="get" action="Serv">
		<p><input type="submit" value="< page Accueil"></p>
		<p><input type="hidden" name="op" value="accueil"></p>
		</form>
		<form method="get" action="Serv">
		<p><input type="submit" value="Mon compte"></p>
		<p><input type="hidden" name="op" value="moncompte"></p>
		</form><br>
		<b>TOPIC : </b><%= t.getTitre() %><br>
		<%
		Collection<Message> messages = t.getMessages();
		if (messages != null) {
			for (Message m : messages) {%>
			● <i><%= m.getAuteur() + " ()" + m.getJour() + "/" + m.getMois() + "/" + m.getAnnee() + ") :" %></i>
			<%= m.getContenu() %><br>
			<%
			}
		}%>
		<b>Ecrire un commentaire :</b><br>
		<form method="post" action="Serv">
		<p><input type="text" name="contenu"></p>
		<p><input type="submit" value="VALIDER"></p>
		<p><input type="hidden" name="op" value="Vcommentaire"></p>
		</form>
	</body>
</html>