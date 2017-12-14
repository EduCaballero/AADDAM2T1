/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author DAM
 */
public class Generador {

    public static void main(String[] args) {

    }

    public void generarHtml() throws IOException {
        FileWriter archivo = new FileWriter("agenda.html");
        PrintWriter imprimir = new PrintWriter(archivo);
        
    }
}
