<%@ page import="ar.com.codoacodo.domain.Departamento" %>
<%@ page import="ar.com.codoacodo.domain.Empleado" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!Doctype html>
<html>
		<head>
		 <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Nuevo Empleado</title>
		 <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  
	</head>
	<body>
	   <div class="container bg-light">
	   		<jsp:include page="navbar.jsp"/>
	   </div>
		<main class="container mt-5">
			<h1>Nuevo Empleado</h1>
			
			<div class="row mt-5">
				<div class="col-12">
					<form class="row g-3 needs-validation" 
						action="<%=request.getContextPath()%>/CreateEmpleadoController"
						method="POST">
					  <div class="col-md-4">
					    <label for="validationCustom01" class="form-label">DNI</label>
					    <input type="number"
					    	name="dni" 
					    	class="form-control" 
					    	id="validationCustom01" 
					    	value="" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-md-4">
					    <label for="validationCustom02" class="form-label">Nombre</label>
					    <input type="text"
					    	name="nombre" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-md-4">
					    <label for="validationCustom02" class="form-label">Apellido</label>
					    <input type="text"
					    	name="apellido" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-md-4">
					    <label for="validationCustom02" class="form-label">ID Departamento</label>
					    <input type="number"
					    	name="IdDepartamento" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-12">
					    <button class="btn btn-primary" type="submit">Nuevo</button>
					  </div>
					</form>
				</div>
			</div>
		</main>
	</body>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>