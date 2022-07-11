package Final.code;

import java.sql.Time;

public class Sesion {
	private Time hora;
	private double precio;
	private String dia;
	private Sala sala;
	private Pelis pelicula;
	private Descuento descuento;


	
	
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public Time getHora() {
		return hora;
	}


	public void setHora(Time hora) {
		this.hora = hora;
	}


	public Pelis getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelis pelicula) {
		this.pelicula = pelicula;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Sesion()
	{
	}
	
	public Sesion(Time hr,double prec, String day, Sala sala, Pelis peli, Descuento desc)
	{
		setPelicula(peli); setPrecio(precio); setHora(hr); setSala(sala); setDescuento(desc);
	}

	
}
