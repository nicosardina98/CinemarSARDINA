package Final.code;

public class Descuento {
private String dia;
private double desc;

public String getDia() {
	return dia;
}
public void setDia(String dia) {
	this.dia = dia;
}
public double getDesc() {
	return desc;
}
public void setDesc(double desc) {
	this.desc = desc;
}

public Descuento()
{
}

public Descuento(String day, double descuento)
{
	setDia(day);setDesc(descuento);
}
}
