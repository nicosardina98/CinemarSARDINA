package cinemar;

public class Sala {

private int num;
private tipo tipo_sala;
private int capacidad;
private Administrador admin;

public enum tipo {DD,DDD};

public Administrador getAdmin() {
	return admin;
}

public void setAdmin(Administrador admin) {
	this.admin = admin;
}

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public tipo getTipo_sala() {
	return tipo_sala;
}

public void setTipo_sala(tipo tipo_sala) {
	this.tipo_sala = tipo_sala;
}

public int getCapacidad() {
	return capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

public Sala (int n,String t, int capacidad,Administrador adm)
{
	setNum(n); setTipo_sala(tipo.valueOf(t)); setCapacidad(capacidad);setAdmin(adm);
}

public Sala(int n)
{
	this.num=n;
}


public  Sala()
{	
}

}
