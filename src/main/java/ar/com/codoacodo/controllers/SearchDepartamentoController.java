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

@WebServlet("/SearchDepartamentoController")
public class SearchDepartamentoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//buscar en la db productos por titulo
		//interface = new class que implementa la interface
		 iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		//obtengo la clave enviado desde el formulario que esta en navbar.jsp 
		String clave = req.getParameter("claveBusqueda");
		
		//FALTAN VALIDACIONES!!!
		
		//busco!
		List<Departamento> depto;
		try {
			depto = dao.searchAll(clave);
		} catch (Exception e) {
			depto = List.of();//crea una lista vacia
			e.printStackTrace();
		}
		
		//guardar en el request, los datos que encontre en la busqueda
		//antes de irme a la nueva pagina: guardo en el request los datos que puede necesitar la JSP
		//clave, valor
		req.setAttribute("listado", depto);
		
		//este bloque de codigo lo vamos a usar en todos lados
		getServletContext().getRequestDispatcher("/listadoDepartamentos.jsp").forward(req, resp);
	}
}