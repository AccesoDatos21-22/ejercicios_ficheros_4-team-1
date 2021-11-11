package dao;

import com.thoughtworks.xstream.XStream;
import modelo.*;
import modelo.Farmacia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FarmaciaXSTREAM implements FarmaciaDAO{
	
	final static String rutaFarmcia ="xml/FarmaciaXTREAM.xml"; // localizacion del fichero XML
	final static String rutaMed ="xml/FarmaciaMedXTREAM.xml"; // localizacion del fichero XML
	final static String rutaMedicamento =""; // localizacion del fichero XML

	@Override
	public Farmacia leer() {

		Farmacia farmacia = new Farmacia();
		try {
			Class<?>[] classes = new Class[] { Farmacia.class, Medicamento.class };

			XStream xstream = new XStream();
			//XStream.setupDefaultSecurity(xstream);
			//xstream.allowTypes(classes);
			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class, "Farmacia");

			farmacia = (Farmacia) xstream
					.fromXML(new FileInputStream(rutaFarmcia));

			for(Medicamento e: farmacia.leerTodos()) {
				System.out.println(e+"\n");
			}
			return farmacia;
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e);
		}
		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {
		try {

			System.out.println("Comienza el proceso de creación del fichero a XML...");
			XStream xstream = new XStream();

			long time = System.currentTimeMillis();
			System.out.println("Inicio: " + new Date(time));

			// cambiar de nombre a las etiquetas XML
			xstream.alias("Medicamento", Medicamento.class);
			xstream.alias("Farmacia", Farmacia.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Farmacia");
			// Insertar los objetos en XML
			xstream.toXML(farmacia, new FileOutputStream(rutaFarmcia));
			System.out.println("Creado fichero XML....");
			return true;
		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
		return false;
	}

	public List<Medicamento> leerMedicamento() {
		Farmacia farmacia = new Farmacia();
		try {
			Class<?>[] classes = new Class[] { Farmacia.class, Medicamento.class };

			XStream xstream = new XStream();
			//XStream.setupDefaultSecurity(xstream);
			//xstream.allowTypes(classes);
			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class, "Farmacia");

			farmacia = (Farmacia) xstream
					.fromXML(new FileInputStream(rutaMed));

			for(Medicamento e: farmacia.leerTodos()) {
				System.out.println(e+"\n");

			}
			return farmacia.leerTodos();
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e);
		}
		return null;
	}

	public void guardarMedicamento(Medicamento medicamento) {

		try {

			Farmacia farmacia = new Farmacia();
			farmacia.guardar(medicamento);
			System.out.println("Comienza el proceso de creación del fichero a XML...");
			XStream xstream = new XStream();

			long time = System.currentTimeMillis();
			System.out.println("Inicio: " + new Date(time));

			// cambiar de nombre a las etiquetas XML
			xstream.alias("Medicamento", Medicamento.class);
			xstream.alias("Farmacia", Farmacia.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Farmacia");
			// Insertar los objetos en XML
			xstream.toXML(farmacia, new FileOutputStream(rutaMed));
			System.out.println("Creado fichero XML....");
//			return true;
		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
//		return false;

	}


}
