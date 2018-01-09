<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
	Collection<Topic> topics = ...%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accueil</title>
	</head>
	
	<body>
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="bienvenue"></p>
		<p><input type="submit" value="Mon compte"></p>
		<p><input type="hidden" name="op" value="moncompte"></p><br>
		<b>LISTE DES TOPICS : </b><br>
		<%
		if (topics != null) {
			for (Topic t : topics) {%>
			● <a href = "topic.html"><%= t.titre %></a><br>
			<%
			}
		}%>
	</body>
</html>