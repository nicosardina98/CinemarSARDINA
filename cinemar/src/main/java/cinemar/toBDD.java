package cinemar;

import java.util.ArrayList;
import static spark.Spark.get;
import static spark.Spark.port;

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
		ArrayList <Descuento> mis_Descuentos= new ArrayList();
		
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
		 sql = "SELECT idDESCUENTO,descuento FROM descuento;";
		 ResultSet rs = stmt.executeQuery(sql);
		 
		//STEP 5: Extraer datos del ResultSet
		 while(rs.next()){
		 //Recibir por tipo de columna
			 String id_descuento = rs.getString("idDESCUENTO");
			 float descuento = rs.getFloat("descuento");
			 System.out.println("id_descuento: "+ id_descuento + " descuento: " + descuento );
			 
			 //Creo el usuario y agrego a un arraylist
			 Descuento mi_descuento= new Descuento(id_descuento,descuento);
			 mis_Descuentos.add(mi_descuento);
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
		
		  String log4jConfPath = "C:/Users/nico_/eclipse-workspace/cinemar/to/log4j.properties";
		   PropertyConfigurator.configure(log4jConfPath);
	       port(8098);
	       String json= new Gson().toJson(mis_Descuentos);
	      get("/consulta", (req,res) -> json);
		 System.out.println("Goodbye!");
		 
	} // cierra metodo principal (main)

} // cierra clase
		 
		 


