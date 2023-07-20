package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// un servelet es una clase que extiende de httpServlet
//http://localhost:8080/app-web//FindAllEmpleadoController
@WebServlet("/FindAllEmpleadoController")

public class FindAllEmpleadoController extends HttpServlet {
	// tienen metodos importantes como es el 
	//doGet
	//doPost
	// version para la web 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//interface = new class que implementa la interface
	     iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
	     List<Empleado> Empleados = new ArrayList<>();
			
	       try {
	    	 Empleados = dao.findAll();
	       } catch (Exception e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace(); //  muestra por consola el error 
	       }
	       req.setAttribute("listado", Empleados);
	      // este bloque de codigo lo vamos a usar en todos lados, redirecciona al listado.jsp 
	       getServletContext().getRequestDispatcher("/listadoEmpleados.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	/*	
	// version de consola 
	public static void main(String[] args) {
		//interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		List<Empleado> Empleados;
		// si no usamos try catch podemos arriba poner throws Exception
		try {
		   Empleados = dao.findAll();
		}
		catch(Exception e) {
			//e.printStackTrace();
			Empleados= null;
		}
		if (Empleados != null) {
			for(Empleado aux : Empleados) {
				System.out.println(aux.toString());
				System.out.println("--------------------------------");
			}
			
			//System.out.println(d);
		}else {
			System.err.println("No hay registros en la tabla Empleados");
		}
		
	} */
}
