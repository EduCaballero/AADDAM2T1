/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DAM
 */
public class Generador {
 private String hora;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
 private String dia;

  
 
    public static void main(String[] args) throws IOException  {
       String[] dias =new String[7];
       String[] horas =new String[24];
      String horapet="";
      String diapet="";
      String idiom = "CAT";
      //carga array de dias en funcion del idioma
      diascalendar(idiom,dias);
      //carga array de horas
      horasCalendario(horas);
      //genera documento html
      generarHtml(dias,horas);   
     
    }

    public static void diascalendar(String idiom,String[] dias){
        idiom = idiom.toUpperCase();
        if(idiom.equals("ESP")){
         
          dias[0]="Lunes";dias[1]="Martes";dias[2]="Miercoles";dias[3]="Jueves";dias[4]="Viernes";dias[5]="Sabado";dias[6]="domingo";
          
        }
        if(idiom.equals("ENG")){
          
          dias[0]="Monday";dias[1]="Tuesday";dias[2]="Wednsday";dias[3]="Thursday";dias[4]="Friday";dias[5]="Saturday";dias[6]="Sunday"; 
           
          
        }
         if(idiom.equals("CAT")){
         
          dias[0]="Dilluns";dias[1]="Dimarts";dias[2]="Dimecres";dias[3]="dijous";dias[4]="Divendres";dias[5]="Dissabte";dias[6]="Diumenge";
          
        }
    }
      public static void horasCalendario(String[] horas){
          int x = 0;
          for(int i = 0; i<24;i++){
              if(x==0){
         horas[i] = "0"+i+":00 - " + i + 1 + ":00";
         x++;
              }else{
                horas[i] = i+ ":00 - " + (i + 1) + ":00";  
              }
         
          }
        
    }
    public static void generarHtml(String[] dias,String[] horas) throws IOException {
          
          String ruta = "/Applications/XAMPP/xamppfiles/htdocs/ProjectesCat/ADD/src/add/megenero.html";
          File archivo = new File (ruta);
          BufferedWriter bw;
          bw = new BufferedWriter(new FileWriter(archivo));
         
        
        bw.write("<HTML> \n" +
"<HEAD> \n "+
"<link href=\"myStyles.css\" rel=\"stylesheet\" type=\"text/css\"/>" +                
"\n" +
"</HEAD> \n" +
"<BODY> \n" +
"\n" +
"<H1>Nombre sala</H1> \n" +
"\n" +
"<TABLE BORDER=\"1\"> \n" +
"<TR> "+
       "<TH>xxxxxxxxx</TH>" );
        for(int i = 0;i<dias.length;i++){
            Generador g = new Generador();
            g.setDia(dias[i]);
            bw.write( "<TH class='mydays'>"+g.getDia()+"</TH> \n");
        }
        bw.write("</TR>");
        for(int i = 0;i<horas.length;i++){
            bw.write( " <TR>  <TD class='myhours'>"+horas[i]+"</TD> \n");
         for(int x = 0;x<dias.length;x++){
             if(x<5){
                 if(i<=8){
             bw.write( "<TD class='closed'>close</TD> ");     
                 }else{
            bw.write( "<TD class='open'>open</TD> \n" );
                 }
             }else{
              bw.write( "<TD class='closed'>close</TD> \n");   
             }
        }
         bw.write( "</TR> \n");
        }
         bw.write("  </TABLE> \n" +
"\n" +
"</BODY> \n" +
"</HTML> ");
     bw.close();   
    }
    
    public static void gestionPeticion(String horaspet,String diaspet,String dias[]) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("megenero.html");
        BufferedReader bf = new BufferedReader(fr);
        String sCadena;
        int myday=0;
        while ((sCadena = bf.readLine())!=null) {
        if(sCadena .equals(horaspet)){
            //recoger hora de la peticion para saber que fila es
            for(int i =0; i<dias.length;i++){
                if(dias[i].equals(diaspet)){
                    //recoger numero del dia
                    myday=i;
                }
                
            }
        }
       }
    }
}