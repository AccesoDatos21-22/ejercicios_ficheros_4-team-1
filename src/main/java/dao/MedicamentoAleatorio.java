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

	@Override
	public boolean guardar(Medicamento medicamento) {
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(RUTA, "rw");
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


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/*@Override
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
*/
	@Override
	public Medicamento buscar(int codigo) {
		Medicamento med = new Medicamento();
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(RUTA, "rw");
			char[] nom = new char[TAM_NOMBRE];
			char letra;
			if ((codigo - 1) * TAM_REGISTRO > out.length()) {
				return null;
			} else {
				out.seek((codigo - 1) * TAM_REGISTRO);
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
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return med;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		Medicamento med;
		List<Medicamento> list = new ArrayList<>();
		boolean agtum = false;
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(RUTA, "rw");
			if ((medicamento.getCod()-1)*TAM_REGISTRO < out.length()) {
				agtum=true;
				out.seek((medicamento.getCod()-1)*TAM_REGISTRO);
				out.writeInt(medicamento.getCod());
				StringBuilder sb = new StringBuilder(medicamento.getNombre());
				sb.setLength(TAM_NOMBRE);
				out.writeChars(sb.toString());
				out.writeDouble(medicamento.getPrecio());
				out.writeInt(medicamento.getStock());
				out.writeInt(medicamento.getStockMaximo());
				out.writeInt(medicamento.getStockMinimo());
				out.writeInt(medicamento.getCodProveedor());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return agtum;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		boolean borrado = false;
		RandomAccessFile out = null;
		try {
				out = new RandomAccessFile(RUTA, "rw");
				int pos = (medicamento.getCod() - 1) * TAM_REGISTRO;
			if (pos < out.length()) {
				out.seek(pos);
				for (int i = pos; i < pos+TAM_REGISTRO; i++) {

					out.write(0);
				}
				borrado = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return borrado;
	}


	@Override
	public List<Medicamento> leerTodos() {
		List<Medicamento> list = new ArrayList<>();

		Medicamento med;
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(RUTA, "rw");
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
