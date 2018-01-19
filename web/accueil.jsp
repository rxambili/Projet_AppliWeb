<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, Entities.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
	<%List<Topic> topics = (List<Topic>) request.getAttribute("ListeTopics");
	Utilisateur user = (Utilisateur) session.getAttribute("user");
    List<Collection<Label>> topicsLabels = (List<Collection<Label>>) request.getAttribute("topicsLabels");
    boolean isAdmin = (boolean)request.getAttribute("isAdmin");
    %>
    <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Accueil</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">


    <!-- Custom styles for this template -->
    <link href="css/accueil.css" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

  </head>

   <body ng-app>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
		<% if(user == null) {%>
        <div ng-include="'modules/top_navbar.html'"></div>
		<% } else {%>
		<div ng-include="'modules/top_navbar_restricted.jsp'"></div>
		<%}%>
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div id="newtopic" class="row">
            <div class="col-md-3">
            <form id="signup" method="post" action="Restricted">
                <h1></h1>
                <input type="submit" value="Nouveau topic" class="inputButton"/>
                <input type="hidden" name="op" value="creationtopic">
            </form>
            </div>
            <div class="col-md-3">
            <%if (isAdmin) {%>
            <form id="signup" method="post" action="Admin">
                <h1></h1>
                <input type="submit" value="Administrer les Labels" class="inputButton"/>
                <input type="hidden" name="op" value="creationlabel">
            </form>
            <%}%>
            </div>
        </div>
    </div>
    <div class="container">
	<%
	if (topics != null) {
	    int i = 0;
	for (Topic t : topics) {%>
      <div id="topicbox" class="row vdivide">
		
		<div class="col-md-2">
			<a href="Restricted?op=afficherTopic&topicId=<%= t.getId() %>" ><img src="img/topic.png" class="rounded"></a>
			<a style="color:#f14444"> posts : <%=t.getNbMessages()%> </a>
		</div>
		
		<!-- topic -->
        <div class="col-md-8">
			<h1><%= t.getTitre()%></h1>
            <h2>Labels: <%for (Label l : topicsLabels.get(i)){%>
               <%=l.getText()+", "%>
            <%}%></h2>
			<hr/>
            <h3><% if(t.getNbMessages() > 0) {%> <%= t.getMessages().get(0).getContenu()%> <%}%></h3>
            <a href="Restricted?op=afficherTopic&topicId=<%= t.getId() %>" class="btn btn-primary">Rejoindre! &rarr;</a>
        </div>
		
		<!-- auteur -->
        <div class="col-md-2">
			<img src="img/user.jpg" class="rounded" style="width:100%">
			<a href="#" style="color:#f14444"><i class="fa fa-user"></i> <%= t.getCreateur().getPseudo()%>  </a><br/>
            <a href="#" style="color:#f14444"><i class="fa fa-calendar"></i> 01/01/18 </a>
			<a href="#" style="color:#f14444"><i class="fa fa-clock-o"></i> 17:57 </a>
        </div>
		

        <!-- Pagination 
        <ul class="pagination justify-content-center mb-4">
          <li class="page-item">
            <a class="page-link" href="#">&larr; Older</a>
          </li>
          <li class="page-item disabled">
            <a class="page-link" href="#">Newer &rarr;</a>
          </li>
        </ul> -->

        <!-- Sidebar Widgets Column 
        <div class="col-md-4">-->

          <!-- Search Widget 
          <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button class="btn btn-secondary" type="button">Go!</button>
                </span>
              </div>
            </div>
          </div> -->

          <!-- Categories Widget 
          <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Web Design</a>
                    </li>
                    <li>
                      <a href="#">HTML</a>
                    </li>
                    <li>
                      <a href="#">Freebies</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">JavaScript</a>
                    </li>
                    <li>
                      <a href="#">CSS</a>
                    </li>
                    <li>
                      <a href="#">Tutorials</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div> -->

          <!-- Side Widget 
          <div class="card my-4">
            <h5 class="card-header">Side Widget</h5>
            <div class="card-body">
              You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
            </div>
          </div> 

        </div> -->

      </div>
	  <%i++;
	    }
		}%>
      <!-- /.row -->

    </div>

    <!-- /.container -->

    <!-- Footer -->
    <div ng-include="'modules/footer.html'"></div>
    
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
