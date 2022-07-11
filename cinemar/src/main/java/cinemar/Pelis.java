package cinemar;


public class Pelis {

private String titulo;
private int duración;
private String genero;
private String idioma;
private Clasificación clas;
private int id;
private String tipo;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Clasificación getClas() {
	return clas;
}
public void setClas(Clasificación clas) {
	this.clas = clas;
}

public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public int getDuración() {
	return duración;
}
public void setDuración(int duración) {
	this.duración = duración;
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
public Pelis(String titulo, String genero, String idioma, int time,Clasificación clas)
{
	setTitulo(titulo); setGenero(genero); setIdioma(idioma); setDuración(time);
	setClas (clas);
}

public Pelis(String tittle, int time)
{
	setTitulo(tittle); setDuración(time);
}

}

