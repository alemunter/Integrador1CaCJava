<%@ page import="ar.com.codoacodo.domain.Departamento" %>
<%@ page import="ar.com.codoacodo.domain.Empleado" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
	Empleado e = (Empleado)request.getAttribute("empleado"); // levanto el departamento que viene del controller
	System.out.println(e.getDni());
	System.out.println(e.getNombre());
	System.out.println(e.getApellido());
	System.out.println(e.getDepto().getId());
%>
<!Doctype html>
<html>
		<head>
		 <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Editar Empleado</title>
		 <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  
	</head>
	<body>
	   <div class="container bg-light">
	   		<jsp:include page="navbar.jsp"/>
	   </div>
		<main class="container mt-5">
			<h1>Editar Empleado</h1>
			<div class="row mt-5">
				<div class="col-12">
					<form class="row g-3 needs-validation" 
						action="<%=request.getContextPath()%>/UpdateEmpleadoController"
						method="POST">
					  <div class="col-md-4">
					    <label for="validationCustom01" class="form-label">DNI</label>
					    <input type="number"
					    	name="dni" 
					    	class="form-control" 
					    	id="validationCustom01" 
					    	value="<%=e.getDni()%>" 
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
					    	value="<%=e.getNombre()%>" 
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
					    	value="<%=e.getApellido()%>" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="container mt-4">
					  <% 
					  	List<Departamento> listado = (List<Departamento>)request.getAttribute("listado");
					  %>
					    <label for="selectDepartamento" class="form-label">Selecciona un elemento:</label>
					    <select name="IdDepartamento" class="form-select" id="selectDepartamento" >
						    <%
						    if (!listado.isEmpty()){
						   		for( Departamento  unDepto : listado) { 
						   			if (unDepto.getId().equals(e.getDepto().getId())) { %>
						      			<option value="<%=unDepto.getId()%>" selected> <%=unDepto.getNombre() %> </option>
						      		<% }else { %>
						      			<option value="<%=unDepto.getId()%>"> <%=unDepto.getNombre() %> </option>
						   		<% 		}
						   			}
						   	}%>
					    </select>
					  </div>
					  <div class="col-12">
					    <button class="btn btn-primary" type="submit">Editar</button>
					  </div>
					</form>
				</div>
			</div>
		</main>
	</body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>