package com.rentalfast.app.domain.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderManual {


    private CsvReaderManual() {}

    public static List<List<String>> leerCsv(String rutaArchivo) throws IOException {
        List<List<String>> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                filas.add(parsearLinea(linea));
            }
        }

        return filas;
    }

    private static List<String> parsearLinea(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder campoActual = new StringBuilder();
        boolean dentroDeComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (c == '"') {

                if (dentroDeComillas
                        && i + 1 < linea.length()
                        && linea.charAt(i + 1) == '"') {
                    campoActual.append('"');
                    i++;
                } else {
                    dentroDeComillas = !dentroDeComillas;
                }
            } else if (c == ',' && !dentroDeComillas) {
                campos.add(campoActual.toString());
                campoActual.setLength(0);
            } else {
                campoActual.append(c);
            }
        }
        campos.add(campoActual.toString());

        return campos;
    }

}


/*src/main/resources/data/cars_us_2022.csv*/
