package cinemar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesAdmin {
	
	static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	static final String DB_URL= "jdbc:mysql://localhost:3306/cinemar2";
	
	//CREDENCIALES
	static final String USER ="root";
	static final String PASS = "JuanRoman10!";

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps= null;
	
	public FuncionesAdmin()
	{
	}
	
	public void Cargar_admin(ArrayList <Administrador> administradores_cinemar)
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
					+ "cinemar2.administrador.idADMINISTRADOR,contraseña,antigüedad \r\n"
					+ "FROM cinemar2.usuario INNER JOIN cinemar2.administrador\r\n"
					+ "ON cinemar2.usuario.numUSUARIO = cinemar2.administrador.usuario_numUSUARIO;";
			
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
				String id = rs.getString("idADMINISTRADOR");
				String contraseña=rs.getString("contraseña");
				int antigüedad = rs.getInt("antigüedad");
				
				 //Creo un nuevo Admin con los datos extraidos de la base de datos y lo agrego en un ArrayList
				Administrador admin= new Administrador (nombre,apellido,fecha_n,celular,email,rolU,id,contraseña,antigüedad);
				administradores_cinemar.add(admin);
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

			//MUestra los admins cargados en la base de datos
//			for(int i=0; i<administradores_cinemar.size();i++)
//			{
//				 System.out.println("Admin "  + (i+1));
//				 System.out.println("Id: " + (administradores_cinemar.get(i).getId_administrador()));
//				 System.out.println("Contraseña: " +(administradores_cinemar.get(i).getContraseña()));
//				 System.out.println("Usted es administradore hace " +(administradores_cinemar.get(i).getAntiguedad()));
//				 System.out.println("Nombre: "+ administradores_cinemar.get(i).getNombre());
//				 System.out.println("Apellido: "+ administradores_cinemar.get(i).getApellido());
//				 System.out.println("Gmail: "+ administradores_cinemar.get(i).getEmail());
//
//			}
		
	}

	
public static boolean Inicio_Sesión()
	{
		ArrayList <Administrador> administradores_cinemar= new ArrayList <Administrador> ();
		FuncionesAdmin f = new FuncionesAdmin();
		
		@SuppressWarnings("resource")
		Scanner entrada= new Scanner (System.in);
		
		//Cargo la base de datos en un ArrayList
		f.Cargar_admin(administradores_cinemar);
		
		System.out.println("Para accerder a la zona de Administradores, ingrese su ID y contraseña!");
		System.out.print("Ingrese su ID: ");
		String id= entrada.nextLine();
		
		System.out.print("Ingrese su contraseña: ");
		String contraseña = entrada.nextLine();
		System.out.println();
		
		//BUSCAMOS LOS DATOS INGRESADOS EN LA BASE DE DATOS
		int posicion=0;
		boolean inicio=false;
		int intentos=3;
		
		while (posicion<=administradores_cinemar.size())
		{
			for (int i=0; i<administradores_cinemar.size(); i++)
			{
				posicion++;
				
				String id_bdd= administradores_cinemar.get(i).getId_administrador();
				String contraseña_bdd= administradores_cinemar.get(i).getContraseña();
				
				if(id.equals(id_bdd) && contraseña.equals(contraseña_bdd))
				{
					System.out.print("Hola " + administradores_cinemar.get(i).getNombre()+ " ");
					System.out.println(administradores_cinemar.get(i).getApellido());
					inicio=true;
					posicion--;
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
							System.out.print("Hola " + administradores_cinemar.get(i).getNombre()+ " ");
							System.out.println(administradores_cinemar.get(i).getApellido());
							break;
						}
						
						else if (intentos==0 && !contraseña.equals(contraseña_bdd))
						{
							inicio=false; 
							break;
						}
					}
			}
			
			if(posicion==administradores_cinemar.size())
				{
					System.err.println("Error. El administrador "+ id +" no existe");
					
					System.out.print("Ingrese su ID: ");
					id=entrada.nextLine();
					
					System.out.print("Ingrese su contraseña: ");
					contraseña=entrada.nextLine();
					System.out.println();
					
					posicion=0;;
				}
			else if(posicion<administradores_cinemar.size())
			{
				break;
			}
		}	//cierra while
		
		if (!inicio)
		{
			System.err.println("Fracasó el inicio de sesión.");
		}
		return inicio;
	}

public static void Menu_adm()
{
	System.out.println("¿Qué desea hacer?");
	System.out.println("1. Crear una sesión. ");
	System.out.println("2. Modificar una sesión. ");
	System.out.println("3. Eliminar una sesión. ");
	System.out.println("4. Modificar descuentos. ");
	System.out.println("5. Ver todas las reservas por fecha");
	System.out.println("5. Ver total de reservas para una sesión");
}
}
