<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualiser Bulletin</title>
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

	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<h1>Bulletin de salaire</h1>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<div class="float-right">
					<strong>Période</strong>
				</div>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="float-right">
					Du
					<c:out value="${bull.periode.dateDebut}"></c:out>
					Au
					<c:out value="${bull.periode.dateFin}"></c:out>
				</div>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<strong>Entreprise</strong>
			</div>
		</div>
		<div class="row">
			<div class="col-4">
				<c:out value="${bull.remunerationEmploye.entreprise.denomination}"></c:out>
			</div>
			<div class="col-8">
				<div class="float-right">
					<strong>Matricule : </strong>${bull.remunerationEmploye.matricule}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<strong>SIRET : </strong>${bull.remunerationEmploye.entreprise.siret}
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<strong>Salaire </strong>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base</th>
					<th>Taux Salarial</th>
					<th>Montant Salarial</th>
					<th>Taux Patronal</th>
					<th>Cot. Patronales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Salaire de base</td>
					<td>${bull.remunerationEmploye.grade.nbHeuresBase}</td>
					<td>${bull.remunerationEmploye.grade.tauxBase}</td>
					<td>${calcul.salaireDeBase}</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Prime Except.</td>
					<td></td>
					<td></td>
					<td>${bull.primeExceptionnelle}</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Salaire Brut</td>
					<td></td>
					<td></td>
					<td>${calcul.salaireBrut}</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<br>
		<div class="row">
			<div class="col">
				<strong>Cotisations </strong>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base(brut)</th>
					<th>Taux Salarial</th>
					<th>Montant Salarial</th>
					<th>Taux Patronal</th>
					<th>Cot. Patronales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>EP01 URSSAF Maladie ...</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
				</tr>
				<tr>
					<td>EP01 URSSAF Solidarité.</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
				</tr>
				<tr>
					<td>...</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>...</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Total Retenue</td>
					<td></td>
					<td></td>
					<td>xx</td>
					<td></td>
					<td>xx</td>
				</tr>
			</tbody>
		</table>
		<div class="row">
			<div class="col">
				<strong>NET Imposable :  </strong>XXX
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base(brut)</th>
					<th>Taux Salarial</th>
					<th>Montant Salarial</th>
					<th>Taux Patronal</th>
					<th>Cot. Patronales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>SP01 URSSAF CSG NON DEDUCTIB</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>SXX XXXXX</td>
					<td>xx</td>
					<td>xx</td>
					<td>xx</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<br>
		<div class="row">
			<div class="col">
				<div class="float-right">
					<strong>NET A PAYER : </strong>XXXXX
				</div>
			</div>
		</div>
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