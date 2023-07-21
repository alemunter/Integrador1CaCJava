<%@ page import="ar.com.codoacodo.domain.Departamento" %>
<%@ page import="ar.com.codoacodo.domain.Empleado" %>
<%@ page import="java.util.List" %>

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
					    	name="presupuesto" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					    <div class="col-md-4">
					    <label for="departamento" class="form-label">Departamento</label>
					      	<option value="php">PHP</option>
					        <option value="java">Java</option>
					        <option value="golang">Golang</option>
					        <option value="python">Python</option>
					        <option value="c#">C#</option>
					        <option value="C++">C++</option>
					        <option value="erlang">Erlang</option>
      					</select>
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
			<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">ID</th>
					      <th scope="col">NOMBRE</th>
					      <th scope="col">PRESUPUESTO</th>
					      <th scope="col">ACCIONES</th>
					   
					    </tr>
					  </thead>
					  <% 
					  	//codigo java
					  	//obtener el listado desde el request
					  	//se guardo bajo el nombre de "departamentos"
					  	List<Departamento> listado = (List<Departamento>)request.getAttribute("listado");
					  %>
					  <tbody>
					   <!-- ESTO SE REPITE TANTA CANDTIDAD DE VECES COMO ARTICULOS TENGA -->
					   <%
					   	for( Departamento  unDepto : listado) {
					   %>
					    <tr>
						      <th scope="row"> <%=unDepto.getId() %> </th>
						      <td><%=unDepto.getNombre() %></td>
						      <td><%=unDepto.getPresupuesto() %></td>
						      <td>
							      	<a class="btn btn-info" 
							      	   role="button" 
							      	   href="<%=request.getContextPath()%>/UpdateDepartamentoController?id=<%=unDepto.getId()%>">
							      	   Editar
							      	</a> | 
						      		<!-- Button trigger modal -->
									<button type="button" class="btn btn-danger" 
										data-bs-toggle="modal" 
										data-bs-target="#exampleModal" 
										onclick="setDepartamentoId(<%=unDepto.getId()%>)">
									  Eliminar
									</button>
							  </td>
					    </tr>
					   <%
					   	}
					   %>
					  </tbody>
					</table>
		</main>
	</body>
</html>