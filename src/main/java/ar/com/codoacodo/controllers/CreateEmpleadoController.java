package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/CreateEmpleadoController")
public class CreateEmpleadoController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturo los parametros que viene en el request enviado por el form
		String dni = req.getParameter("dni");//name de input
		String nombre= req.getParameter("nombre");//name de input
		String apellido= req.getParameter("apellido");//name de input
		String idDepartamento = req.getParameter("IdDepartamento");//name de input
		
		//validaciones!
		List<String> errores = new ArrayList<>();
		if(nombre == null || "".equals(nombre)) {
			errores.add("Nombre vacío");
		}
		if(apellido == null || "".equals(apellido)) {
			errores.add("Nombre vacío");
		if(dni == null || "".equals(dni)) {
			errores.add("Id vacío");
		}
		if(idDepartamento == null || "".equals(idDepartamento)) {
			errores.add("Presupuesto vacío");
		}
		//agrego las demas validaciones!!!! (uds)
		if(!errores.isEmpty()) {
			req.setAttribute("errors", errores);
			//vuelvo a la jsp con la lista de errores cargadas 
			getServletContext().getRequestDispatcher("/nuevoEmpleado.jsp").forward(req, resp);
			return;
		}

		//interface = new class que implementa la interface
		iDepartamentoDAO daoD = new DepartamentoDAOMysqlImpl();
		iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
		
		// buscamos datos del departamento por id
		
		// si no usamos try catch podemos arriba poner throws Exception
		try {
			//Departamento d = daoD.getById(Long.parseLong(idDepartamento));
			Empleado e = new Empleado(Long.parseLong(dni),nombre,apellido,Long.parseLong(idDepartamento));
			daoE.create(e);
			req.setAttribute("success", List.of("Alta de producto exitosa"));
		}
		catch (Exception exception) {
			//si falla volver al nuevo.jsp
			exception.printStackTrace();
		 }
		//ahora redirect!!!!
		getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req, resp);
	}
}
}
