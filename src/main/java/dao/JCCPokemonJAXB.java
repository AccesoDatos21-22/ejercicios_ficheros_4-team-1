package dao;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.JCCPokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class JCCPokemonJAXB implements JCCPokemonDAO {

	private static final String JAXB_XML_FILE = "xml/PokemonJAXB.xml";

	@Override
	public JCCPokemon leer() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(JCCPokemon.class);
			// Si las clases a serializar están en otro paquete se indica el paquete
			// al crear el marshall
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Provincia provincia = fillProvincia();
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JCCPokemon poki = (JCCPokemon) unmarshaller.unmarshal(Files.newInputStream(Paths.get(JAXB_XML_FILE)));
			System.out.println("********* Empresa cargado desde fichero XML***************");
			// Mostramos por linea de comandos el objeto Java obtenido
			// producto de la deserialziacion
			marshaller.marshal(poki, System.out);
			return poki;
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean guardar(JCCPokemon pokemones) {

		long time = System.currentTimeMillis();
		System.out.println("Inicio: " + new Date(time));
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(JCCPokemon.class);

			// Si las clases a serializar están en otro paquete se indica el paquete
			// al crear el marshall
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Provincia provincia = fillProvincia();
			// Mostramos el documento XML generado por la salida estandar
			marshaller.marshal(pokemones, System.out);
			// guardamos el objeto serializado en un documento XML
			marshaller.marshal(pokemones, Files.newOutputStream(Paths.get(JAXB_XML_FILE)));
			Unmarshaller unmarshaller = context.createUnmarshaller();

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
