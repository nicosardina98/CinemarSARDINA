package Final.code;

import java.time.LocalDate;

public class Usuario {
	private String nombre;
	private String apellido;
	private LocalDate Fecha_nacimiento;
	private String Numero_celular;
	private String email;
	private boolean rol;
	
	
	public boolean isRol() {
		return rol;
	}
	public void setRol(boolean rol) {
		this.rol = rol;
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
	public LocalDate getFecha_nacimiento() {
		return Fecha_nacimiento;
	}
	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		Fecha_nacimiento = fecha_nacimiento;
	}
	
	public Usuario(String nombre, String apellido, LocalDate Fecha_nac, String celu, String mail, boolean rol)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac); setNumero_celular(celu);
		setEmail(mail); setRol(rol);
	}
	
	public Usuario(String nombre, String apellido, LocalDate Fecha_nac, String celu, String mail)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac); setNumero_celular(celu);
		setEmail(mail);
	}
	
	public Usuario(String nombre, String apellido, LocalDate Fecha_nac)
	{
		setNombre(nombre); setApellido(apellido); setFecha_nacimiento(Fecha_nac);
	}
	public Usuario()
	{
	}
}
