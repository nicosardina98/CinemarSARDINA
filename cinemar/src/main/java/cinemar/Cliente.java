package cinemar;


import java.sql.Date;


public class Cliente extends Usuario{
	
	private String Id_Usuario;
	private String Contraseña;
	private Date Fecha_registro;

	
	public String getId_Usuario() {
		return Id_Usuario;
	}
	public void setId_Usuario(String id_Usuario) {
		Id_Usuario = id_Usuario;
	}
	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}


	public Date getFecha_registro() {
		return Fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		Fecha_registro = fecha_registro;
	}
	
	public Cliente(String nombre, String apellido, Date Fecha_nac, String celu, String mail,String rol)
	{
		super(nombre,apellido,Fecha_nac,celu,mail,rol);
	}
	
	public Cliente (String nombre, String apellido, Date Fecha, String Id, String Pass,Date Fecha_r)
	{
		super(nombre,apellido,Fecha);
		setContraseña(Pass);
		setFecha_registro(Fecha_r);
		setId_Usuario(Id);	
		
	}
	public Cliente (String nombre, String apellido, Date Fecha, String celu, String mail,String rol,String Id, String Pass, Date Fecha_r)
	{
		super(nombre,apellido,Fecha,celu,mail,rol);
		setContraseña(Pass);
		setFecha_registro(Fecha_r);
		setId_Usuario(Id);	
	}
	
	public Cliente(String id, String Pass, Date fecha_r,int num)
	{
		super();
		setId_Usuario(id); setContraseña(Pass); setFecha_registro(fecha_r);
	}
	
	public Cliente()
	{
	}
	

}
