package Final.code;


public class Pelis {

private String titulo;
private int duraci�n;
private String genero;
private String idioma;
private Clasificaci�n clas;



public Clasificaci�n getClas() {
	return clas;
}
public void setClas(Clasificaci�n clas) {
	this.clas = clas;
}

public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public int getDuraci�n() {
	return duraci�n;
}
public void setDuraci�n(int duraci�n) {
	this.duraci�n = duraci�n;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}
public String getIdioma() {
	return idioma;
}
public void setIdioma(String idioma) {
	this.idioma = idioma;
}
public Pelis()
{
	
}
public Pelis(String titulo, String genero, String idioma, int time,Clasificaci�n clas)
{
	setTitulo(titulo); setGenero(genero); setIdioma(idioma); setDuraci�n(time);
	setClas (clas);
}

public Pelis(String tittle, int time)
{
	setTitulo(tittle); setDuraci�n(time);
}

}

