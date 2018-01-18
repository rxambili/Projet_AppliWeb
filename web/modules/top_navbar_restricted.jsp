 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, Entities.*" %>
<%@ page import="java.util.List" %>
 <head>
	<link rel="stylesheet" href="css/top_navbar_restricted.css" />
	
	<!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

	
 </head>
 <% Utilisateur u = (Utilisateur) session.getAttribute("user");	%>
 <nav class="navbar navbar-findcond fixed-top bg-faded" role="navigation">
    <div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="Restricted?op=accueil">Forum7</a>
		</div>
		<!-- <form class="navbar-form navbar-right search-form" role="search">
				<input type="text" class="form-control" placeholder="Recherche..." />
		</form> -->
		<ul class="nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-fw fa-bell-o"></i> Notifications <span class="badge" style="color:#fff">0</span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#"><i class="fa fa-fw fa-tag"></i> <span class="badge">Music</span> sayfası <span class="badge">Video</span> sayfasında etiketlendi</a></li>
					<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Music</span> sayfasında iletiniz beğenildi</a></li>
					<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Video</span> sayfasında iletiniz beğenildi</a></li>
					<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Game</span> sayfasında iletiniz beğenildi</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%=u.getPseudo()%> <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">Mon Compte</a></li>
					<li><a href="#">Devenir VIP</a></li>
					<li class="divider"></li>
					<li><a href="#exit">Déconnexion</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>
