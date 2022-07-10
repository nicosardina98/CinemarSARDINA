package cinemar;

import java.sql.Date;

public class Administrador extends Usuario{
private String id_administrador;
private String contrase�a;
private int antiguedad;
	

public String getId_administrador() {
	return id_administrador;
}


public void setId_administrador(String id_administrador) {
	this.id_administrador = id_administrador;
}


public String getContrase�a() {
	return contrase�a;
}


public void setContrase�a(String contrase�a) {
	this.contrase�a = contrase�a;
}



public int getAntiguedad() {
	return antiguedad;
}


public void setAntiguedad(int antiguedad) {
	this.antiguedad = antiguedad;
}


public Administrador (String nombre, String apellido, Date Fecha,String id, int antiguedad)
{
	super(nombre, apellido, Fecha); setAntiguedad(antiguedad); setId_administrador(id);
}

public Administrador(String nombre, String apellido, Date Fecha_nac, String celu, String mail, String id, String puesto, int antiguedad)
{
	super(nombre,apellido, Fecha_nac, celu, mail); setAntiguedad(antiguedad); setId_administrador(id);
}

public Administrador(String nombre, String apellido, Date Fecha_nac, String celu, String mail, String rol, String id, String contrase�a, int antiguedad)
{
	super(nombre,apellido, Fecha_nac, celu, mail,rol); setAntiguedad(antiguedad); setId_administrador(id); setContrase�a(contrase�a);
}
}