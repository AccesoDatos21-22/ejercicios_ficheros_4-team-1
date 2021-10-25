package modelo;

import java.util.ArrayList;
import java.util.List;

import dao.MedicamentoDAO;

public class Farmacia implements MedicamentoDAO {
	private List<Medicamento> medicamentos;

	/**
	 * Constructor de la farmacia
	 */
	public Farmacia() {
		medicamentos = new ArrayList<>();
	}

	@Override
	public boolean guardar(Medicamento medicamento) {
		return medicamentos.add(medicamento);
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		return medicamentos.remove(medicamento);
	}

	@Override
	public List<Medicamento> leerTodos() {
		return medicamentos;
	}

	@Override
	public Medicamento buscar(String nombre) {
		for (Medicamento med : medicamentos) {
			if (med.getNombre().equals(nombre)) {
				return med;
			}
		}
		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		for (int i=0; i < medicamentos.size(); i++) {
			if (medicamentos.get(i).equals(medicamento)) {
				medicamentos.set(i, medicamento);
				return true;
			}
		}
		return false;
	}
}
