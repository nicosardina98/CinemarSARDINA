package cinemar;

public class Clasificación {
	
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
	
	public Clasificación()
	{
		
	}
	public Clasificación(String desc, String Tipo)
	{
		setDescripcion(desc); setTipo(Tipo);
	}
	

}
