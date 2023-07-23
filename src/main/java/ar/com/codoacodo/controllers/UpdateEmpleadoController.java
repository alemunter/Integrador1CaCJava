package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

@WebServlet("/UpdateEmpleadoController")
public class UpdateEmpleadoController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturo los parametros que viene en el request enviado por el form
		String dni = req.getParameter("dni");//name de input
		String nombre= req.getParameter("nombre");//name de input
		String apellido= req.getParameter("apellido");//name de input
		String idDepartamento = req.getParameter("IdDepartamento");//name de input
       
		Empleado e = new Empleado(Long.parseLong(dni),nombre,apellido,Long.parseLong(idDepartamento));
		
		iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
		// si no usamos try catch podemos arriba poner throws Exception
		try { 
			daoE.update(e);
			//aca mensaje de exito, PERO COMO UNA LISTA
			req.setAttribute("success", List.of("empleado id:" + e.getDni() + " actualizado correctamente"));
		} catch (Exception exc) {
			exc.printStackTrace();
			req.setAttribute("errors", List.of("Error actualizando empleado<" + exc.getMessage()));
		}
		
		//ahora redirect!!!!
	     getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req, resp);
	}
	
	//cargar el departamento y enviarlo a la jsp que va a editar los datos
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String dni = req.getParameter("dniEmpleado");
			
			// realizar validaciones, para los datos que vienen!!!
			
			//interface = new class que implementa la interface
			iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
			
			Empleado e = null;
			//cargo los datos 
			try {
				e = daoE.getById(Long.parseLong(dni));
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			
			//guardar el producto en request y pasar dicho producto a la jsp
			req.setAttribute("empleado", e);
			
			//redirect
			//ahora redirect!!!!
		     getServletContext().getRequestDispatcher("/editarEmpleado.jsp").forward(req, resp);
		}

}
