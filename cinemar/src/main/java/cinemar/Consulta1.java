//usuarios registrados en 2021

package cinemar;

import java.util.ArrayList;

import static spark.Spark.get;
import static spark.Spark.port;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import org.json.JSONObject;

import com.google.gson.Gson;

import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;

public class Consulta1 {

	static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	static final String DB_URL= "jdbc:mysql://localhost:3306/cinemar2";
	
	//CREDENCIALES
	static final String USER ="root";
	static final String PASS = "JuanRoman10!";
		
public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		JSONObject jo = new JSONObject();
		
		 ArrayList <Cliente> clientes = new ArrayList();
		
		try{
		 //PASO 2: Registrar JDBC driver
		 Class.forName(JDBC_DRIVER);
		 
		 //PASO3: Abrir una Conexion
		 System.out.println("Connectando a la base de datos...");
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 //PASO 4: Ejecutar una consulta SQL
		 System.out.println("Creando consulta");
		 stmt = conn.createStatement();
		 String sql;
			sql = "SELECT cinemar2.usuario.numUSUARIO,nombre,apellido , cinemar2.cliente.idCLIENTE,fechaRegistro\r\n"
					+ " FROM cinemar2.cliente INNER JOIN cinemar2.usuario WHERE fechaRegistro LIKE '2021%' AND \r\n"
					+ " cinemar2.usuario.numUSUARIO=cinemar2.cliente.usuario_numUSUARIO;";
		 
		 ResultSet rs = stmt.executeQuery(sql);
		 
		//STEP 5: Extraer datos del ResultSet
	
		 //Recibir por tipo de columna
			//Extraer datos del ResultSet
				while(rs.next())
				{		 
					//Recibir por tipo de columna BDD USUARIOS
					int num_usuario = rs.getInt("numUSUARIO");
					String nombre = rs.getString("nombre"); 
					String apellido= rs.getString("apellido");
					String id=rs.getString("idCLIENTE");
					Date fecha_r= rs.getDate("fechaRegistro");				
					
					Cliente cliente = new Cliente();
					cliente.setId_Usuario(id); cliente.setNombre(nombre);cliente.setApellido(apellido);cliente.setFecha_registro(fecha_r);

					
					clientes.add(cliente);
				}
		
		//PASO6: Entorno de Limpieza
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
		 	} //cierra finally try
		 } //cierra try
		
		  String log4jConfPath = "C:/Users/nico_/OneDrive/Escritorio/CURSO 1000 PROGRAMADORES/CinemarSARDINA/cinemar/to/log4j.properties";
		   PropertyConfigurator.configure(log4jConfPath);
	       port(9098);
	       String json= new Gson().toJson(clientes);
	      get("/consulta", (req,res) -> json);
		 System.out.println("Goodbye!");
		 
	} // cierra metodo principal (main)

} // cierra clase
		 
		 

