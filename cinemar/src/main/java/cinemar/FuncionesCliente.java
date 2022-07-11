package cinemar;

import java.math.BigDecimal;


import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;

public class FuncionesCliente {

	static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	static final String DB_URL= "jdbc:mysql://localhost:3306/cinemar2";
	
	//CREDENCIALES
	static final String USER ="root";
	static final String PASS = "JuanRoman10!";

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement ps= null;
	
	private Reserva reserva;
	private Tarjeta tarjeta;
	private static Cliente Cliente_que_opera;
	private static ArrayList <Usuario> usuarios = new ArrayList <Usuario>();
	private static ArrayList <Cliente> clientes = new ArrayList <Cliente>();
	private static ArrayList<Butacas> butacas= new ArrayList <Butacas>();
	private static ArrayList <Sesion_alt> sesiones = new ArrayList <Sesion_alt>();
	private static ArrayList <Reserva_alt> reservas= new ArrayList <Reserva_alt>();
	private static ArrayList <Reserva_alt> mis_reservas= new ArrayList <Reserva_alt>();
	

public Cliente getCliente_que_opera() {
		return Cliente_que_opera;
	}
	public void setCliente_que_opera(Cliente cliente_que_opera) {
		Cliente_que_opera = cliente_que_opera;
	}
public Reserva getReserva() {
	return reserva;
}
public void setReserva(Reserva reserva) {
	this.reserva = reserva;
}
public Tarjeta getTarjeta() {
	return tarjeta;
}
public void setTarjeta(Tarjeta tarjeta) {
	this.tarjeta = tarjeta;
}

public FuncionesCliente()
{
}

public static void Cargar_usuarios(ArrayList <Usuario> usuarios_cinemar)
{
	//PARTE A-1: CARGO LAS BASE DE DATOS "USUARIO"
	try
	{
		//Registrar JDBC driver
		Class.forName(JDBC_DRIVER);
				 
		// Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 
		// Ejecutar una accion en SQL
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM cinemar2.usuario;";
		ResultSet rs = stmt.executeQuery(sql);
				 
		//Extraer datos del ResultSet
		while(rs.next())
		{		 
			//Recibir por tipo de columna
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			Date fecha_n= rs.getDate("fechaNacimiento"); 
			String celular= rs.getString("numeroCelular");
			String email= rs.getString("email");
			String rolU=rs.getString("rol");
					 
			//Creo un nuevo usuario con los datos extraidos de la base de datos y lo agrego en un ArrayList
			Usuario usuario=new Usuario (nombre,apellido,fecha_n,celular,email,rolU);
			usuarios_cinemar.add(usuario);
		}
		
		usuarios=usuarios_cinemar;
				 
			//Entorno de Limpieza
			rs.close();
			stmt.close();
			conn.close();
			}catch(SQLException se){
				// Resolver errores para JDBC
				se.printStackTrace();
			}catch(Exception e){
				// Resolver errores para Class.forName
				e.printStackTrace();
			}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
				 se.printStackTrace();
				 }
			} 
				 
	//MUestra los usuarios cargados en la base de datos
//		for(int i=0; i<usuarios_cinemar.size();i++)
//		{
//			 System.out.println("Usuario " + (i+1));
//			 System.out.println("Nombre: " + (usuarios_cinemar.get(i).getNombre()));
//			 System.out.println("Apellido: " + (usuarios_cinemar.get(i).getApellido()));
//			 System.out.println("Fecha de nacimiento: " + (usuarios_cinemar.get(i).getFecha_nacimiento()));
//			 System.out.println("Numero de celular: " + (usuarios_cinemar.get(i).getNumero_celular()));
//			 System.out.println("Email: " + (usuarios_cinemar.get(i).getEmail()));
//			 System.out.println("Rol: " + (usuarios_cinemar.get(i).getRol_usuario()));
//			 System.out.println();
//		}
}


public static void Cargar_clientes(ArrayList <Cliente> clientes_cinemar)
{

	
	//PARTE A-1: CARGO LAS BASE DE DATOS "USUARIO"
	try
	{
		//Registrar JDBC driver
		Class.forName(JDBC_DRIVER);
				 
		// Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		
		// Ejecutar una accion en SQL
		stmt = conn.createStatement();
		
		String sql;
	
		
		sql = "SELECT cinemar2.usuario.nombre,apellido,fechaNacimiento,numeroCelular,email,rol,\r\n"
				+ "cinemar2.cliente.idCLIENTE,contraseña,fechaRegistro FROM cinemar2.usuario\r\n"
				+ "INNER JOIN cinemar2.cliente \r\n"
				+ "ON cinemar2.usuario.numUSUARIO = cinemar2.cliente.usuario_numUSUARIO;";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//Extraer datos del ResultSet
		while(rs.next())
		{		 
			//Recibir por tipo de columna BDD USUARIOS
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			Date fecha_n= rs.getDate("fechaNacimiento"); 
			String celular= rs.getString("numeroCelular");
			String email= rs.getString("email");
			String rolU=rs.getString("rol");

			//DATOS DE LA BBD CLIENTES
			String id = rs.getString("idCLIENTE");
			String contraseña=rs.getString("contraseña");
			Date fecha_r = rs.getDate("fechaRegistro");
			
			 //Creo un nuevo usuario con los datos extraidos de la base de datos y lo agrego en un ArrayList
			Cliente cliente= new Cliente (nombre,apellido,fecha_n,celular,email,rolU,id,contraseña,fecha_r);
			clientes_cinemar.add(cliente);
		}
		
		clientes=clientes_cinemar;
				 
			//Entorno de Limpieza
			rs.close();
			stmt.close();
			conn.close();
			}catch(SQLException se){
				// Resolver errores para JDBC
				se.printStackTrace();
			}catch(Exception e){
				// Resolver errores para Class.forName
				e.printStackTrace();
			}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
				 se.printStackTrace();
				 }
			} 

		//MUestra los usuarios cargados en la base de datos
//		for(int i=0; i<clientes_cinemar.size();i++)
//		{
//			 System.out.println("Cliente " + (i+1));
//			 System.out.println("Id: " + (clientes_cinemar.get(i).getId_Usuario()));
//			 System.out.println("Contraseña: " +(clientes_cinemar.get(i).getContraseña()));
//			 System.out.println("Registrado el " +(clientes_cinemar.get(i).getFecha_registro()));
//			 System.out.println("Nombre: "+ clientes_cinemar.get(i).getNombre());
//			 System.out.println("Apellido: "+ clientes_cinemar.get(i).getApellido());
//			 System.out.println("Gmail: "+ clientes_cinemar.get(i).getEmail());
//
//		}
		
}


public static boolean Registrarse()
{
	boolean registro = false;
	PreparedStatement ps= null;
	
		
	//PARTE A:CARGO BASE DE DATOS CLIENTES
	Cargar_clientes(clientes);
	Cargar_usuarios(usuarios);
	//PARTE B-1: NUEVO USUARIO EN LA BASE DE DATOS DE USUARIOS
				
	Cliente cliente = new Cliente();
	System.out.println("Usted está a punto de registrarse...");
				
	@SuppressWarnings("resource")
	Scanner entrada= new Scanner(System.in);
	
	System.out.print("Ingrese su nombre (primera letrra con mayúscula): ");		
	cliente.setNombre(entrada.nextLine());
	
	//Validación
	
	Pattern pt=Pattern.compile("[A-Z]{1}+[a-z]{2,20}$");
	Matcher mc= pt.matcher(cliente.getNombre());
	
	while(!mc.matches())
	{
		System.err.print("Dato invalido. Ingrese correctamente su nombre (primer letra con mayúscula): ") ;
		cliente.setNombre(entrada.nextLine());
		mc= pt.matcher(cliente.getNombre());
	}
	System.out.println();

	System.out.print("Ingrese su apellido (primera letrra con mayúscula): ");
	cliente.setApellido(entrada.nextLine());
	
	pt=Pattern.compile("[A-Z]{1}+[a-z]{2,20}$");
	mc= pt.matcher(cliente.getApellido());
	
	while(!mc.matches())
	{
		System.err.print("Dato invalido. Ingrese correctamente su apellido: ") ;
		cliente.setApellido(entrada.nextLine());
		mc= pt.matcher(cliente.getApellido());
	}
	
	while(cliente.getApellido().equals(cliente.getNombre()))
	{
		System.err.print("Error. El nombre y el apellido son iguales. Ingrese el apellido correctamente: ") ;
		cliente.setApellido(entrada.nextLine());
		mc= pt.matcher(cliente.getApellido());
	}
	
	
	System.out.println();

	

	System.out.print("Ingrese su fecha de nacimiento (AAAA-MM-DD): ");
	
	do {
			try
			{
				cliente.setFecha_nacimiento(Date.valueOf(entrada.nextLine()));
				break;
			}
			catch(Exception e)
			{
				System.err.print("Error. Ingrese una fecha válida (AAAA-MM-DD): ");
			}
		}while(true);
	
	System.out.println();
	System.out.print("Ingrese su numero de celular (10 digitos): ") ;
	cliente.setNumero_celular(entrada.nextLine());

	
	//Validacion
	pt=Pattern.compile("[0-9]{10}");
	mc= pt.matcher(cliente.getNumero_celular());
	
	while(!mc.matches())
	{
		System.err.print("Dato invalido. Ingrese su numero de celular (10 digitos): ") ;
		cliente.setNumero_celular(entrada.nextLine());
		mc= pt.matcher(cliente.getNumero_celular());
	}
	
	System.out.println();
	System.out.print("Ingrese su Gmail: ");
	cliente.setEmail(entrada.nextLine());
	
	//Validación
	pt=Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@gmail+[.]+[a-zA-Z]{2,3}$");
	mc=pt.matcher(cliente.getEmail());
	
	while(!mc.matches())
	{
		System.err.print("Dato invalido. Ingrese un Gmail correcto: ") ;
		cliente.setEmail(entrada.nextLine());
		mc= pt.matcher(cliente.getEmail());
	}
	
	System.out.println();

	try{
					
	//Registrar JDBC driver
	 Class.forName(JDBC_DRIVER);
					 
	//Abrir una Conexion
	conn = DriverManager.getConnection(DB_URL,USER,PASS);
				
	//Carga de datos por columna
	ps=conn.prepareStatement("INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, ?)");
	ps.setInt(1, usuarios.size()+1);
	ps.setString(2,cliente.getNombre());
	ps.setString(3,cliente.getApellido());
	ps.setDate(4,cliente.getFecha_nacimiento());
	ps.setString(5,cliente.getNumero_celular());
	ps.setString(6, cliente.getEmail());
	ps.setString(7, "Cliente");
					
	//Ejecuta la carga
	ps.executeUpdate();
	
	
	
	ps.close();
	conn.close();
					
	//Agrego el usuario en el arreglo precreado
	usuarios.add(cliente);
					
	}catch(ClassNotFoundException ce)
	{
		ce.printStackTrace();
	}catch(SQLException se)
	{
		se.printStackTrace();
					
	}catch(Exception e){
		e.printStackTrace();
					
				
		}finally{
		// Bloque finalmente utilizado para cerrar recursos
		try{
			if(ps!=null)
			ps.close();
	}catch(SQLException se2){
	}// Nada que podamos hacer
		try{
			if(conn!=null)
				conn.close();
	}catch(SQLException se){
			se.printStackTrace();
			}
	} 
	
	//PARTE B-2: AGREGO A LA BASE DE DATOS CLIENTES

	System.out.print("Ingrese una ID (5 caracteres com mínimo, solo numeros y letras, sin espacios): ");		
	cliente.setId_Usuario(entrada.nextLine());

	//Validación
	pt=Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]{5,25}");
	mc=pt.matcher(cliente.getId_Usuario());
	
	while(!mc.matches())
	{
		System.err.print("Ingrese una id válida de mínimo 5 caracteres: ") ;
		cliente.setId_Usuario(entrada.nextLine());
		mc= pt.matcher(cliente.getId_Usuario());
	}
	
	//VERIFICAR QUE LA ID NO SE REPITA
	for (int i=0; i<clientes.size();i++)
	{
			while(clientes.get(i).getId_Usuario().equals(cliente.getId_Usuario()))
		{
			System.err.print("Ya existe un Usuario con esa Id. Ingrese otra: ");
			cliente.setId_Usuario(entrada.nextLine());
			
			//Validación
			mc=pt.matcher(cliente.getId_Usuario());
			while(!mc.matches())
			{
				System.err.print("Ingrese una id válida:  ") ;
				cliente.setId_Usuario(entrada.nextLine());
				mc= pt.matcher(cliente.getId_Usuario());
			}
		}
	}
	System.out.println();
	

	System.out.print("Ingrese una contraseña (8 a 16 caracteres : como mínimo 2 Mayusculas,2 minusculas , 2 numero y 1 un caracter especial): ");
	String password= entrada.nextLine();
	cliente.setContraseña(password);
	
	//Validación
		pt=Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&-_]{8,15}");
		mc=pt.matcher(password);
		
		while(!mc.matches())
		{
			System.err.println("Contraseña no válida");
			System.out.print("Ingrese una contraseña correcta de 8 a 16 caracteres : como mínimo 2 Mayusculas,2 minusculas , 2 numero y 1 un caracter especial: ");
			cliente.setContraseña(entrada.nextLine());
			mc= pt.matcher(cliente.getContraseña());
		}
	
		
		System.out.print("Repita la contraseña (Tiene 3 intentos): ");
		String contra2= entrada.nextLine();
	
		int intentos=3;
		
		while(intentos>0&&intentos<4)
		{
			
			//si las contras coinciden
			if(contra2.equals(cliente.getContraseña()))
			{
				System.out.println("Las contraseñas coinciden.!");
				System.out.println();
				registro= true;
				break;
			}
			
			else
			if(!(contra2.equals(cliente.getContraseña()))&&intentos>0)
			{
				intentos--;
				System.err.println("Error, las contraseñas no coinciden");
				System.out.print("Repta la contraseña ("+intentos+" intentos restantes): ");
				contra2=entrada.nextLine();
			}
			
			else	if(intentos==0)
					{
						System.err.println("Fracasó el intento de registro.");
						System.out.println();
						registro=false;
						break;
					}
					
		}
		
	
	cliente.setFecha_registro(Date.valueOf(LocalDate.now()));
						
	
	if(registro)
	{
		//Agrego el usuario en el arreglo precreado
		clientes.add(cliente);
		System.out.println( cliente.getId_Usuario()+ " se ha registrado con éxito el " + cliente.getFecha_registro());
		Cliente_que_opera= cliente;

		
		try{
		
		//Registrar JDBC driver
		 Class.forName(JDBC_DRIVER);
						 
		//Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
					
		//Carga de datos por columna
		ps=conn.prepareStatement("INSERT INTO cliente VALUES (?, ?, ?, ?, ?)");
		ps.setInt(1, clientes.size());
		ps.setString(2,cliente.getId_Usuario());
		ps.setString(3,cliente.getContraseña());
		ps.setDate(4,cliente.getFecha_registro());
		ps.setInt(5,usuarios.size());

						
		//Ejecuta la carga
		ps.executeUpdate();
		
		ps.close();
		conn.close();
						
		
		
		}catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}catch(SQLException se)
		{
			se.printStackTrace();
						
		}catch(Exception e){
			e.printStackTrace();
						
					
			}finally{
			// Bloque finalmente utilizado para cerrar recursos
			try{
				if(ps!=null)
				ps.close();
		}catch(SQLException se2){
		}// Nada que podamos hacer
			try{
				if(conn!=null)
					conn.close();
		}catch(SQLException se){
				se.printStackTrace();
				}
		} 
	}//IF CIERRE
	
	
	
	return registro;
}	


public static boolean Inicio_Sesion()

{

	@SuppressWarnings("resource")
	Scanner entrada= new Scanner (System.in);
	
	//Cargo la base de datos en un ArrayList
	Cargar_clientes(clientes);
	
	System.out.print("Ingrese su ID: ");
	String id= entrada.nextLine();
	
	System.out.print("Ingrese su contraseña: ");
	String contraseña = entrada.nextLine();
	System.out.println();
	
	//BUSCAMOS LOS DATOS INGRESADOS EN LA BASE DE DATOS
	int posicion=0;
	boolean inicio=false;
	int intentos=3;
	int i_que_opera=-1; 
	
	while (posicion<=clientes.size())
	{
		for (int i=0; i<clientes.size(); i++)
		{
			posicion++;
			
			String id_bdd= clientes.get(i).getId_Usuario();
			String contraseña_bdd= clientes.get(i).getContraseña();
			
			if(id.equals(id_bdd) && contraseña.equals(contraseña_bdd))
			{
				System.out.print("Sesión iniciada correctamente. Hola " + clientes.get(i).getNombre()+ " ");
				System.out.println(clientes.get(i).getApellido());
				inicio=true;
				posicion--;
				i_que_opera=i;
				break;
				
			}
			
			else if(id.equals(id_bdd) && !contraseña.equals(contraseña_bdd) )
				{
					System.err.println("Error. Contraseña incorrecta!");
					posicion--;
					System.out.print("Ingrese nuevamente la contraseña ("+ intentos+" intentos restantes): ");
					contraseña=entrada.nextLine();
					intentos--;
					System.out.println();
					i--;
					
					if(intentos==0 && contraseña.equals(contraseña_bdd))
					{
						i++;
						inicio=true;
						System.out.print("Sesión iniciada correctamente. Hola " + clientes.get(i).getNombre()+ " ");
						System.out.println(clientes.get(i).getApellido());
						i_que_opera=i;
						break;
					}
					
					else if (intentos==0 && !contraseña.equals(contraseña_bdd))
					{
						inicio=false; 
						break;
					}
				}
		}
		
		if(posicion==clientes.size())
			{
				System.err.println("Error. El usuario "+ id +" no existe");
				
				System.out.print("Ingrese su ID: ");
				id=entrada.nextLine();
				
				System.out.print("Ingrese su contraseña: ");
				contraseña=entrada.nextLine();
				System.out.println();
				
				posicion=0;;
			}
		else if(posicion<clientes.size())
		{
			break;
		}
	}	//cierra while
	
	
	if (!inicio)
	{
		System.err.println("Fracasó el inicio de sesión.");
	}
	
	if(inicio)
	{
		Cliente_que_opera= clientes.get(i_que_opera);
		
	}
	

	return inicio;
}

public static void Cargar_butacas (ArrayList <Butacas> butacas_sala,String Id_sala)
{
//	sesion_elegida.getId_sala()
	try
	{
		//Registrar JDBC driver
		Class.forName(JDBC_DRIVER);
				 
		// Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 
		// Ejecutar una accion en SQL
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM cinemar2.butaca WHERE cinemar2.butaca.sala_idSALA LIKE '"+ Id_sala+"';";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//Extraer datos del ResultSet
		while(rs.next())
		{		 
			//Recibir por tipo de columna BDD USUARIOS
			String id_butaca=rs.getString("idBUTACA");
			String fila = rs.getString("fila");
			int numero =rs.getInt("numero");
			String id_sala=rs.getString("sala_idSALA");
			String estado= rs.getString("reservada");
		
			Butacas butaca= new Butacas(id_butaca, fila, numero,estado,id_sala);
			butacas_sala.add(butaca);
			
		}
		

		
		
		//Entorno de Limpieza
		rs.close();
		stmt.close();
		conn.close();
		}catch(SQLException se){
			// Resolver errores para JDBC
			se.printStackTrace();
		}catch(Exception e){
			// Resolver errores para Class.forName
			e.printStackTrace();
		}finally{
			// Bloque finalmente utilizado para cerrar recursos
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// Nada que podamos hacer
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
			 se.printStackTrace();
			 }
		} 
}

public static void Cargar_reservas(ArrayList <Reserva_alt> reservas_cinemar)
{
	if(reservas_cinemar.size()>0)
	{
		reservas_cinemar.clear();
	}
	
	//CARGAR RESERVAS
	try
	{
		//Registrar JDBC driver
		Class.forName(JDBC_DRIVER);
				 
		// Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 
		// Ejecutar una accion en SQL
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM cinemar2.reserva;";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//Extraer datos del ResultSet
		while(rs.next())
		{		 
			//Recibir por tipo de columna BDD USUARIOS
			int num_reserva = rs.getInt("numRESERVA");
			String id_reserva = rs.getString("idRESERVA");
			String id_cliente = rs.getString("cliente_idCLIENTE"); 
			String id_butaca= rs.getString("butaca_idBUTACA");

			Reserva_alt reserva = new Reserva_alt(num_reserva, id_reserva, id_cliente,id_butaca);
			reservas_cinemar.add(reserva);
		}
		
		
		System.out.println()	;
		
			//Entorno de Limpieza
			rs.close();
			stmt.close();
			conn.close();
			}catch(SQLException se){
				// Resolver errores para JDBC
				se.printStackTrace();
			}catch(Exception e){
				// Resolver errores para Class.forName
				e.printStackTrace();
			}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
				 se.printStackTrace();
				 }
			} 
	
}

public static void Ver_sesiones(ArrayList <Sesion_alt> sesiones_cinemar)
{
	
	
	//PARTE A-1: CARGO LAS BASE DE DATOS
		try
		{
			//Registrar JDBC driver
			Class.forName(JDBC_DRIVER);
					 
			// Abrir una Conexion
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
					 
			// Ejecutar una accion en SQL
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT cinemar2.sesión.idSESIÓN,día,hora,precio,\r\n"
					+ "cinemar2.película.título,`duración(min)`,\r\n"
					+ "cinemar2.descuento.descuento,\r\n"
					+ "cinemar2.sala.idSALA,tipo\r\n"
					+ "FROM cinemar2.sesión INNER JOIN cinemar2.película INNER JOIN cinemar2.descuento INNER JOIN cinemar2.sala\r\n"
					+ "ON cinemar2.sesión.película_idPELÍCULA=cinemar2.película.idPELÍCULA\r\n"
					+ "AND cinemar2.sesión.descuento_idDESCUENTO=cinemar2.descuento.idDESCUENTO\r\n"
					+ "AND cinemar2.sala.sesión_idSESIÓN=cinemar2.sesión.idSESIÓN;";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			//Extraer datos del ResultSet
			while(rs.next())
			{		 
				//Recibir por tipo de columna BDD USUARIOS
				int id_sesion = rs.getInt("idSESIÓN");
				String dia = rs.getString("día");
				Time hora= rs.getTime("hora"); 
				String peli= rs.getString("título");
				String tipo=rs.getString("tipo");
				int duracion= rs.getInt("duración(min)");
				Double precio =rs.getDouble("precio");
				Float descuento=rs.getFloat("descuento");
				String id_sala=rs.getString("idSALA");
		
				Double precio_final=(1.0-descuento)*precio;
			
				BigDecimal bd = new BigDecimal(precio_final);
				bd= bd.setScale(2,RoundingMode.HALF_UP);
				
				Sesion_alt sesion = new Sesion_alt(id_sesion,dia,hora,peli,duracion,bd,descuento,id_sala,tipo);
				sesiones_cinemar.add(sesion);
			}
			
			//ORDENAMOS LAS SESIONES
			for(int i=0; i<(sesiones_cinemar.size()-2);i++)
			{
				
				for(int j= i+1;j<(sesiones_cinemar.size()-1);j++ )
				{
					if (sesiones_cinemar.get(i).getId()>sesiones_cinemar.get(j).getId())
					{
						Sesion_alt sesion_aux1= sesiones_cinemar.get(i);
						Sesion_alt sesion_aux2= sesiones_cinemar.get(j);
						sesiones_cinemar.set(i, sesion_aux2);
						sesiones_cinemar.set(j, sesion_aux1);
						j=i+1;
					
					}
					else
					{
						j++;
					}
				}
			}
			
			sesiones=sesiones_cinemar;
			
			for(int i=0; i<sesiones_cinemar.size();i++)
			{
				System.out.println()	;
				System.out.print("Sesión N° "+ sesiones_cinemar.get(i).getId()+") ");
				System.out.print(sesiones_cinemar.get(i).getDia()+"  ");
				System.out.print(sesiones_cinemar.get(i).getHora()+"  $");
				System.out.print(sesiones_cinemar.get(i).getPrecio()+"  ");
				System.out.print(sesiones_cinemar.get(i).getPelicula()+"(");
				System.out.print(sesiones_cinemar.get(i).getDuracion()+" min) ");
				System.out.print(sesiones_cinemar.get(i).getTipo()+" ");
			}
			
			
			
			System.out.println()	;
			
				//Entorno de Limpieza
				rs.close();
				stmt.close();
				conn.close();
				}catch(SQLException se){
					// Resolver errores para JDBC
					se.printStackTrace();
				}catch(Exception e){
					// Resolver errores para Class.forName
					e.printStackTrace();
				}finally{
					// Bloque finalmente utilizado para cerrar recursos
					try{
						if(stmt!=null)
							stmt.close();
					}catch(SQLException se2){
					}// Nada que podamos hacer
					try{
						if(conn!=null)
							conn.close();
					}catch(SQLException se){
					 se.printStackTrace();
					 }
				} 
}

public static void Reservar(ArrayList <Butacas> butacas_sala,ArrayList <Reserva_alt> reservas_cinemar)

{
		Ver_sesiones(sesiones);
		@SuppressWarnings("resource")
		Scanner entrada= new Scanner(System.in);
		
		String n= sesiones.size()-10+"";
		char n0=n.charAt(0);
		String regex="^(?:[1-9]|[1-"+n0+"][0-9]|"+sesiones.size()+")$";
		
		System.out.println("Ingrese el número de sesión que desea reservar");
		String navegador= entrada.nextLine();
		navegador= Main.Validar_opcion(regex ,navegador);
		
		int id_sesion_elegida= Integer.parseInt(navegador);
		int indice = id_sesion_elegida-1;
		Sesion_alt sesion_elegida = sesiones.get(indice);
		
		System.out.println();
		System.out.print("Usted seleccionó reservar la Sesión N° "+sesion_elegida.getId() );
		System.out.print(" "+sesion_elegida.getDia()+"  "+sesion_elegida.getHora() );
		System.out.print(" $"+sesion_elegida.getPrecio()+"  "+sesion_elegida.getPelicula());
		System.out.println(" ("+sesion_elegida.getTipo()+")");
		
		System.out.println("¿Está Seguro? (si/no)");
		navegador = entrada.nextLine();
		navegador= Main.Validar_opcion("[s][i]||[n][o]",navegador);
		
		//no está seguro
		if(navegador.equals("no"))
		{
			System.out.println();
			System.out.println("Qué desea hacer?");
			System.out.println("1.Volver al menú");
			System.out.println("2.Salir");
			
			String navegador2 = entrada.nextLine();
			navegador2= Main.Validar_opcion("[1-2]",navegador2);
		
			if(navegador2.equals("1"))
			{
				System.err.println("No se realizó la reserva. Se dirige al menú:");
				FuncionesCliente.Menu_cl();
			}
			
			if(navegador2.equals("2"))
			{
				System.err.println("No se realizó la reserva. Usted saldrá del sistema.");
				
				try {
					
					System.out.println("Hasta pronto " + Cliente_que_opera.getNombre()+" "+Cliente_que_opera.getApellido()+" !");
					
				}catch(Exception e){
					System.out.println("Hasta pronto! ");
				}
						
			}
		}
		
		//si esta seguro
		if(navegador.equals("si"))
		{
			try
			{
				//Registrar JDBC driver
				Class.forName(JDBC_DRIVER);
						 
				// Abrir una Conexion
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
						 
				// Ejecutar una accion en SQL
				stmt = conn.createStatement();
				String sql;
				sql = "SELECT idSALA,numSALA,sesión_idSESIÓN FROM cinemar2.sala WHERE sesión_idSESIÓN="+id_sesion_elegida+";";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				
				//Extraer datos del ResultSet
				while(rs.next())
				{		 
					//Recibir por tipo de columna BDD USUARIOS
					int id_sesion = rs.getInt("sesión_idSESIÓN");
					String id_sala=rs.getString("idSALA");
					int num_sala=rs.getInt("numSALA");
				
					System.out.println("La sesión " +id_sesion +" se transmite en la sala "+ num_sala + " (Codigo: "+id_sala +")");
				}
				
				System.out.println()	;
				
				//Entorno de Limpieza
				rs.close();
				stmt.close();
				conn.close();
				}catch(SQLException se){
					// Resolver errores para JDBC
					se.printStackTrace();
				}catch(Exception e){
					// Resolver errores para Class.forName
					e.printStackTrace();
				}finally{
					// Bloque finalmente utilizado para cerrar recursos
					try{
						if(stmt!=null)
							stmt.close();
					}catch(SQLException se2){
					}// Nada que podamos hacer
					try{
						if(conn!=null)
							conn.close();
					}catch(SQLException se){
					 se.printStackTrace();
					 }
				} 
			
			System.out.println("A continuación, se muestran las butacas disponibles en la sala ");
			System.out.println("Aclaración. si= butaca reservada \t no= butaca no reservada/disponible");
			
			Cargar_butacas(butacas,sesion_elegida.getId_sala());
			for (int i=0; i<butacas.size();i++)
			{
				if(butacas.get(i).getFila().equals("A")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("B")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("C")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("D")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("E")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("F")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("G")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("H")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("I")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				if(butacas.get(i).getFila().equals("J")&&butacas.get(i).getNumero()==1)
				{
					System.out.println();
				}
				System.out.print("    \t");
				System.out.print(butacas.get(i).getFila()+butacas.get(i).getNumero()+" ("+ butacas.get(i).getReservada()+")");
				
				
			}
			
			
			System.out.println();
			System.out.println();
			System.out.print("Ingrese la fila y numero de la Butaca que desea reservar: ");
			
			navegador=entrada.nextLine();
			
			if (sesion_elegida.getTipo().equals("2D"))
			{
				navegador= Main.Validar_opcion("[A-J][1-8]",navegador);
			}
			else if(sesion_elegida.getTipo().equals("3D"))
			{
				navegador= Main.Validar_opcion("[A-E][1-8]",navegador);
				
			}
			
			//GENERO EL CODIGO
			String codigo_butaca_elegida= butacas_sala.get(1).getId_sala()+navegador ;
			
			int posicion=-1;
			
			for (int i=0; i<butacas_sala.size();i++)
			{
				if(butacas_sala.get(i).getCodigo().equals(codigo_butaca_elegida))
				{
					posicion=i;
				}
				
			}
			
			Butacas butaca_elegida= butacas_sala.get(posicion);
			
			if(butaca_elegida.getReservada().equals("no"))
			{
				System.out.println("La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+ " está disponible ");

			}
			
			else
				
			while(butaca_elegida.getReservada().equals("si"))
			{
				System.err.println("Error. La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+" está reservada");
				System.out.print("Seleccione otra:");
				
				String navegador2=entrada.nextLine();
				
				if (sesion_elegida.getTipo().equals("2D"))
				{
					navegador2= Main.Validar_opcion("[A-J][1-8]",navegador2);
				}
				
				else if(sesion_elegida.getTipo().equals("3D"))
				{
					navegador2= Main.Validar_opcion("[A-E][1-8]",navegador2);
				}
				
				//GENERO EL CODIGO
				codigo_butaca_elegida= butacas_sala.get(1).getId_sala()+navegador2 ;
				
				 posicion=-1;
				
				for (int i=0; i<butacas_sala.size();i++)
				{
					if(butacas_sala.get(i).getCodigo().equals(codigo_butaca_elegida))
					{
						posicion=i;
					}
					
				}
				
				butaca_elegida=butacas_sala.get(posicion);
				
				if(butaca_elegida.getReservada().equals("no"))
				{
					System.out.println("La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+ " está disponible ");
					break;
				}

				
			}
			
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Confirmar la reserva");
		System.out.println("2. Volver al Menú");
		
		navegador=entrada.nextLine();
		navegador= Main.Validar_opcion("[1-2]",navegador);
		
		if (navegador.equals("1"))
		{
			try{
				
				//Registrar JDBC driver
				 Class.forName(JDBC_DRIVER);
								 
				//Abrir una Conexion
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
							
				
				ps=conn.prepareStatement("UPDATE cinemar2.butaca SET reservada = ? WHERE (idBUTACA = ?);");
				ps.setString(1, "si");
				ps.setString(2,butaca_elegida.getCodigo());
								
				//Ejecuta
				ps.executeUpdate();
				ps.close();
				conn.close();
								
				butaca_elegida.setReservada("si");	
				
				butacas.set(posicion, butaca_elegida);
				
//				butacas=butacas_sala;
				
				}catch(ClassNotFoundException ce)
				{
					ce.printStackTrace();
				}catch(SQLException se)
				{
					se.printStackTrace();
								
				}catch(Exception e){
					e.printStackTrace();
								
							
					}finally{
					// Bloque finalmente utilizado para cerrar recursos
					try{
						if(ps!=null)
						ps.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
					try{
						if(conn!=null)
							conn.close();
				}catch(SQLException se){
						se.printStackTrace();
						}
				} 		
				
			//CARGAR RESERVAS
			Cargar_reservas(reservas_cinemar);
			
			String fecha_string="";
			//AÑADIR A LA BASE DE DATOS "RESERVA"
			if(sesion_elegida.getDia().equals("Lunes"))
			{
				fecha_string="110722";
			}
			else if(sesion_elegida.getDia().equals("Martes"))
			{
				fecha_string="120722";

			}
			else if(sesion_elegida.getDia().equals("Miercoles"))
			{
				fecha_string="130722";

			}
			else if(sesion_elegida.getDia().equals("Jueves"))
			{
				fecha_string="140722";

			}
			else if(sesion_elegida.getDia().equals("Viernes"))
			{
				fecha_string="150722";

			}
			else if(sesion_elegida.getDia().equals("Sabado"))
			{
				fecha_string="160722";

			}
			else if(sesion_elegida.getDia().equals("Domingo"))
			{
				fecha_string="170722";

			}
			
			String codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;
		
			int num_reserva_nueva = reservas_cinemar.get(reservas_cinemar.size()-1).getNum_reserva()+1;
		
			
			try{
				
				//Registrar JDBC driver
				 Class.forName(JDBC_DRIVER);
								 
				//Abrir una Conexion
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
							
				//Carga de datos por columna
				ps=conn.prepareStatement("INSERT INTO reserva VALUES (?, ?, ?, ? )");
				ps.setInt(1, num_reserva_nueva);
				ps.setString(2,codigo_reserva);
				ps.setString(3,Cliente_que_opera.getId_Usuario());
				ps.setString(4,butaca_elegida.getCodigo());
								
				//Ejecuta la carga
				ps.executeUpdate();
				
				
				
				ps.close();
				conn.close();
		
				
				Reserva_alt reserva = new Reserva_alt(num_reserva_nueva, codigo_reserva, Cliente_que_opera.getId_Usuario(),butaca_elegida.getCodigo());
								
			
				//Agrego el usuario en el arreglo precreado
				reservas_cinemar.add(reserva);
			
				
				}catch(ClassNotFoundException ce)
				{
					ce.printStackTrace();
				}catch(SQLException se)
				{
					se.printStackTrace();
								
				}catch(Exception e){
					e.printStackTrace();
								
							
					}finally{
					// Bloque finalmente utilizado para cerrar recursos
					try{
						if(ps!=null)
						ps.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
					try{
						if(conn!=null)
							conn.close();
				}catch(SQLException se){
						se.printStackTrace();
						}
				} 
			
			
			System.out.println("La reserva se realizó con exito. Reserva N° : " + reservas_cinemar.get(reservas_cinemar.size()-1).getNum_reserva()+" . Codigo " + reservas.get(reservas.size()-1).getId_reserva() );
			
			Menu_cl();
			
		}
		else if(navegador.equals("2"))
		{
			System.err.println("No se realizó la reserva. Se dirige al menú:");
			Menu_cl();
		}
		
	}//FIN IF ESTA SEGURO
}
			

public static void Ver_misReservas(ArrayList <Reserva_alt> mis_reservas_cinemar)


{
	
	if(mis_reservas_cinemar.size()>0 && mis_reservas_cinemar.get(0).equals(mis_reservas_cinemar.get(1)))
	{
		mis_reservas_cinemar.clear();
	}
//	ArrayList <Reserva_alt> mis_reservas_cinemar = new ArrayList <Reserva_alt> ();

	try
	{
		//Registrar JDBC driver
		Class.forName(JDBC_DRIVER);
				 
		// Abrir una Conexion
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
				 
		// Ejecutar una accion en SQL
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT cinemar2.reserva.numRESERVA,idRESERVA,cliente_idCLIENTE,\r\n"
				+ "cinemar2.butaca.idBUTACA,fila,numero,\r\n"
				+ "cinemar2.sala.idSALA,numSALA,tipo,\r\n"
				+ "cinemar2.sesión.idSESIÓN,día,hora,\r\n"
				+ "cinemar2.película.título\r\n"
				+ "FROM cinemar2.reserva INNER JOIN cinemar2.butaca INNER JOIN cinemar2.sala INNER JOIN cinemar2.sesión INNER JOIN cinemar2.película\r\n"
				+ "ON cinemar2.butaca.idBUTACA=cinemar2.reserva.butaca_idBUTACA\r\n"
				+ "AND cinemar2.butaca.sala_idSALA=cinemar2.sala.idSALA\r\n"
				+ "AND cinemar2.sesión.idSESIÓN=cinemar2.sala.sesión_idSESIÓN\r\n"
				+ "AND cinemar2.película.idPELÍCULA=cinemar2.sesión.película_idPELÍCULA\r\n"
				+ "AND cinemar2.reserva.cliente_idCLIENTE LIKE '"+Cliente_que_opera.getId_Usuario()+"';";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//Extraer datos del ResultSet
		while(rs.next())
		{	
			
			//Recibir por tipo de columna BDD USUARIOS
			int num_reserva = rs.getInt("numRESERVA");
			String id_reserva = rs.getString("idRESERVA");
			String id_butaca= rs.getString("idBUTACA");
			String fila= rs.getString("fila");
			int numero= rs.getInt("numero");
			String id_sala=rs.getString("idSALA");
			int num_sala=rs.getInt("numSALA");
			String tipo= rs.getString("tipo");
			int id_sesion= rs.getInt("idSESIÓN");
			String dia= rs.getString("día");
			Time hora= rs.getTime("hora"); 
			String peli= rs.getString("título");
			
			Reserva_alt mi_reserva = new Reserva_alt(id_sesion,dia,hora,peli,tipo,num_sala,id_sala,id_butaca,fila,numero,id_reserva,num_reserva);
			
			mis_reservas_cinemar.add(mi_reserva);
	
		}
		
		if (mis_reservas_cinemar.size()==0)
		{
			System.out.println("Usted no tiene reservas!");
			System.out.println("Pruebe con otra opcion: ");
			Menu_cl();
		}
		
		else
		
				
			//Entorno de Limpieza
			rs.close();
			stmt.close();
			conn.close();
			}catch(SQLException se){
				// Resolver errores para JDBC
				se.printStackTrace();
			}catch(Exception e){
				// Resolver errores para Class.forName
				e.printStackTrace();
			}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
				 se.printStackTrace();
				 }
			}
	
	//ORDENAMOS LAS SESIONES
	for(int i=0; i<(mis_reservas_cinemar.size()-2);i++)
	{
		
		for(int j= i+1;j<(mis_reservas_cinemar.size()-1);j++ )
		{
			if (mis_reservas.get(i).getId_sesion()>mis_reservas_cinemar.get(j).getId_sesion())
			{
				Reserva_alt reserva_aux1= mis_reservas_cinemar.get(i);
				Reserva_alt reserva_aux2= mis_reservas_cinemar.get(j);
				mis_reservas_cinemar.set(i, reserva_aux2);
				mis_reservas_cinemar.set(j, reserva_aux1);
				j=i+1;
			
			}
			else
			{
				j++;
			}
		}
	}
	

	
	System.out.println("Para esta semana usted reservó:");
	for(int i=0; i<mis_reservas_cinemar.size();i++)
	{
		System.out.println();
		System.out.print((i+1)+". ");
		System.out.print(mis_reservas_cinemar.get(i).getDia()+"  ");
		System.out.print(mis_reservas_cinemar.get(i).getHora()+"  ");
		System.out.print(mis_reservas_cinemar.get(i).getPeli()+" (");
		System.out.print(mis_reservas_cinemar.get(i).getTipo()+")  Sala ");
		System.out.print(mis_reservas_cinemar.get(i).getNum_sala()+" Butaca:  ");
		System.out.print(mis_reservas_cinemar.get(i).getFila()+"");
		System.out.print(mis_reservas_cinemar.get(i).getNumero()+"");

	}
	
	
}


public static void Modificar_reserva(ArrayList <Reserva_alt> mis_reservas, ArrayList <Butacas> butacas)
{
	System.out.println();
	System.out.println("Espere un momento...");
	System.out.println();

	@SuppressWarnings("resource")
	Scanner entrada = new Scanner (System.in);
	Ver_misReservas(mis_reservas);
	System.out.println();
	System.out.println();
	
	int total_reservas= mis_reservas.size();

	
	
	
	System.out.print("Ingrese el numero de reserva de la reserva que modifcará (1 al "+total_reservas+ "): ");
	String navegador = entrada.nextLine();
	System.out.println();
	
	navegador= Main.Validar_opcion("[1-9]|[1-4][0-9]", navegador);
	
	int opcion= Integer.parseInt(navegador);
	
	

	while(opcion>total_reservas)
			
		{
			System.err.println("Error. Numero no valido");
			System.out.print("Ingrese una opcion válida (1 al "+total_reservas+ "): ");
			navegador = entrada.nextLine();
			navegador= Main.Validar_opcion("[1-9]|[1-4][0-9]", navegador);
			opcion= Integer.parseInt(navegador);
			
			if(opcion<total_reservas)
			{
	
				break;
			}
		}
	
	int indice_reserva = opcion-1;
	Reserva_alt reserva_modificar = mis_reservas.get(indice_reserva);	
	Cargar_butacas(butacas,reserva_modificar.getId_sala());
	
	System.out.print("Está a punto de modificar la reserva N° "+ reserva_modificar.getNum_reserva());
	System.out.println("   de ID = "+reserva_modificar.getId_reserva()+".");
	System.out.println();
	System.out.println("Qué desea hacer?");
	System.out.println("1.Cancelar Reserva");
	System.out.println("2.Elegir otra butaca");
	
	String navegador2 = entrada.nextLine();
	navegador2=Main.Validar_opcion("[1-2]", navegador2);
	
	if (navegador2.equals("1"))
	{
		
		try{
			
			//Registrar JDBC driver
			 Class.forName(JDBC_DRIVER);
							 
			//Abrir una Conexion
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
						
			
			ps=conn.prepareStatement("DELETE FROM cinemar2.reserva WHERE idRESERVA = ? ;");
			ps.setString(1,reserva_modificar.getId_reserva());
							
			//Ejecuta
			ps.executeUpdate();
			ps.close();
			conn.close();
							
			mis_reservas.remove(indice_reserva);
			
			}catch(ClassNotFoundException ce)
			{
				ce.printStackTrace();
			}catch(SQLException se)
			{
				se.printStackTrace();
							
			}catch(Exception e){
				e.printStackTrace();
							
						
				}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(ps!=null)
					ps.close();
			}catch(SQLException se2){
			}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
			}catch(SQLException se){
					se.printStackTrace();
					}
			} 
		
		
		//CAMBIAMOS EL ESTADO DE LA BUTACA A LIBRE
		try{
			
			//Registrar JDBC driver
			 Class.forName(JDBC_DRIVER);
							 
			//Abrir una Conexion
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
						
			
			ps=conn.prepareStatement("UPDATE cinemar2.butaca SET reservada = ? WHERE idBUTACA = ? ;");
			ps.setString(1,"no");
			ps.setString(2, reserva_modificar.getId_butaca());	
			
			//Ejecuta
			ps.executeUpdate();
			ps.close();
			conn.close();
		
			int indice_butaca=-1;
			
			for(int i=0;i<butacas.size();i++)
			{
				if(butacas.get(i).getCodigo().equals(reserva_modificar.getId_butaca()))
				{
					indice_butaca=i;
				}
			}
			
			butacas.get(indice_butaca).setReservada("no");
			
			}catch(ClassNotFoundException ce)
			{
				ce.printStackTrace();
			}catch(SQLException se)
			{
				se.printStackTrace();
							
			}catch(Exception e){
				e.printStackTrace();
							
						
				}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(ps!=null)
					ps.close();
			}catch(SQLException se2){
			}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
			}catch(SQLException se){
					se.printStackTrace();
					}
			} 
		
		
//		try
//		{
//			//Registrar JDBC driver
//			Class.forName(JDBC_DRIVER);
//					 
//			// Abrir una Conexion
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//					 
//			// Ejecutar una accion en SQL
//			stmt = conn.createStatement();
//			String sql;
//			sql = "SELECT * FROM cinemar2.butaca WHERE cinemar2.butaca.sala_idSALA LIKE '"+ reserva_modificar.getId_sala()+"';";
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			//Extraer datos del ResultSet
//			while(rs.next())
//			{		 
//				//Recibir por tipo de columna BDD USUARIOS
//				String id_butaca=rs.getString("idBUTACA");
//				String fila = rs.getString("fila");
//				int numero =rs.getInt("numero");
//				String id_sala=rs.getString("sala_idSALA");
//				String estado= rs.getString("reservada");
//			
//				Butacas butaca= new Butacas(id_butaca, fila, numero,estado,id_sala);
//				butacas.add(butaca);
//				
//			}
		
		System.out.println("Reservas actualizadas para la semana :");
		for(int i=0; i<mis_reservas.size();i++)
		{
			System.out.println();
			System.out.print((i+1)+". ");
			System.out.print(mis_reservas.get(i).getDia()+"  ");
			System.out.print(mis_reservas.get(i).getHora()+"  ");
			System.out.print(mis_reservas.get(i).getPeli()+" (");
			System.out.print(mis_reservas.get(i).getTipo()+")  Sala ");
			System.out.print(mis_reservas.get(i).getNum_sala()+" Butaca:  ");
			System.out.print(mis_reservas.get(i).getFila()+"");
			System.out.print(mis_reservas.get(i).getNumero()+"");

		}

		
		
	}
	
	else if (navegador2.equals("2"))
	{
	
		//1°COLOCO LA BUTACA INICIAL COMO DESOCUPADA
		//CAMBIAMOS EL ESTADO DE LA BUTACA A LIBRE
				try{
					
					//Registrar JDBC driver
					 Class.forName(JDBC_DRIVER);
									 
					//Abrir una Conexion
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
								
					
					ps=conn.prepareStatement("UPDATE cinemar2.butaca SET reservada = ? WHERE idBUTACA = ? ;");
					ps.setString(1,"no");
					ps.setString(2, reserva_modificar.getId_butaca());	
					
					//Ejecuta
					ps.executeUpdate();
					ps.close();
					conn.close();
					
					int indice_butaca=-1;
					
					for(int i=0;i<butacas.size();i++)
					{
						if(butacas.get(i).getCodigo().equals(reserva_modificar.getId_butaca()))
						{
							indice_butaca=i;
						}
					}
					
					butacas.get(indice_butaca).setReservada("no");
					
									
					}catch(ClassNotFoundException ce)
					{
						ce.printStackTrace();
					}catch(SQLException se)
					{
						se.printStackTrace();
									
					}catch(Exception e){
						e.printStackTrace();
									
								
						}finally{
						// Bloque finalmente utilizado para cerrar recursos
						try{
							if(ps!=null)
							ps.close();
					}catch(SQLException se2){
					}// Nada que podamos hacer
						try{
							if(conn!=null)
								conn.close();
					}catch(SQLException se){
							se.printStackTrace();
							}
					} 
				
		//ELIJO LA NUEVA BUTACA;
		
				System.out.println("A continuación, se muestran las butacas disponibles en la sala ");
				System.out.println("Aclaración. si= butaca reservada \t no= butaca no reservada/disponible \t ");
				
				

					for (int i=0; i<butacas.size();i++)
					{
						if(butacas.get(i).getFila().equals("A")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("B")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("C")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("D")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("E")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("F")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("G")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("H")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("I")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						if(butacas.get(i).getFila().equals("J")&&butacas.get(i).getNumero()==1)
						{
							System.out.println();
						}
						System.out.print("    \t");
						System.out.print(butacas.get(i).getFila()+butacas.get(i).getNumero()+" ("+ butacas.get(i).getReservada()+")");
						
						
					}
					 
				
				System.out.println();
				System.out.println();
				System.out.print("Ingrese la fila y numero de la Butaca que desea reservar: ");
				
				navegador=entrada.nextLine();
				
				if (reserva_modificar.getTipo().equals("2D"))
				{
					navegador= Main.Validar_opcion("[A-J][1-8]",navegador);
				}
				else if(reserva_modificar.getTipo().equals("3D"))
				{
					navegador= Main.Validar_opcion("[A-E][1-8]",navegador);
					
				}
				
				//GENERO EL CODIGO de la BUTACA
				String codigo_butaca_elegida= reserva_modificar.getId_sala()+navegador ;
				
				int posicion=-1;
				
				for (int i=0; i<butacas.size();i++)
				{
					if(butacas.get(i).getCodigo().equals(codigo_butaca_elegida))
					{
						posicion=i;
					}
					
				}
				
				Butacas butaca_elegida= butacas.get(posicion);
				
				if(butaca_elegida.getReservada().equals("no"))
				{
					System.out.println("La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+ " está disponible ");

				}
				
				else
					
				while(butaca_elegida.getReservada().equals("si"))
				{
					System.err.println("Error. La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+" está reservada");
					System.out.print("Seleccione otra:");
					
					String navegador3=entrada.nextLine();
					
					if (reserva_modificar.getTipo().equals("2D"))
					{
						navegador3= Main.Validar_opcion("[A-J][1-8]",navegador3);
					}
					
					else if(reserva_modificar.getTipo().equals("3D"))
					{
						navegador3= Main.Validar_opcion("[A-E][1-8]",navegador3);
					}
					
					
					codigo_butaca_elegida= reserva_modificar.getId_sala()+navegador3 ;
					
					 posicion=-1;
					
					for (int i=0; i<butacas.size();i++)
					{
						if(butacas.get(i).getCodigo().equals(codigo_butaca_elegida))
						{
							posicion=i;
						}
						
					}
					
					butaca_elegida=butacas.get(posicion);
					
					if(butaca_elegida.getReservada().equals("no"))
					{
						System.out.println("La butaca "+butaca_elegida.getFila()+butaca_elegida.getNumero()+ " está disponible ");
						break;
					}

					
				}	
	
				
				
				
		//GENERO EL CODIGO DE RESERVA
				String fecha_string="";
				String codigo_reserva="";
				
				//AÑADIR A LA BASE DE DATOS "RESERVA"
				if(reserva_modificar.getDia().equals("Lunes"))
				{
					fecha_string="110722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;

					
				}
				else if(reserva_modificar.getDia().equals("Martes"))
				{
					fecha_string="120722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				else if(reserva_modificar.getDia().equals("Miercoles"))
				{
					fecha_string="130722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				else if(reserva_modificar.getDia().equals("Jueves"))
				{
					fecha_string="140722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				else if(reserva_modificar.getDia().equals("Viernes"))
				{
					fecha_string="150722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				else if(reserva_modificar.getDia().equals("Sabado"))
				{
					fecha_string="160722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				else if(reserva_modificar.getDia().equals("Domingo"))
				{
					fecha_string="170722";
					codigo_reserva= butaca_elegida.getCodigo()+"-"+fecha_string;


				}
				
				
		//ACTUALIZO LA BDD RESERVAS CON EL NUEVO CODIGO DE BUTACA
		try{
			
			//Registrar JDBC driver
			 Class.forName(JDBC_DRIVER);
							 
			//Abrir una Conexion
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
						
			
			ps=conn.prepareStatement("UPDATE cinemar2.reserva SET idRESERVA =?, butaca_idBUTACA =? WHERE (numRESERVA =?);");
			
			ps.setString(2,butaca_elegida.getCodigo());
			ps.setString(1, codigo_reserva);	
			ps.setInt(3, reserva_modificar.getNum_reserva());
			
			//Ejecuta
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			System.out.print("Se modificó la reserva N° "+ reserva_modificar.getNum_reserva());
			System.out.println(". El nuevo codigo de reserva es "+codigo_reserva);
							
			}catch(ClassNotFoundException ce)
			{
				ce.printStackTrace();
			}catch(SQLException se)
			{
				se.printStackTrace();
							
			}catch(Exception e){
				e.printStackTrace();
							
						
				}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(ps!=null)
					ps.close();
			}catch(SQLException se2){
			}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
			}catch(SQLException se){
					se.printStackTrace();
					}
			} 	

					
		//CAMBIAMOS EL ESTADO DE LA NUEVA BUTACA A OCUPADA
		try{
			
			//Registrar JDBC driver
			 Class.forName(JDBC_DRIVER);
							 
			//Abrir una Conexion
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
						
			
			ps=conn.prepareStatement("UPDATE cinemar2.butaca SET reservada = ? WHERE idBUTACA = ? ;");
			ps.setString(1,"si");
			ps.setString(2, butaca_elegida.getCodigo());	
			
			//Ejecuta
			ps.executeUpdate();
			ps.close();
			conn.close();
							

			int indice_butaca=-1;
			
			for(int i=0;i<butacas.size();i++)
			{
				if(butacas.get(i).getCodigo().equals(reserva_modificar.getId_butaca()))
				{
					indice_butaca=i;
				}
			}
			
			butacas.get(indice_butaca).setReservada("si");	
			
			}catch(ClassNotFoundException ce)
			{
				ce.printStackTrace();
			}catch(SQLException se)
			{
				se.printStackTrace();
							
			}catch(Exception e){
				e.printStackTrace();
							
						
				}finally{
				// Bloque finalmente utilizado para cerrar recursos
				try{
					if(ps!=null)
					ps.close();
			}catch(SQLException se2){
			}// Nada que podamos hacer
				try{
					if(conn!=null)
						conn.close();
			}catch(SQLException se){
					se.printStackTrace();
					}
			} 	
		System.out.println();
		
		Reserva_alt reserva_modificada= reserva_modificar;
		reserva_modificada.setFila(butaca_elegida.getFila());
		reserva_modificada.setNumero(butaca_elegida.getNumero());
		reserva_modificada.setId_reserva(codigo_reserva);
		reserva_modificada.setId_butaca(codigo_butaca_elegida);
		
		mis_reservas.set(indice_reserva, reserva_modificada);
		
		System.out.println("Sus reservas actualizadas:");
		for(int i=0; i<mis_reservas.size();i++)
		{
			System.out.println();
			System.out.print((i+1)+". ");
			System.out.print(mis_reservas.get(i).getDia()+"  ");
			System.out.print(mis_reservas.get(i).getHora()+"  ");
			System.out.print(mis_reservas.get(i).getPeli()+" (");
			System.out.print(mis_reservas.get(i).getTipo()+")  Sala ");
			System.out.print(mis_reservas.get(i).getNum_sala()+" Butaca:  ");
			System.out.print(mis_reservas.get(i).getFila()+"");
			System.out.print(mis_reservas.get(i).getNumero()+"");

		}
		
		
	}
	
	Menu_cl();
}	
		
	


public static void Menu_cl()

{
	
	System.out.println();
	System.out.println("¿Que desea hacer?");
	System.out.println("1. Hacer una reserva. ");
	System.out.println("2. Modificar una reserva. ");
	System.out.println("3. Ver sus reservas.");
	
	@SuppressWarnings("resource")
	Scanner entrada= new Scanner(System.in);
	
	String navegador = entrada.nextLine();
	navegador= Main.Validar_opcion("[1-3]",navegador);
	
	
	//HACER UNA RESERVA
	if(navegador.equals("1"))
	{
		Reservar(butacas,reservas);
	}
	
	
	//MODIFICAR UNA RESERVA
	else if(navegador.equals("2"))
	{
		
		
		Modificar_reserva(mis_reservas, butacas);
		
		
	}

	
	//VER MIS RESERVAS
	else if(navegador.equals("3"))
	{
		Ver_misReservas(mis_reservas);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Qué desea hacer?");
		System.out.println("1.Volver al menú");
		System.out.println("2.Salir");
		
		String navegador2 = entrada.nextLine();
		navegador2= Main.Validar_opcion("[1-2]",navegador2);
	
		if(navegador2.equals("1"))
		{
			System.out.println();
			System.out.println("Se dirige al menú:");
			FuncionesCliente.Menu_cl();
		}
		
		if(navegador2.equals("2"))
		{
			System.out.println();
			System.out.println("Usted saldrá del sistema.");
			
			try {
				
				System.out.println("Hasta pronto " + Cliente_que_opera.getNombre()+" "+Cliente_que_opera.getApellido()+" !");
				
			}catch(Exception e){
				System.out.println("Hasta pronto! ");
			}
					
		}
		
		
	}
	
}




}

				 	
