<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, Entities.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
	List<Topic> topics = (List<Topic>) request.getAttribute("ListeTopics");%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accueil</title>
	</head>
	
	<body>
		<form method="get" action="Serv">
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="deconnexion"></p>
		</form>
		<!--<form method="get" action="Serv">
		<p><input type="submit" value="Mon compte"></p>
		<p><input type="hidden" name="op" value="moncompte"></p><br>
		</form>-->
		<b>LISTE DES TOPICS : </b><br>
		<form method="get" action="Serv">
			<input type="submit" value="Nouveau Topic">
			<input type="hidden" name="op" value="creationtopic"><br>
		</form>
		<%
		if (topics != null) {
			for (Topic t : topics) {%>
			<a href = "Serv?op=afficherTopic&topicId=<%= t.getId() %>">● <%= t.getTitre() %></a><br>
<%--<form method="get" action="Serv">
    <p><input type="hidden" name="op" value="afficherTopic"></p><br>
    <p><input type="hidden" name="topicId" value="<%=t.getId()%>"></p><br>
    <p><input type="submit" value="<%= t.getTitre()%>"></p>
</form>--%>
<%
}
}%>
</body>
</html>