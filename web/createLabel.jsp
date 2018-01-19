<%@ page import="Entities.Label" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Label</title>
</head>
<body>
<form method="get" action="Restricted">
    <p><input type="submit" value="Déconnexion"></p>
    <p><input type="hidden" name="op" value="deconnexion"></p>
</form>
<form method="get" action="Restricted">
    <p><input type="submit" value="< page Accueil"></p>
    <p><input type="hidden" name="op" value="accueil"></p>
</form>
<form method="get" action="Restricted">
    <p><input type="submit" value="Mon compte"></p>
    <p><input type="hidden" name="op" value="moncompte"></p>
</form><br>
<b>LISTE DES LABELS</b>
<% List<Label> labels = (List<Label>)request.getAttribute("labels");
for (Label l : labels){%>
    <p style="color:<%=l.getHtml_color()%>"><%=l.getText()%></p>
<%}%>
<br>
<b>CREER UN LABEL :</b><br>
<form method="post" action="Admin">
    <p>Texte : <input type="text" name="texte"></p>
    <p>Couleur : <input type="text" name="color" placeholder="#FF00FF"></p>
    <p><input type="submit" value="CREER"></p>
    <p><input type="hidden" name="op" value="Vcreationlabel"></p>
</form>
</body>
</html>