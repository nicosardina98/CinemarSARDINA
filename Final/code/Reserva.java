package Final.code;

public class Reserva {

private Cliente cliente;
private String Id_Reserva;
private Butacas butaca;



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

public Reserva( String Id_reserva, Cliente cliente,Butacas Butaca)
{
	setButaca(Butaca); setCliente(cliente); setId_Reserva(Id_reserva);
}


}
