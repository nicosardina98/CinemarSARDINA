package cinemar;


import java.sql.Date;


public class Usuario {
	private String nombre;
	private String apellido;
	private Date Fecha_nacimiento;
	private String Numero_celular;
	private String email;
	private rol rol_usuario;
	
	public enum rol{Cliente,Administrador};
	
	
	
	
	public rol getRol_usuario() {
		return rol_usuario;
	}
	public void setRol_usuario(rol rol_usuario) {
		this.rol_usuario = rol_usuario;
	}
	public String getNumero_celular() {
		return Numero_celular;
	}
	public void setNumero_celular(String numero_celular) {
		Numero_celular = numero_celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFecha_nacimiento() {
		return Fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		Fecha_nacimiento = fecha_nacimiento;
	}
	
	public Usuario(String nombre, String apellido, Date Fecha_nac, String celu, String mail,String rs)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac); setNumero_celular(celu);
		setEmail(mail);
		setRol_usuario(rol.valueOf(rs));
	}
	
	public Usuario(String nombre, String apellido, Date Fecha_nac, String celu, String mail)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac); setNumero_celular(celu);
		setEmail(mail);
	}
	
	public Usuario(String nombre, String apellido, Date Fecha_nac)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac);
	}
	public Usuario()
	{
	}
	
	

}
