package org.example.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.example.estructuras.arbol.NodoArbol;

import java.nio.file.Path;
import java.nio.file.Paths;

public class VisualizadorAVL {

    public static void guardarArbol(NodoArbol raiz, String nombreArchivo) {
        try {
            // 1. Generar el contenido DOT
            StringBuilder dot = new StringBuilder();
            dot.append("digraph ArbolAVL {\n");
            dot.append("  node [shape=record, style=filled, fillcolor=lightblue];\n");
            dot.append("  edge [arrowhead=vee];\n\n");
            
            generarNodosConComplejidad(dot, raiz);
            dot.append("}\n");

            // 2. Guardar archivo DOT
            Path dotPath = Paths.get(nombreArchivo + ".dot");
            Files.write(dotPath, dot.toString().getBytes());

            // 3. Generar imagen JPG
            ProcessBuilder pb = new ProcessBuilder(
                "dot", "-Tjpg", 
                dotPath.toString(), 
                "-o", nombreArchivo + ".jpg"
            );
            pb.start().waitFor();

            System.out.println("Árbol generado exitosamente: " + nombreArchivo + ".jpg");
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al generar visualización: " + e.getMessage());
        }
    }



    
    private static void generarNodosConComplejidad(StringBuilder dot, NodoArbol nodo) {
        if (nodo == null) return;
        
        // Obtener datos del nodo
        String id = nodo.getInterseccion().getPosicion(); 
        int complejidad =   nodo.getInterseccion().getComplejidad(); 
        String nombre = nodo.getInterseccion().getTipo() + " " + nodo.getInterseccion().getTipoInterseccion();
        int altura = nodo.getAltura();
        
        // Formatear nodo con ID, complejidad y altura
        dot.append(String.format(
            "  %s [label=\"Id: %s\\nTipo: %s\\nComplej: %s\\nAltura: %d\"];\n", 
            id,id,nombre,complejidad, altura
        ));
        
        // Conexión con hijo izquierdo
        if (nodo.getIzquierda() != null) {
            dot.append(String.format("  %s -> %s [label=\"Izq\"];\n", 
                   id, nodo.getIzquierda().getInterseccion().getPosicion()));
            generarNodosConComplejidad(dot, nodo.getIzquierda());
        } else {
            dot.append(String.format("  null%s [shape=point];\n", id));
            dot.append(String.format("  %s -> null%s;\n", id, id));
        }
        
        // Conexión con hijo derecho
        if (nodo.getDerecha() != null) {
            dot.append(String.format("  %s -> %s [label=\"Der\"];\n", 
                   id, nodo.getDerecha().getInterseccion().getPosicion()));
            generarNodosConComplejidad(dot, nodo.getDerecha());
        } else {
            dot.append(String.format("  null%s [shape=point];\n", id));
            dot.append(String.format("  %s -> null%s;\n", id, id));
        }
    }

}
