package cinemar;

import java.sql.Time;

public class Reserva_alt {

	private String id_reserva;
	private int num_reserva;
	private String id_cliente;
	private String id_butaca;
	private String fila;
	private int numero;
	private String id_sala;
	private int num_sala;
	private String tipo;
	private int id_sesion;
	private String dia;
	private Time hora; 
	private String peli;
	
	public Reserva_alt(int id_sesion, String dia, Time hora, String pelicula, String tipo,int num_sala,String id_sala, String id_butaca, String fila, int numero,String id_reserva ,int num_reserva)
	{
		setId_sesion(id_sesion); setDia(dia); setHora(hora); setPeli(pelicula); setTipo(tipo);
		setNum_sala(num_sala); setId_sala(id_sala); setId_butaca(id_butaca); setFila(fila);
		setNumero(numero); setId_reserva(id_reserva); setNum_reserva(num_reserva);
	}
	
	public String getFila() {
		return fila;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getId_sala() {
		return id_sala;
	}
	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}
	public int getNum_sala() {
		return num_sala;
	}
	public void setNum_sala(int num_sala) {
		this.num_sala = num_sala;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId_sesion() {
		return id_sesion;
	}
	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getPeli() {
		return peli;
	}
	public void setPeli(String peli) {
		this.peli = peli;
	}
	public String getId_reserva()
	{
		return id_reserva;
	}
	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}
	public int getNum_reserva() {
		return num_reserva;
	}
	public void setNum_reserva(int num_reserva) {
		this.num_reserva = num_reserva;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getId_butaca() {
		return id_butaca;
	}
	public void setId_butaca(String id_butaca) {
		this.id_butaca = id_butaca;
	}
	
	public Reserva_alt()
	{
		
	}
	
	public Reserva_alt(int num, String id, String id_cliente, String id_butaca)
	{
		setNum_reserva(num); setId_reserva(id); setId_cliente(id_cliente);
		setId_butaca(id_butaca);
	}
	
	
}
