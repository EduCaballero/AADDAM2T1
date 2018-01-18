/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import static booking.Sala.diasMes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
      String idiom = "ESP";
      diascalendar(idiom,dias);
      horasCalendario(horas);
      generarHtml(dias,horas);   
      System.out.println(dias[0]);
      
      
    }

    public static void diascalendar(String idiom,String[] dias){
        if(idiom.equals("ESP")){
         
          dias[0]="Lunes";dias[1]="Martes";dias[2]="Miercoles";dias[3]="Jueves";dias[4]="Viernes";dias[5]="Sabado";dias[6]="domingo";
           System.out.println(dias[0]);
        }
        if(idiom.equals("ENG")){
          
          dias[0]="Monday";dias[1]="Tuesday";dias[2]="Wednsday";dias[3]="Thursday";dias[4]="Friday";dias[5]="Saturday";dias[6]="Sunday"; 
           System.out.println(dias[0]);
          
        }
       
    }
      public static void horasCalendario(String[] horas){
          int x = 0;
          for(int i = 0; i<24;i++){
              if(x==0){
         horas[i] = "0"+i+":00 - " + i + 1 + ":00";
              }else{
                horas[i] = i+ ":00 - " + i + ":00";  
              }
         
          }
        
    }
    public static void generarHtml(String[] dias,String[] horas) throws IOException {
          int diasmes = diasMes (2,2018);
          String ruta = "/Applications/XAMPP/xamppfiles/htdocs/ProjectesCat/ADD/src/add/archivo.html";
          File archivo = new File (ruta);
          BufferedWriter bw;
          bw = new BufferedWriter(new FileWriter(archivo));
         
        
        bw.write("<HTML> \n" +
"<HEAD> \n" +
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
            bw.write( "<TH>"+g.getDia()+"</TH>");
        }
        bw.write("</TR>");
        for(int i = 0;i<horas.length;i++){
            bw.write( " <TR>  <TD>"+horas[i]+"</TD>");
         for(int x = 0;i<dias.length;i++){
            bw.write( "<TD>oli</TD>");
        }
         bw.write( "</TR>");
        }
         bw.write("  </TABLE> \n" +
"\n" +
"</BODY> \n" +
"</HTML> ");
     bw.close();   
    }
}
