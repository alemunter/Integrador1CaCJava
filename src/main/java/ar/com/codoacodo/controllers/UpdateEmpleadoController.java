package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;
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
		
		iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
        iDepartamentoDAO daoD = new DepartamentoDAOMysqlImpl();
        
        Departamento d = null; 
		
		
		// si no usamos try catch podemos arriba poner throws Exception
		try { 
			d = daoD.getById(Long.parseLong(idDepartamento));
			Empleado e = new Empleado(Long.parseLong(dni),nombre,apellido,d);
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
			String dni = req.getParameter("id");
			iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		    List<Departamento> departamentos = new ArrayList<>();
			
			// realizar validaciones, para los datos que vienen!!!
			System.out.println(dni);
			
			//interface = new class que implementa la interface
			iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
			
			Empleado e = null;
			
			//cargo los datos 
			try {
				e = daoE.getById(Long.parseLong(dni));
				departamentos = dao.findAll();
		    	 for (Departamento depto : departamentos) 
		    		 System.out.println(depto.toString());
		    	 req.setAttribute("listado", departamentos);
		    	 req.setAttribute("empleado", e);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		     getServletContext().getRequestDispatcher("/editarEmpleado.jsp").forward(req, resp);
		}

}
