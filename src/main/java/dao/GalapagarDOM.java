package dao;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public class GalapagarDOM {

    public boolean leer(URL url) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            System.out.println("tiem");
            builder = factory.newDocumentBuilder();

            Document document = builder.parse(url.openStream());
            document.getDocumentElement().normalize();

            // Obtenemos la lista de nodos con nombre empleado de todo el documento
            NodeList tiempo = document.getElementsByTagName("time");
            for (int i = 0; i < tiempo.getLength(); i++) {
                Node time = tiempo.item(i);
                for (int j = 0; j < time.getAttributes().getLength();j++ ) {
                    System.out.println(time.getAttributes().item(j));
                }
                for (int j = 0; j < time.getChildNodes().getLength();j++ ) {
                    Node jota = time.getChildNodes().item(j);
                    System.out.println(jota.getNodeName()+": ");
                    for (int k = 0; k < jota.getAttributes().getLength(); k++) {
                        System.out.print(jota.getAttributes().item(k)+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            return true;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } // TODO Auto-generated catch block

        return false;

    }

    private static Node getNodo(String etiqueta, Element elem) {
        Node nodo = elem.getElementsByTagName(etiqueta).item(0);
        return nodo;
    }

    private static NamedNodeMap getAtt(Node nodo) {
        NamedNodeMap att = nodo.getAttributes();
        return att;
    }

}
