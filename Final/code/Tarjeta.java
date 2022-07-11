package Final.code;

import java.time.LocalDate;

public class Tarjeta {
	private int num;
	private LocalDate fecha_vencimiento;
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public LocalDate getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public Tarjeta(Cliente cliente, int num, LocalDate fecha_v)
	{
		setCliente(cliente); setNum(num); setFecha_vencimiento(fecha_v);
	}
	
	public Tarjeta()
	{
	}
	

}
