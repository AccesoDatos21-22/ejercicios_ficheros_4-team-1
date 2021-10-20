package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@XmlRootElement(name="JCCPokemon")
@XmlType(propOrder = { "pokemones", "fechaLanzamiento", "numCartas" })
public class JCCPokemon implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Pokemon> pokemones;
	private Date fechaLanzamiento;
	private int numCartas;

	public JCCPokemon() {
	}

	public JCCPokemon(Date fechaLanzamiento, int numCartas) {
		pokemones = new ArrayList<>();
		this.fechaLanzamiento = fechaLanzamiento;
		this.numCartas = numCartas;
	}

	@XmlElement
	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	@XmlElement
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getNumCartas() {
		return numCartas;
	}

	@XmlElement
	public void setNumCartas(int numCartas) {
		this.numCartas = numCartas;
	}
}
