//CONSULTA: TODAS LAS SESIONES DE LA SEMANA. TIENE DATOS DE VARIAS TABLAS

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

public class toBDD {

	static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	static final String DB_URL= "jdbc:mysql://localhost:3306/cinemar2";
	
	//CREDENCIALES
	static final String USER ="root";
	static final String PASS = "JuanRoman10!";
		
public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		JSONObject jo = new JSONObject();
		
		 ArrayList <Sesion_alt> sesiones = new ArrayList();
		
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
			sql = "SELECT cinemar2.sesi?n.idSESI?N,d?a,hora,precio,\r\n"
					+ "cinemar2.pel?cula.t?tulo,`duraci?n(min)`,\r\n"
					+ "cinemar2.descuento.descuento,\r\n"
					+ "cinemar2.sala.idSALA,tipo\r\n"
					+ "FROM cinemar2.sesi?n INNER JOIN cinemar2.pel?cula INNER JOIN cinemar2.descuento INNER JOIN cinemar2.sala\r\n"
					+ "ON cinemar2.sesi?n.pel?cula_idPEL?CULA=cinemar2.pel?cula.idPEL?CULA\r\n"
					+ "AND cinemar2.sesi?n.descuento_idDESCUENTO=cinemar2.descuento.idDESCUENTO\r\n"
					+ "AND cinemar2.sala.sesi?n_idSESI?N=cinemar2.sesi?n.idSESI?N;";
		 
		 ResultSet rs = stmt.executeQuery(sql);
		 
		//STEP 5: Extraer datos del ResultSet
	
		 //Recibir por tipo de columna
			//Extraer datos del ResultSet
				while(rs.next())
				{		 
					//Recibir por tipo de columna BDD USUARIOS
					int id_sesion = rs.getInt("idSESI?N");
					String dia = rs.getString("d?a");
					Time hora= rs.getTime("hora"); 
					String peli= rs.getString("t?tulo");
					String tipo=rs.getString("tipo");
					int duracion= rs.getInt("duraci?n(min)");
					Double precio =rs.getDouble("precio");
					Float descuento=rs.getFloat("descuento");
					String id_sala=rs.getString("idSALA");
			
					Double precio_final=(1.0-descuento)*precio;
				
					BigDecimal bd = new BigDecimal(precio_final);
					bd= bd.setScale(2,RoundingMode.HALF_UP);
					Sesion_alt sesion = new Sesion_alt(id_sesion,dia,hora,peli,duracion,bd,descuento,id_sala,tipo);
					
					sesiones.add(sesion);
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
	       port(8900);
	       String json= new Gson().toJson(sesiones);
	      get("/consulta", (req,res) -> json);
		 System.out.println("Goodbye!");
		 
	} // cierra metodo principal (main)

} // cierra clase
		 
		 


