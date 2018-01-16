<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, facade.*, Entities.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mon compte</title>
	</head>
	
	<body>
		<form method="get" action="Restricted">
		<p><input type="submit" value="Déconnexion"></p>
		<p><input type="hidden" name="op" value="deconnexion"></p>
		</form>
		<form method="get" action="Restricted">
		<p><input type="submit" value="< page Accueil"></p>
		<p><input type="hidden" name="op" value="accueil"></p>
		</form><br>
		<%
		Utilisateur p = (Utilisateur) request.getAttribute("sessionUser");
		String VIP;
		if (p.isVIP()) {
			VIP = "oui";
		} else {
			VIP = "non";
		}%>
		Mon nom : <i><%= p.getNom() %></i><br>
		Mon prénom : <i><%= p.getPrenom() %></i><br>
		Mon pseudonyme : <i><%= p.getPseudo() %></i><br>
		Mon mot de passe : <i><%= p.getMdp() %></i><br>
		VIP ? : <i><%= VIP %></i><br><br>
		<br>
		<a href = "Restricted?op=devenirVIP">Devenir VIP</a>
		<br>
		Permissions: <br>
		<%
		List<Topic> permitedTopics = (List<Topic>)request.getAttribute("permitedTopics");
			if (permitedTopics.size() == 0) out.println("aucune");
			else {
				for (Topic t : permitedTopics) {%>

		<form method="get" action="Restricted" >
			- <%=t.getTitre()%> <input type="submit" value="Supprimer">
			<input type="hidden" name="op" value="Vdelete_permission">
			<input type="hidden" name="perm_topic" value=<%=t.getId()%>>
			<input type="hidden" name="perm_user" value=<%=p.getId()%>>
		</form>
		<%}}%>
		<br>
		Invitations: <br>
		<%List<Topic> invitedTopics = (List<Topic>)request.getAttribute("invitedTopics");
			if (invitedTopics.size() == 0) out.println("aucune");
			else {
				for (Topic t : invitedTopics) {%>
		<form method="get" action="Restricted" >
			- <%=t.getTitre()%> <input type="submit" value="Accepter">
			<input type="hidden" name="op" value="Vaccepter_invitation">
			<input type="hidden" name="perm_topic" value=<%=t.getId()%>>
			<input type="hidden" name="perm_user" value=<%=p.getId()%>>
		</form>
		<form method="get" action="Restricted" >
			<input type="submit" value="Supprimer">
			<input type="hidden" name="op" value="Vdelete_permission">
			<input type="hidden" name="perm_topic" value=<%=t.getId()%>>
			<input type="hidden" name="perm_user" value=<%=p.getId()%>>
		</form>
		<%}}%>
	</body>
</html>