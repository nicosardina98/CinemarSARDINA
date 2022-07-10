package cinemar;

public class Reserva {

private Sesion sesion;
private Cliente cliente;
private String Id_Reserva;
private Butacas butaca;




public Sesion getSesion() {
	return sesion;
}

public void setSesion(Sesion sesion) {
	this.sesion = sesion;
}

public Butacas getButaca() {
	return butaca;
}

public void setButaca(Butacas butaca) {
	this.butaca = butaca;
}

public String getId_Reserva() {
	return Id_Reserva;
}

public void setId_Reserva(String id_Reserva) {
	Id_Reserva = id_Reserva;
}


public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public Reserva()
{
}

public Reserva( String Id_reserva, Sesion sesion, Cliente cliente,Butacas Butaca)
{
	setButaca(Butaca); setCliente(cliente); setId_Reserva(Id_reserva);setSesion(sesion);
}


}
