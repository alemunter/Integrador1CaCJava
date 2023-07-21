<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">CRUD</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Inicio</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Listado
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="nav-link" href="<%=request.getContextPath()%>/FindAllDepartamentoController">Departamentos</a>
            <a class="nav-link" href="<%=request.getContextPath()%>/FindAllEmpleadoController">Empleados</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Nuevo
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="nav-link" href="<%=request.getContextPath()%>/nuevoDepartamento.jsp">Nuevo Departamento</a>
            <a class="nav-link" href="<%=request.getContextPath()%>/nuevoEmpleado.jsp">Nuevo Empleado</a>
          </div>
        </li>
      </ul>
      <div class="d-flex w-75 justify-content-end"> 
	      <form class="d-flex" action="<%=request.getContextPath()%>/SearchDepartamentoController">
		        <input  name="claveBusqueda"  class="form-control me-2" type="search" placeholder="Buscar Nombre Depto" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
      </div>
      <div class="d-flex w-75 justify-content-end"> 
	      <form class="d-flex" action="<%=request.getContextPath()%>/SearchEmpleadoController">
		        <input  name="claveBusqueda"  class="form-control me-2" type="search" placeholder="Buscar DNI Empleado" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
      </div>
    </div>
  </div>
</nav>