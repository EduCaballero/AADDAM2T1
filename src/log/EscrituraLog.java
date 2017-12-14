package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscrituraLog {
	
	public void escribir(String lineaEscribir){
	    String frase=lineaEscribir; //le decimos aquí lo que vamos a escribir
	    try {
	        FileWriter escritura=new FileWriter("incidencies.log", true); //con true hacemos que si el fichero ya existe, le añada la información
																				//si false, sobrescribiría el fichero entero en lugar de añadir
	        BufferedWriter miBufer=new BufferedWriter(escritura);
	        try{
	        	miBufer.write(frase);
	        } catch (IOException ex) {
	        	System.out.println("No se ha podido escribir en el fichero auxiliar "+ex);//Mostramos el mensaje de error si no hemos podido escribir
	        	File f1=new File("incidencies.log"); //guardamos en la variable f1 el fichero incidencies.log para...
	        	f1.delete();//...borrarlo aquí si ha dado error 
	        }
	        miBufer.newLine(); //salto de línea
	        miBufer.flush(); //cerramos con flush y close
	        miBufer.close();
	        //System.out.println("Fichero incidencies.log escrito");
	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        System.out.println("ERROR: No se creó el archivo");
	    }
	}

}
