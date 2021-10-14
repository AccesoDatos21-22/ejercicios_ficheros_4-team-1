package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	public final static String RUTA = "Medicamentos.dat";

	@Override
	public boolean guardar(Medicamento medicamento) {
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			int pos = ((medicamento.getCod()-1)*TAM_REGISTRO);
			out.seek(pos);
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
		List<Medicamento> lista =new ArrayList<>();
		Medicamento med = new Medicamento();
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i=0; i< out.length();i+=TAM_REGISTRO) {
				out.seek(i);
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j]=letra;
					}else{
						nom[j]=' ';
					}

				}
				if (new String(nom).equals(nombre)) {
					med.setNombre(new String(nom));
					med.setPrecio(out.readDouble());
					med.setStock(out.readInt());
					med.setStockMaximo(out.readInt());
					med.setStockMinimo(out.readInt());
					med.setCodProveedor(out.readInt());
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return med;
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
