package cinemar;

import java.math.BigDecimal;
import java.sql.Time;

public class Sesion_alt {
	private int id;
	private Time hora;
	private BigDecimal precio;
	private String dia;
	private String pelicula;
	private Float descuento;
	private int duracion;
	private String id_sala;
	private String tipo;
	
	
	
	public String getId_sala() {
		return id_sala;
	}
	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getPelicula() {
		return pelicula;
	}
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	public Float getDescuento() {
		return descuento;
	}
	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}
	
	public Sesion_alt()
	{
	}
	
	public Sesion_alt(int id, String dia, Time hr, String peli, int duracion,BigDecimal precio, Float desc,String id_sala, String tipo)
	{
		setId(id); setDia(dia); setHora(hr); setPelicula(peli); setDuracion(duracion);
		setPrecio(precio); setDescuento(desc); setId_sala(id_sala); setTipo(tipo);
	}
}
