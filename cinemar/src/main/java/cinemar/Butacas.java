package cinemar;

public class Butacas {

	private String codigo;
	private String fila;
	private int numero;
	private Sala sala;
	private String reservada;
	private String id_sala;
	
	
	
	
	

	public String getReservada() {
		return reservada;
	}

	public void setReservada(String reservada) {
		this.reservada = reservada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Butacas()
	{
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
	
	public Butacas(String codigo,String f, int num,Sala sala,String reservada)
	{
		setFila(f); setNumero(num); setSala(sala); setCodigo(codigo); setReservada(reservada);
	}
	
	public Butacas(String codigo, String reservada)
	{
		setCodigo(codigo); setReservada(reservada);
	}
	public Butacas(String codigo,String f, int num,String reservada,String id_sala)
	{
		setFila(f); setNumero(num);  setCodigo(codigo); setReservada(reservada);setId_sala(id_sala);
	}

	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}
}
