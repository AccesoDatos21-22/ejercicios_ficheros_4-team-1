package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement(name="Pokemon")
public class Pokemon implements Serializable {


	private static final long serialVersionUID = 1L;
	private String nombre;
	private int ataque;
	private int vida;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int nivel;
	private int velocidad;

	public Pokemon() {

	}

	public Pokemon(String nombre, int ataque, int vida, int defensa, int ataqueEspecial, int defensaEspecial, int nivel, int velocidad) {
		this.nombre = nombre;
		this.ataque = ataque;
		this.vida = vida;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.nivel = nivel;
		this.velocidad = velocidad;
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

	public int getVida() {
		return vida;
	}

	@XmlElement
	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDefensa() {
		return defensa;
	}

	@XmlElement
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	@XmlElement
	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	@XmlElement
	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public int getNivel() {
		return nivel;
	}

	@XmlElement
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVelocidad() {
		return velocidad;
	}

	@XmlElement
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
}
