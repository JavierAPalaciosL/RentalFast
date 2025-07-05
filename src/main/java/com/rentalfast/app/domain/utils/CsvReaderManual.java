package com.rentalfast.app.domain.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for manually reading CSV files and converting them
 * into a list of string lists, where each inner list represents a row
 * of fields.
 *
 * @author Javier Palacios
 * @deprecated Use {@link java.nio.file.Files#readAllLines(java.nio.file.Path)} o OpenCSV para un parsing más completo y eficiente.
 */
public class CsvReaderManual {


    private CsvReaderManual() {}

    /**
     * Reads a CSV file from the given file path and returns its contents
     * as a list of rows, where each row is a list of string fields.
     * <p>
     * Lines are parsed using {@link #parsearLinea(String)}, which handles
     * quoted fields and escaped quotes.
     * </p>
     *
     * @param rutaArchivo the file path of the CSV file to read
     * @return a list of rows, each row represented as a list of string fields
     * @throws IOException if an I/O error occurs while reading the file
     * @deprecated desde la versión 2.0.0. Use {@link java.nio.file.Files#readAllLines(java.nio.file.Path)}
     *             u otra solución especializada.
     */
    @Deprecated
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


    /**
     * Parses a single line of a CSV file into a list of string fields.
     * <p>
     * Handles fields enclosed in double quotes, supports escaped quotes
     * by using a pair of consecutive double quotes, and splits on commas
     * only when not inside quoted sections.
     * </p>
     *
     * @param linea the raw CSV line to parse
     * @return a list of string fields extracted from the line
     */
    @Deprecated
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
