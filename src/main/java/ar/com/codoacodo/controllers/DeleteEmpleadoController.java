package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmpleadoController")
public class DeleteEmpleadoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long dni = Long.parseLong(req.getParameter("dniEmpleado"));//viene como String -> Long.parseLong()

		//interface = new class que implementa la interface
		iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();		
		//eliminar
		try {
			daoE.delete(dni);
			//mensaje de exito
			req.setAttribute("success", List.of("Se he eliminado el producto con id:" + dni));
		} catch (Exception e) {
			e.printStackTrace();
			//mensaje de error
			req.setAttribute("erorrs", List.of("NO se he eliminado el producto :" + e.getMessage()));
		}//ctrl+t
		
		//ahora redirect!!!!
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, resp);
		
		
	}

}
