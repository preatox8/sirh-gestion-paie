<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Lister les bulletins</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-4.0.0-dist/css/bootstrap.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/mvc/employes/lister'/>">Employés</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='lister'/>">Bulletins</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<br />
		<div class="row">
			<div class="col">
				<h1>Liste des bulletins</h1>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col">
				<a class="btn btn-secondary btn-sm float-right" role="button"
					href="<c:url value='creer'/>"> Créer un nouveau bulletin</a>
			</div>
		</div>
		<br />
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Date/heure création</th>
					<th>Période</th>
					<th>Matricule</th>
					<th>Salaire Brut</th>
					<th>Net Imposable</th>
					<th>Net A Payer</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listcalcul}" var="b">
					<tr>
						<td>${b.key.remunerationEmploye.dateHeureCreation}</td>
						<td>${b.key.periode.dateDebut}- ${b.key.periode.dateFin}</td>
						<td>${b.key.remunerationEmploye.matricule}</td>
						<td>${b.value.salaireBrut}</td>
						<td>${b.value.netImposable}</td>
						<td>${b.value.netAPayer}</td>
						<td><a class="nav-link" href="<c:url value='visualiser/?id=${b.key.id}'/>">Visualiser</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js "
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN "
		crossorigin="anonymous "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js "
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q "
		crossorigin="anonymous "></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js "
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl "
		crossorigin="anonymous "></script>
</body>
</html>