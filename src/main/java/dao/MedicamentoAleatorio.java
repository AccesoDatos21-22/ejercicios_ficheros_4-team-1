package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.List;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	
	@Override
	public boolean guardar(Medicamento medicamento) {
		try {
			RandomAccessFile out = new RandomAccessFile("Medicamentos.dat", "rw");
			int pos = (medicamento.getCod()-1*TAM_REGISTRO);
			out.writeInt(medicamento.getCod());
			StringBuilder sb = new StringBuilder(medicamento.getNombre());
			sb.setLength(TAM_NOMBRE);
			out.writeChars(sb.toString());

			out.writeDouble(medicamento.getPrecio());
			out.writeInt(medicamento.getStock());
			out.writeInt(medicamento.getStockMaximo());
			out.writeInt(medicamento.getStockMinimo());
			out.writeInt(medicamento.getCodProveedor());

			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Medicamento buscar(String nombre) {
		
		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		
		return false;
	}



	@Override
	public List<Medicamento> leerTodos() {
		return null;
	}

}
