package Final.code;

public class Sala {

private int num;
private boolean tipo;
private int capacidad;
private Sesion sesion;






public Sesion getSesion() {
	return sesion;
}

public void setSesion(Sesion sesion) {
	this.sesion = sesion;
}

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public boolean isTipo() {
	return tipo;
}

public void setTipo(boolean tipo) {
	this.tipo = tipo;
}


public int getCapacidad() {
	return capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

public Sala (int n, boolean tipo, int capacidad)
{
	setNum(n); setTipo(tipo); setCapacidad(capacidad);
}

public Sala(int n)
{
	this.num=n;
}


public  Sala()
{	
}

}
