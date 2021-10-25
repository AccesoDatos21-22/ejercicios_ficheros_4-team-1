package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Farmacia;
import modelo.Medicamento;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FarmaciaDOM{

	private static final String DOM_XML_FILE = "xml/FarmaciaDOM.xml";

	/**
	 * Lee los medicamentos de la farmacia de un fichero xml
	 * mediante DOM
	 * @param farmaciaXML
	 * @return
	 */
	public boolean leer(Path farmaciaXML) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(DOM_XML_FILE));
			document.getDocumentElement().normalize();

			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList medicamento = document.getElementsByTagName("Medicamento");

			for (int i = 0; i < medicamento.getLength(); i++) {
				Node emple = medicamento.item(i); // obtener un nodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) emple; // tipo de nodo
					System.out.println("ID: " + getNodo("id", elemento));
					System.out.println("Nombre: " + getNodo("nombre", elemento));
					System.out.println("Precio: " + getNodo("precio", elemento));
					System.out.println("stock: " + getNodo("stock", elemento));
					System.out.println("stockMaximo: " + getNodo("stockMaximo", elemento));
					System.out.println("stockMinimo: " + getNodo("stockMinimo", elemento));
				}
			}
			return true;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
		
	}

	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	/**
	 * Guarda los medicamentos de la farmacia en un fichero XML 
	 * mediamente DOM
	 * @param farmacia
	 * @return
	 */
	public boolean guardar(Farmacia farmacia) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Farmacia", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

			List<Medicamento> meds = farmacia.leerTodos();

			for (int i = 0; i < meds.size(); i++) {
				Medicamento med = meds.get(i);
				Element raiz = document.createElement("Medicamento");
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("id", String.valueOf(med.getCod()), raiz, document);
				CrearElemento("nombre", med.getNombre(), raiz, document);
				CrearElemento("precio", String.valueOf(med.getPrecio()), raiz, document);
				CrearElemento("stock", String.valueOf(med.getStock()), raiz, document);
				CrearElemento("stockMaximo", String.valueOf(med.getStockMaximo()), raiz, document);
				CrearElemento("stockMinimo", String.valueOf(med.getStockMinimo()), raiz, document);
			}

			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(document);
			// Creamos el resultado en el fichero Empleados.xml
			Result result = new StreamResult(new java.io.File(DOM_XML_FILE));
			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
			// Mostramos el documento por pantalla especificando el canal de salida el
			// System.out
			Result console = new StreamResult(System.out);

//			transformer.transform(source, console);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	static void CrearElemento(String dato, String valor, Element raiz, Document document) {
		Element elem = document.createElement(dato);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

}
