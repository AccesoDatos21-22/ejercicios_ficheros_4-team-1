package dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class GalapagarJSON {

    public boolean leer(URL url) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            reader.close();

            JSONObject city = (JSONObject) jsonObject.get("city");
            System.out.println("Ciudad: " + city.get("name") + "\n");

            JSONArray list = (JSONArray) jsonObject.get("list");

            for (Object lista : list) {
                JSONObject dias = (JSONObject) lista;
                Date date = new Date(Long.parseLong(dias.get("dt").toString()) * 1000);
                System.out.println("Dia: " + date);
                JSONObject tiempo = (JSONObject) dias.get("temp");
                System.out.println("Tiempo: ");
                System.out.println("\tDia: " + tiempo.get("day") + "º Minimo: " + tiempo.get("min") + "º Maximo: " + tiempo.get("max") + "º");
                System.out.println("\tNoche: " + tiempo.get("night") + "º Tarde: " + tiempo.get("eve") + "º Mañana: " + tiempo.get("morn") + "º");
                JSONObject sensasion = (JSONObject) dias.get("feels_like");
                System.out.println("Sensacion termica: ");
                System.out.println("\tDia: " + sensasion.get("day") + "º Noche: " + sensasion.get("night") + "º Tarde: " + sensasion.get("eve") + "º Mañana: " + sensasion.get("morn") + "º");
                System.out.println("Presion: " + dias.get("pressure") + " Humedad: " + dias.get("humidity"));
                System.out.println();
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
