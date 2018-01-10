<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, facade.*, Entities.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
	Collection<Topic> topics = (Collection<Topic>) request.getAttribute("ListerTopics");%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accueil</title>
	</head>
	
	<body>
		<form method="get" action="Serv">
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="bienvenue"></p>
		</form>
		<form method="get" action="Serv">
		<p><input type="submit" value="Mon compte"></p>
		<p><input type="hidden" name="op" value="moncompte"></p><br>
		</form>
		<b>LISTE DES TOPICS : </b><br>
		<%
		if (topics != null) {
			for (Topic t : topics) {%>
			● <%--<a href = "Serv?op=afficherTopic"><%= t.titre %></a><br>--%>
			<form method="get" action="Serv">
				<p><input type="hidden" name="op" value="afficherTopic"></p><br>
				<p><input type="hidden" name="op" value="<%=t.getId()%>"></p><br>
				<p><input type="submit" value="<%= t.getTitre()%>"></p>
			</form>
			<%
			}
		}%>
	</body>
</html>