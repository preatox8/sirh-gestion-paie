<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Ajouter un employé</title>
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
					href="<c:url value='lister'/>">Employés</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/mvc/bulletins/lister'/>">Bulletins</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<br />
		<div class="row">
			<div class="col">
				<h1>Ajouter un employé</h1>
			</div>
		</div>
		<br />
		<form method="POST" name="nvcollab" id="formcreaemploye"
			action="<%=request.getContextPath()%>/mvc/employes/creer">
			<div class="row">
				<div class="col-12 col-md-4 col-xl-3">
					<label for="validationDefault05" class="texte-justify float-right">
						<strong>Matricule</strong>
					</label>

				</div>
				<div class="col-12 col-md-6">
					<input required type="text" class="form-control" name="matricule"
						id="matricule">
					<div class="invalid-feedback">Le matricule est obligatoire</div>
				</div>
			</div>
			<br />
			<div class="card-body">
				<div class="row">
					<div class="input-group mb-3">
						<div class="col-12 col-md-4 col-xl-3">
							<label for="validationDefault05"
								class="texte-justify float-right"> <strong>Entreprise</strong>
							</label>
						</div>
						<div class="col-12 col-md-6 col-xl-6">
							<select class="custom-select" name="entreprise" id="entreprise">
								<c:forEach items="${entreprise}" var="e">
									<option value="${e.id}">${e.denomination}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">L'entreprise est obligatoire</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="input-group mb-3">
						<div class="col-12 col-md-4 col-xl-3">
							<label for="validationDefault05"
								class="texte-justify float-right"> <strong>Profil</strong>
							</label>
						</div>
						<div class="col-12 col-md-6 col-xl-6">
							<select class="custom-select" name="profil" id="profil">
								<c:forEach items="${profil}" var="p">
									<option value="${p.id}">${p.code}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Le profil est obligatoire</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="input-group mb-3">
						<div class="col-12 col-md-4 col-xl-3">
							<label for="validationDefault05"
								class="texte-justify float-right"> <strong>Grade</strong>
							</label>
						</div>
						<div class="col-12 col-md-6 col-xl-6">
							<select class="custom-select" name="grade" id="grade">
								<c:forEach items="${grade}" var="g">
									<option value="${g.id}">${g.code}-${g.nbHeuresBase * g.tauxBase * 12}
										€ / an</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Le grade est obligatoire</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row ">
				<div class="col-12 col-md-9 col-xl-9 ">
					<button type="submit" class="btn btn-secondary btn-sm float-right">Ajouter</button>
				</div>
			</div>
			<sec:csrfInput />
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