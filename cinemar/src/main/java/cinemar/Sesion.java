package cinemar;

import java.sql.Time;

public class Sesion {
	private Time hora;
	private double precio;
	private String dia;
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
	
	public Sesion(Time hr,double prec, String day, Pelis peli, Descuento desc)
	{
		setPelicula(peli); setPrecio(precio); setHora(hr); setDescuento(desc);
	}

	
}
