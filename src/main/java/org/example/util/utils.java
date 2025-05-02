package org.example.util;

public class utils {

    public static String extraerFila(String coordenada) {
        return coordenada.replaceAll("[0-9]", ""); // extraer letar para la coordenada
    }

    public static int extraerColumna(String coordenada) {
        return Integer.parseInt(coordenada.replaceAll("[^0-9]", "")); /// extraer numero para la corrdenada
    }


}
