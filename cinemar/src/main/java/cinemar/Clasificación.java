package cinemar;

public class Clasificaci�n {
	
	private String Descripcion;
	private String Tipo;
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	public Clasificaci�n()
	{
		
	}
	public Clasificaci�n(String desc, String Tipo)
	{
		setDescripcion(desc); setTipo(Tipo);
	}
	

}
