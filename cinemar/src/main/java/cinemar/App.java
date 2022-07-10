package cinemar;

import static spark.Spark.*;
import java.sql.Date;
import org.apache.log4j.PropertyConfigurator;
import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {
		
			Date date1 = Date.valueOf("1998-01-25");
			System.out.println(date1);
			   String log4jConfPath = "C:/Users/nico_/eclipse-workspace/cinemar/to/log4j.properties";
			   PropertyConfigurator.configure(log4jConfPath);
		       Gson mapper= new Gson();
		       port(8000);
		       get("/saludar", (req,res) -> new Usuario("Nico","Sardina",date1, "3886415860", "nico_sardina98@gmail.com","cliente"), mapper::toJson);
		       
		       get("/desc", (req,res)-> new Clasificación("Descripción1", "Acción"),mapper::toJson);
	}

}
