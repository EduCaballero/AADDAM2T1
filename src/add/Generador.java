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
import java.io.PrintWriter;
import java.util.Calendar;
import static jdk.nashorn.tools.ShellFunctions.input;

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

    public void gestionPeticion(String idiom, String mysala,int [][] matriz) throws FileNotFoundException, IOException{
        String[] dias =new String[7];
       String[] horas =new String[24];
         diascalendar(idiom,dias);
      //carga array de horas
      horasCalendario(horas);
            String ruta = "C:\\Users\\alu2016425\\Documents\\AADDAM2T1\\src\\add\\megenero.html";
          File archivo = new File (ruta);
          BufferedWriter bw;
          bw = new BufferedWriter(new FileWriter(archivo));
         
         for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(matriz[i][j]);
               
            }
        }
        bw.write("<HTML> \n" +
"<HEAD> \n "+
"<link href=\"myStyles.css\" rel=\"stylesheet\" type=\"text/css\"/>" +                
"\n" +
"</HEAD> \n" +
"<BODY> \n" +
"\n" +
"<H1>"+ mysala+"</H1> \n" +
"\n" +
"<TABLE id='tables'> \n" +
"<TR> "+  
        
       "<TH>week</TH>" );
        for(int i = 0;i<dias.length;i++){
            Generador g = new Generador();
            g.setDia(dias[i]);
            bw.write( "<TH class='mydays'>"+g.getDia()+"</TH> \n");
        }
        bw.write("</TR>");
        int j=0;
        int i =0;
        
        
        for (i = 0; i < horas.length; i++) {
             bw.write( " <TR>  <TD class='myhours'>"+horas[i]+"</TD> \n");
            for (j = 0; j < dias.length; j++) {
                if(i>=8){
                if(j<5){
              
               
               if(matriz[i][j]==1){
                   bw.write( "<TD class='chosed'>open</TD> \n" );
               }else{
                  bw.write( "<TD class='open'>open</TD> \n" ); 
               }
               
                }
                
                if(j>4){
                  bw.write( "<TD class='closed'>close</TD> \n" );   
                }
           }else{
                 bw.write( "<TD class='closed'>close</TD> \n" );    
           }
        }
        
        }
         bw.write("  </TABLE> \n" +
"\n" +
"</BODY> \n" +
"</HTML> ");
     bw.close();   
    }

   

   
    
}