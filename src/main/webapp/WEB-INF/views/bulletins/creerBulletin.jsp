<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Créer bulletin</title>
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
		<br>
		<div class="alert alert-info alert-dismissible fade show" role="alert">
			Veuillez créer un employé avant de créer un bulletin de salaire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="row">
			<div class="col">
				<h1>Créer un bulletin de salaire</h1>
			</div>
		</div>
		<br />
		<form method="POST" name="nvcollab" id="formcreabulletin"
			action="<%=request.getContextPath()%>/mvc/bulletins/creer">
			<div class="card-body">
				<div class="row">
					<div class="input-group mb-3">
						<div class="col-12 col-md-4 col-xl-3">
							<label for="validationDefault05"
								class="texte-justify float-right"> <strong>Période</strong>
							</label>
						</div>
						<div class="col-12 col-md-6 col-xl-6">
							<select class="custom-select" name="periode" id="periode">
								<c:forEach items="${periode}" var="p">
									<option value="${p.id}">${p.dateDebut}-${p.dateFin}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">La période est obligatoire</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="input-group mb-3">
						<div class="col-12 col-md-4 col-xl-3">
							<label for="validationDefault05"
								class="texte-justify float-right"> <strong>Matricule</strong>
							</label>
						</div>
						<div class="col-12 col-md-6 col-xl-6">
							<select class="custom-select" name="matricule" id="matricule">
								<c:forEach items="${employe}" var="emp">
									<option value="${emp.id}">${emp.matricule}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Le matricule est obligatoire</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12 col-md-4 col-xl-3">
					<label for="validationDefault05" class="texte-justify float-right">
						<strong>Prime exceptionnelle</strong>
					</label>
				</div>
				<div class="col-12 col-md-6">
					<input required type="text" class="form-control" name="prime"
						id="prime">
					<div class="invalid-feedback">La prime est obligatoire</div>
				</div>
			</div>
			<br />
			<div class="row ">
				<div class="col-12 col-md-9 col-xl-9 ">
					<button type="submit" class="btn btn-secondary btn-sm float-right">Créer</button>
				</div>
			</div>
		</form>
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