package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement(name="Pokemon")
public class Pokemon implements Serializable {


	private static final long serialVersionUID = 1L;
	private String nombre;
	private int ataque;

	public Pokemon() {

	}

	public Pokemon(String nombre, int ataque) {
		this.nombre = nombre;
		this.ataque = ataque;
	}

	public String getNombre() {
		return nombre;
	}

	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAtaque() {
		return ataque;
	}

	@XmlElement
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
}
