package Final.code;

import java.time.LocalDate;

public class Administrador extends Usuario{
private String id_administrador;
private String contraseña;
private String antiguedad;
	

public String getId_administrador() {
	return id_administrador;
}


public void setId_administrador(String id_administrador) {
	this.id_administrador = id_administrador;
}


public String getContraseña() {
	return contraseña;
}


public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}



public String getAntiguedad() {
	return antiguedad;
}


public void setAntiguedad(String antiguedad) {
	this.antiguedad = antiguedad;
}


public Administrador (String nombre, String apellido, LocalDate Fecha,String id,String puesto, String antiguedad)
{
	super(nombre, apellido, Fecha); setAntiguedad(antiguedad); setId_administrador(id);
}

public Administrador(String nombre, String apellido, LocalDate Fecha_nac, String celu, String mail, String id, String puesto, String antiguedad)
{
	super(nombre,apellido, Fecha_nac, celu, mail); setAntiguedad(antiguedad); setId_administrador(id);
}

}