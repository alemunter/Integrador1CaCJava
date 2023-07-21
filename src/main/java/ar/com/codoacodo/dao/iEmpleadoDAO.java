package ar.com.codoacodo.dao;

import ar.com.codoacodo.domain.Empleado;
import java.util.List;

public interface iEmpleadoDAO {
	
   // en esta interface vamos a definir metodos de acceso a la tabla Empleados
	// LAS INTERFACES POR SI SOLAS NO HACEN NADA, SOLO ES UNA ESTRUCTURA
	// SE DEBE IMPLEMENTAR EN UNA CLASE, ES DECIR EN UNA CLASE USAMOS ESTOS METODOS QUE DEFINIMOS ACA
	
	/*crud
	 getById()
	 find()
	 delete()
	 update()
	 create()*/
	
	// select * from Empleados where id = id;
	public Empleado getById(Long id) throws Exception; // devuelve de un id todos los campos 
	
	// cambiamos el array por una lista de java 
	// select * from Empleados;
	public List<Empleado> findAll() throws Exception;	// devuelve todos los registros de la tabla Empleados
	
	// delete from Empleados where dni = dni;
	public void delete(Long id) throws Exception;// esto borra un registro por el id del Empleado
	
	//update Empleados set nombre = nombre, apellido = apellido where id = depto.id;
	public void update(Empleado empleado) throws Exception; // se le pasa un objeto 
	
	// insert to Empleados (campo 1..campo2..campo3) values(newEmpleado.campo1....newDepto.campoN)
	public void create(Empleado newEmpleado) throws Exception;
	
	//select * from Empleados where dni like '%clave%' 
    public List<Empleado> searchByDNI(Long clave) throws Exception;
	
	// crear un method que consulte a la base por el nombre y que devuelva una lista de Empleados
	// crear un nuevo method que haga update solo al nombre o solo al presupuesto 
    
}
	