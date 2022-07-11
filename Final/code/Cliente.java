package Final.code;

import java.time.LocalDate;

public class Cliente extends Usuario{
	
	private String Id_Cliente;
	private String Contraseña;
	private LocalDate Fecha_registro;

	
	
	public String getId_Cliente() {
		return Id_Cliente;
	}
	public void setId_Cliente(String id_Cliente) {
		Id_Cliente = id_Cliente;
	}
	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}


	public LocalDate getFecha_registro() {
		return Fecha_registro;
	}
	public void setFecha_registro(LocalDate fecha_registro) {
		Fecha_registro = fecha_registro;
	}
	public Cliente (String nombre, String apellido, LocalDate Fecha, String Id, String Pass,LocalDate Fecha_r)
	{
		super(nombre,apellido,Fecha);
		setContraseña(Pass);
		setFecha_registro(Fecha_r);
		setId_Cliente(Id);	
		
	}
	public Cliente (String nombre, String apellido, LocalDate Fecha, String celu, String mail,String Id, String Pass, LocalDate Fecha_r)
	{
		super(nombre,apellido,Fecha,celu,mail);
		setContraseña(Pass);
		setFecha_registro(Fecha_r);
		setId_Cliente(Id);	
	}
	
	public Cliente(String id, String Pass, LocalDate fecha_r)
	{
		super();
		setId_Cliente(id); setContraseña(Pass); setFecha_registro(fecha_r);
	}
	
	public Cliente()
	{
	}
	

	
}
