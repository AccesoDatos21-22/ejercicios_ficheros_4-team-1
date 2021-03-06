package Main;

import com.thoughtworks.xstream.XStream;
import dao.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.*;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Main {

	private static final String JAXB_XML_FILE = "xml/EmpresaJAXB.xml";
	private static final String XSTREAM_XML_FILE = "xml/EmpresaXTREAM.xml";
	private static final String DOM_XML_FILE = "xml/FarmaciaDOM.xml";

	public static <JsonArray> void main(String[] args) throws IOException, ParseException {


		//Pruebas SubirNota 1
		System.out.println("Ejecutando pruebas SubirNota1");
		GalapagarDOM gal = new GalapagarDOM();
		URL urlDOM = new URL("https://api.openweathermap.org/data/2.5/forecast/daily?q=Galapagar&units=metric&mode=xml&appid=479092b77bcf850403cb2aeb1a302425");
		gal.leer(urlDOM);

		//Creamos MedicamentoAleatorio y guardamos medicamentos para las pruebas
		System.out.println("Creando medicamentos y guardándolos");
		MedicamentoAleatorio med = new MedicamentoAleatorio();
		Medicamento aBorrar = new Medicamento("Aspirina",4,50,8,50,0,69);
		med.guardar(new Medicamento("Paracetamol",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirinalol",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirinaaaaa",50,8,50,0,69));
		med.guardar(new Medicamento("Paracetammmmmmmol",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirina",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirina",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirina",50,8,50,0,69));
		med.guardar(new Medicamento("Aspirina",50,8,50,0,69));
		med.actualizar(new Medicamento("AspirinaGORDA",7,50,8,50,5,69));
		Medicamento busCode = med.buscar(8);
		List<Medicamento> list;

		//Pruebas MedicamentoAleatorio
		System.out.println("Ejecutando pruebas MedicamentoAleatorio");
		try {
			System.out.println("Buscar");
			//System.out.println(buscNom.toString());
			System.out.println(busCode.toString());
			System.out.println();
			System.out.println("Imprimir todos");
			list = med.leerTodos();
			for (Medicamento a: list) {
				System.out.println(a.toString()+"\n");
			}
			System.out.println();
			System.out.println();
			System.out.println("Borrando...."+ med.borrar(aBorrar));
			list = med.leerTodos();
			for (Medicamento a: list) {
				System.out.println(a.toString()+"\n");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		//Creamos farmacia y añadimos medicamentos para las pruebas
		System.out.println("Creando Farmacia y guardando medicamentos");
		Farmacia far = new Farmacia();
		far.guardar(new Medicamento("Aspirina1", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina2", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina3", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina4", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina5", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina6", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina7", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina8", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina9", 50, 50, 50, 50, 50));
		far.guardar(new Medicamento("Aspirina10", 50, 50, 50, 50, 50));
		//Pruebas DOM
		System.out.println("Ejecutando pruebas DOM");
		FarmaciaDOM farDOM = new FarmaciaDOM();
		farDOM.guardar(far);
		farDOM.leer(Paths.get(DOM_XML_FILE));

		//Pruebas XSTREAM
		System.out.println("Ejecutando pruebas XSTREAM");
		FarmaciaXSTREAM xStream = new FarmaciaXSTREAM();
		xStream.guardar(far);
		xStream.leer();
		xStream.guardarMedicamento(new Medicamento("AspirinaGuay", 50, 50, 50, 50, 50));
		xStream.leerMedicamento();

		//Pruebas JAXB
		System.out.println("Ejecutando pruebas JAXB");
		JCCPokemonJAXB xa = new JCCPokemonJAXB();
		JCCPokemon po = new JCCPokemon(new Date(System.currentTimeMillis()),5000);
		List<Pokemon> listPo = new ArrayList<>();
		listPo.add(new Pokemon("Chorizor", 500,50,80,69,45,85,64));
		listPo.add(new Pokemon("Picantu", 6969,89,65,28,78,96,2));
		listPo.add(new Pokemon("Bulbanorte", 8,9,9,9,7,5,2));
		listPo.add(new Pokemon("Magmatote", 656,656,8,9,78,9,7));
		listPo.add(new Pokemon("Lucasio", 696449,456,77,7,78,9,2));
		listPo.add(new Pokemon("Sudomudo", 69656569,45,8,9,9,7,9));
		listPo.add(new Pokemon("HoHoHou", 4,89,2,28,78,7,2));
		po.setPokemones(listPo);
		xa.guardar(po);
		xa.leer();


		//Pruebas SubirNota 2
		System.out.println("Ejecutando pruebas SubirNota 2");
		URL urlJSON = new URL("https://api.openweathermap.org/data/2.5/forecast/daily?q=Galapagar&units=metric&mode=json&appid=479092b77bcf850403cb2aeb1a302425");
		GalapagarJSON gala = new GalapagarJSON();
		gala.leer(urlJSON);

	}

	private static void ejemploEscribirXSTREAM() {

		try {

			System.out.println("Comienza el proceso de creación del fichero a XML...");

			XStream xstream = new XStream();
			
			long time = System.currentTimeMillis();
			System.out.println("Inicio: " + new Date(time));
			Empresa cc = new Empresa();
			cc.setIdEmpresa(1);
			cc.setDireccion("En la nube");
			cc.setNombreEmpresa("IES");
			cc.setNumEmpleados(10);

			ArrayList<Empleado> alCU = new ArrayList<Empleado>();
			int init = 20000;
			for (int i = 1; i < 10; i++) {
				Empleado cu = new Empleado();
				cu.setId(i);
				cu.setActivo(true);
				cu.setNumeroEmpl(init++);
				cu.setNombre("Empleado " + i);
				cu.setTitulo("SW Architect");
				cu.setFechaAlta(new Date(System.currentTimeMillis()));

				alCU.add(cu);
			}

			cc.setEmpleados(alCU);
			
			// cambiar de nombre a las etiquetas XML
			xstream.alias("Empleado", Empleado.class);
			xstream.alias("Empresa", Empresa.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Empresa");
			// Insertar los objetos en XML
			xstream.toXML(cc, new FileOutputStream(XSTREAM_XML_FILE));
			System.out.println("Creado fichero XML....");

		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
	}

	private static void ejemploLeerXSTREAM() {
		Empresa empresa = new Empresa();
        try {
            Class<?>[] classes = new Class[] { Empresa.class, Empleado.class };

            XStream xstream = new XStream();
            //XStream.setupDefaultSecurity(xstream);
            //xstream.allowTypes(classes);
           
            xstream.alias("Empresa", Empresa.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(Empresa.class, "Empresa");

            empresa = (Empresa) xstream
                    .fromXML(new FileInputStream(XSTREAM_XML_FILE));

            for(Empleado e: empresa.getEmpleados()) {
            	System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e);
        }

	}

	private static void ejemploLeerDOM() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(DOM_XML_FILE));
			document.getDocumentElement().normalize();

			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList empleados = document.getElementsByTagName("empleado");

			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) emple; // tipo de nodo
					System.out.println("ID: " + getNodo("id", elemento));
					System.out.println("Apellido: " + getNodo("nombre", elemento));
					System.out.println("Departamento: " + getNodo("dep", elemento));
					System.out.println("Salario: " + getNodo("salario", elemento));
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// obtener información de un nodo
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	private static void ejemploEscribirDOM() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

			for (int i = 1; i < 10; i++) {
				Element raiz = document.createElement("empleado");

				document.getDocumentElement().appendChild(raiz);

				CrearElemento("id", Integer.toString(i), raiz, document);
				CrearElemento("nombre", "Empleado " + i, raiz, document);
				CrearElemento("dep", "01", raiz, document);
				CrearElemento("salario", "1000.0", raiz, document);
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

			transformer.transform(source, console);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

	private static void ejemploJaxb() {
		long time = System.currentTimeMillis();
		System.out.println("Inicio: " + new Date(time));
		Empresa cc = new Empresa();
		cc.setIdEmpresa(1);
		cc.setDireccion("En la nube");
		cc.setNombreEmpresa("IES");
		cc.setNumEmpleados(10);

		ArrayList<Empleado> alCU = new ArrayList<Empleado>();
		int init = 20000;
		for (int i = 1; i < 10; i++) {
			Empleado cu = new Empleado();
			cu.setId(i);
			cu.setActivo(true);
			cu.setNumeroEmpl(init++);
			cu.setNombre("Empleado " + i);
			cu.setTitulo("SW Architect");
			cu.setFechaAlta(new Date(System.currentTimeMillis()));

			alCU.add(cu);
		}

		cc.setEmpleados(alCU);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Empresa.class);

			// Si las clases a serializar están en otro paquete se indica el paquete
			// al crear el marshall
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Provincia provincia = fillProvincia();
			// Mostramos el documento XML generado por la salida estandar
			marshaller.marshal(cc, System.out);
			// guardamos el objeto serializado en un documento XML
			marshaller.marshal(cc, Files.newOutputStream(Paths.get(JAXB_XML_FILE)));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			// Deserealizamos a partir de un documento XML
			Empresa empresa = (Empresa) unmarshaller.unmarshal(Files.newInputStream(Paths.get(JAXB_XML_FILE)));
			System.out.println("********* Empresa cargado desde fichero XML***************");
			// Mostramos por linea de comandos el objeto Java obtenido
			// producto de la deserialziacion
			marshaller.marshal(empresa, System.out);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}