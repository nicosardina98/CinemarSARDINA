package Final.code;

public class Butacas {

	private String codigo;
	private String fila;
	private int numero;
	private Sala sala;
	private boolean reservada;
	
	
	
	
	
	public boolean isReservada() {
		return reservada;
	}

	public void setReservada(boolean reservada) {
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
	
	public Butacas(String codigo,String f, int num,Sala sala,boolean reservada)
	{
		setFila(f); setNumero(num); setSala(sala); setCodigo(codigo); setReservada(reservada);
	}
	
	public Butacas(String codigo, boolean reservada)
	{
		setCodigo(codigo); setReservada(reservada);
	}
	
}
