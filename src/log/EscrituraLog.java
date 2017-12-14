package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscrituraLog {
	
	public void escribir(String lineaEscribir){
	    String frase=lineaEscribir;
	    try {
	        FileWriter escritura=new FileWriter("incidencies.log", true); //con true hacemos que si el fichero ya existe, le añada la información
																				//si false, sobrescribiría el fichero entero en lugar de añadir
	        BufferedWriter miBufer=new BufferedWriter(escritura);
	        try{
	        	miBufer.write(frase);
	        } catch (IOException ex) {
	        	System.out.println("No se ha podido escribir en el fichero auxiliar "+ex);//lkjbsdljndsglnjl
	        	File f1=new File("incidencies.log");
	        	f1.delete();
	        }
	        miBufer.newLine();
	        miBufer.flush();
	        miBufer.close();
	        //System.out.println("Fichero incidencies.log escrito");
	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        System.out.println("ERROR: No se creó el archivo");
	    }
	}

}
