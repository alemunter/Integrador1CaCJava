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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//interface = new class que implementa la interface
	     iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
	     List<Departamento> departamentos = new ArrayList<>();
	     
	       try {
	    	 departamentos = dao.findAll();
	    	 for (Departamento depto : departamentos) {
	    		 System.out.println(depto.toString());
	    	 }
	       } catch (Exception e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace(); //  muestra por consola el error 
	       }
	       req.setAttribute("listado", departamentos);
	        
	      // este bloque de codigo lo vamos a usar en todos lados, redirecciona al listadoDepartamentos.jsp 
	       getServletContext().getRequestDispatcher("/nuevoEmpleado.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturo los parametros que viene en el request enviado por el form
		String dni = req.getParameter("dni");//name de input
		String nombre= req.getParameter("nombre");//name de input
		String apellido= req.getParameter("apellido");//name de input
		String idDepartamento = req.getParameter("IdDepartamento");//name de input

		iEmpleadoDAO daoE = new EmpleadoDAOMysqlImpl();
		iDepartamentoDAO daoD = new DepartamentoDAOMysqlImpl();
		
		// buscamos datos del departamento por id
		Departamento d = null;
		try {
			d = daoD.getById(Long.parseLong(idDepartamento));
			System.out.println(d.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Empleado e = new Empleado(Long.parseLong(dni),nombre,apellido,Long.parseLong(idDepartamento));
		Empleado e = new Empleado(Long.parseLong(dni),nombre,apellido,d);
		
		//debug
		System.out.println(e.getDni());
		System.out.println(e.getNombre());
		System.out.println(e.getApellido());
		System.out.println(e.getDepto().getId());
		
		try {
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
