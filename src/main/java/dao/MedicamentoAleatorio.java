package dao;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {

	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	public final static String RUTA = "Medicamentos.dat";
	public static RandomAccessFile asdf;

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
			for (int i = 0; i < out.length(); i += TAM_REGISTRO) {
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j] = letra;
					} else {
						nom[j] = ' ';
					}

				}
				String name = new String(nom).replaceAll(" ", "");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (name.equals(nombre)) {
					out.close();
					return med;
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Medicamento buscarCode(int codigo) {
		Medicamento med = new Medicamento();
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i = 0; i < out.length(); i += TAM_REGISTRO) {
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j] = letra;
					} else {
						nom[j] = ' ';
					}

				}
				String name = new String(nom).replaceAll(" ", "");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (codigo == med.getCod()) {
					out.close();
					return med;
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		Medicamento med;
		List<Medicamento> list = new ArrayList<>();
		boolean agtum = false;
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i = 0; i < out.length(); i += TAM_REGISTRO) {
				med = new Medicamento();
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j] = letra;
					} else {
						nom[j] = ' ';
					}

				}
				String name = new String(nom).replaceAll(" ", "");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (med.getCod() == medicamento.getCod()) {
					list.add(medicamento);
					agtum = true;
				} else {
					list.add(med);
				}

			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (agtum) {
			try {
				PrintWriter pw = new PrintWriter(RUTA);
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (Medicamento m : list) {
				guardar(m);
			}
		}
		return agtum;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		Medicamento med;
		List<Medicamento> list = new ArrayList<>();
		boolean borrado = false;
		try {
			RandomAccessFile out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			for (int i = 0; i < out.length(); i += TAM_REGISTRO) {
				med = new Medicamento();
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j] = letra;
					} else {
						nom[j] = ' ';
					}

				}
				String name = new String(nom).replaceAll(" ", "");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				if (medicamento.equals(med)) {
					borrado = true;
				} else {
					list.add(med);
				}

			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (borrado) {
			try {
				PrintWriter pw = new PrintWriter(RUTA);
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (Medicamento m : list) {
				guardar(m);
			}
		}
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
			for (int i = 0; i < out.length(); i += TAM_REGISTRO) {
				med = new Medicamento();
				out.seek(i);
				med.setCod(out.readInt());
				for (int j = 0; j < TAM_NOMBRE; j++) {
					letra = out.readChar();
					if (letra != 0) {
						nom[j] = letra;
					} else {
						nom[j] = ' ';
					}

				}
				String name = new String(nom).replaceAll(" ", "");
				med.setNombre(name);
				med.setPrecio(out.readDouble());
				med.setStock(out.readInt());
				med.setStockMaximo(out.readInt());
				med.setStockMinimo(out.readInt());
				med.setCodProveedor(out.readInt());
				list.add(med);
			}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}
