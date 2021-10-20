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
		medicamentos.add(medicamento);
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		medicamentos.remove(medicamento);
		return false;
	}

	@Override
	public List<Medicamento> leerTodos() {
		return medicamentos;
	}

	@Override
	public Medicamento buscar(int codigo) {
		for(Medicamento med:medicamentos) {
			if (med.getCod() == codigo){
				return med;
			}
		}
		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		for (int i = 0; i < medicamentos.size(); i++) {
			if (medicamentos.get(i).getCod() == medicamento.getCod()){
				medicamentos.set(i, medicamento);
				return true;
			}
		}
		return false;
	}
}
