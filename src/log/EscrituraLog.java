package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscrituraLog {
	
	public static void escribir(String lineaEscribir){
	    String frase=lineaEscribir; //le decimos aqu� lo que vamos a escribir
	    try {
	        FileWriter escritura=new FileWriter("incidencies.log", true); //con true hacemos que si el fichero ya existe, le a�ada la informaci�n
																				//si false, sobrescribir�a el fichero entero en lugar de a�adir
	        BufferedWriter miBuffer=new BufferedWriter(escritura);
	        try{
	        	miBuffer.write(frase);
	        } catch (IOException ex) {
	        	System.out.println("No se ha podido escribir en el fichero auxiliar "+ex);//Mostramos el mensaje de error si no hemos podido escribir
	        	File f1=new File("incidencies.log"); //guardamos en la variable f1 el fichero incidencies.log para...
	        	f1.delete();//...borrarlo aqu� si ha dado error 
	        }
	        miBuffer.newLine(); //salto de l�nea
	        miBuffer.flush(); //cerramos con flush y close
	        miBuffer.close();
	        //System.out.println("Fichero incidencies.log escrito");
	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        System.out.println("ERROR: No se cre� el archivo");
	    }
	}
//
}
