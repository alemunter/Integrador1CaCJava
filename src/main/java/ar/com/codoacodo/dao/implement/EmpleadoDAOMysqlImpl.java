package ar.com.codoacodo.dao.implement;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.db.AdministradorDeConexiones;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.domain.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

// la interface List tiene una serie de metodos que son 
//add(elemento)
//remove(elemento)
//me permite trabajar como si fueran vectores dinamicamente como en js, dependiendo de la memoria

//en la clase que creamos, heredamos de una interface, la interfaceDAO que tiene los
//metodos para interactuar con la base
public class EmpleadoDAOMysqlImpl implements iEmpleadoDAO {
	
	@Override
	public Empleado getById(Long id) throws Exception{
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "select * from departamentos where id = " + id;
		Statement statement  = connection.createStatement();
		
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		// El resultset devuelve un registro de una tabla 
		
	     // primero verifico si hay datos 
		
		if (resultset.next()){
			// obtengo el dato del campo id
			return this.crearEmpleado(resultset);
		}
		cerrar(connection);
		return null; // si no hay resultset entonces no devuelve nada
	}

	@Override
	public List<Empleado> findAll() throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
	    String sql = "select * from empleados";
		Statement statement  = connection.createStatement();
				
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		// El resultset devuelve un registro de una tabla 
	     // primero verifico si hay datos 
		    // creo una lista de departamentos
			List<Empleado> empleados = new ArrayList<Empleado>();	
			
			// mientras encontremos resultados de la base 
			while (resultset.next()){
				Empleado e = this.crearEmpleado(resultset);
				empleados.add(e);
			}
			cerrar(connection);
		   // devolvemos empleados
		   return empleados; //
	}

	@Override
	public void delete(Long id) throws Exception {
	   //-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
	     String sql = "DELETE FROM empleados WHERE ID=" + id;
	 	 Statement statement  = connection.createStatement();
	 	//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		  statement.executeUpdate(sql);
		  cerrar(connection);
		 
	}

	@Override
	public void update(Empleado empleado) throws Exception {
		// creo un Departamento con los datos modificados del departemento 
		
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "update empleados set nombre = ?, apellido = ?, dpto_id = ? where dni= ?"  ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1,empleado.getNombre());
	    statement.setString(2,empleado.getApellido());
		statement.setDouble(3,empleado.getDepto().getId());
		statement.setLong(4,empleado.getDni());
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		statement.execute();
		
		cerrar(connection);
	}

	@Override
	public void create(Empleado newEmpleado) throws Exception {
		
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "insert into empleados (dni, nombre, apellido, dpto_id) values (?,?,?,?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setLong(1,newEmpleado.getDni());
		statement.setString(2,newEmpleado.getNombre());
		statement.setString(3,newEmpleado.getApellido());
		statement.setDouble(3,newEmpleado.getDepto().getId());
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		statement.execute();
		
		ResultSet res = statement.getGeneratedKeys(); // RETORNA LA KEY QUE SE GENERO
		if (res.next()) {
			System.out.println("Se creo el departamento correctamente");
		}
		cerrar(connection);
	}
	@Override
	public List<Empleado> searchByDNI(Long clave) throws Exception {
		// 1 - necesito la Connection
		Connection connection = AdministradorDeConexiones.getConnection();

		// 2 - arma el statement
		String sql = "SELECT * FROM empleados WHERE dni LIKE ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		//setear el valor que va en remplazo del ?
		statement.setString(1, "%" + clave + "%");
		
		// 3 - resultset
		ResultSet resultSet = statement.executeQuery();

		// Interface i = new ClaseQueImplementaLaInterface();
		List<Empleado> empleado = new ArrayList<Empleado>();

		// verifico si hay datos
		while (resultSet.next()) {
			empleado.add(this.crearEmpleado(resultSet));
		}
		
		cerrar(connection);
		
		return empleado;
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
	
	private Empleado crearEmpleado(ResultSet resultSet) throws Exception {
		iDepartamentoDAO departamento = new DepartamentoDAOMysqlImpl();
		// obtengo el dato del campo id
		Long DNIBd = resultSet.getLong("dni");
		String nombreBd = resultSet.getString("nombre");
		String apellidoBd = resultSet.getString("apellido");
		Departamento deptoBd = departamento.getById(resultSet.getLong("dpto_id"));
		return new Empleado(DNIBd,nombreBd,apellidoBd,deptoBd);
	}
	// implementar el nuevo metodo que busca por el nombre y que devuelve una lista de departamentos

}
