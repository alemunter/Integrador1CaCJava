package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

@WebServlet("/SearchEmpleadoController")
public class SearchEmpleadoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//buscar en la db productos por titulo
		//interface = new class que implementa la interface
		 iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		//obtengo la clave enviado desde el formulario que esta en navbar.jsp 
		Long clave = Long.parseLong(req.getParameter("claveBusqueda"));
		
		//FALTAN VALIDACIONES!!!
		
		//busco!
		List<Empleado> empleados;
		try {
			empleados = dao.searchByDNI(clave);
		} catch (Exception e) {
			empleados = List.of();//crea una lista vacia
			e.printStackTrace();
		}
		
		//guardar en el request, los datos que encontre en la busqueda
		//antes de irme a la nueva pagina: guardo en el request los datos que puede necesitar la JSP
		//clave, valor
		req.setAttribute("listado", empleados);
		
		//este bloque de codigo lo vamos a usar en todos lados
		getServletContext().getRequestDispatcher("/listadoEmpleados.jsp").forward(req, resp);
	}
}