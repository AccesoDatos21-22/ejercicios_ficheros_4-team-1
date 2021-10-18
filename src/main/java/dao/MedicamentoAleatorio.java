package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
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
			int pos = ((medicamento.getCod() - 1) * TAM_REGISTRO);
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
		} catch (NullPointerException e) {

		}
		return false;
	}

	@Override
	public Medicamento buscar(String nombre) {
		Medicamento med = new Medicamento();
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i=0; i< out.length();i+=TAM_REGISTRO) {
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j]=letra;
					}else{
						nom[j]=' ';
					}

				}
				String name = new String(nom).replaceAll(" ","");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (name.equals(nombre)) {
					return  med;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		Medicamento med = new Medicamento();
		List<Medicamento> list = new ArrayList<>();
		boolean borrado = false;
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i=0; i< out.length();i+=TAM_REGISTRO) {
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j]=letra;
					}else{
						nom[j]=' ';
					}

				}
				String name = new String(nom).replaceAll(" ","");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (medicamento.equals(med)) {
					long pos = out.getFilePointer();
					/*out.seek(i);*/
					Medicamento eliminado = new Medicamento();
					eliminado.setCod(med.getCod());
					eliminado.setCodProveedor(0);
					eliminado.setStockMinimo(0);
					eliminado.setStockMaximo(0);
					eliminado.setPrecio(0);
					eliminado.setNombre("");
					guardar(eliminado);
					/*for (int k = 0; k < TAM_REGISTRO; k++) {
						out.write(0);
					}*/
					//out.seek(pos);
					borrado = true;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*if (borrado) {
			for (Medicamento m : list) {
				guardar(m);
			}
		}*/

		return borrado;
	}



	@Override
	public List<Medicamento> leerTodos() {
		List<Medicamento> list = new ArrayList<>();

		Medicamento med;
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i=0; i< out.length();i+=TAM_REGISTRO) {
				med = new Medicamento();
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j]=letra;
					}else{
						nom[j]=' ';
					}

				}
				String name = new String(nom).replaceAll(" ","");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				list.add(med);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}
