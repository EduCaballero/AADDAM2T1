package booking;

import com.company.ConfigReader;
import com.company.Peticion;
import log.EscrituraLog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ValidarBooking {

    private List<Peticion> peticiones;
    private ConfigReader idioma;
    private Sala llamadaSala;
    int [][] horaLimpia; //la hora ya limpia, sin 0 a la izquierda y parseada a int
    private String dia;
    private boolean enMascara; //si el día está dentro de la máscara = true
    

    public ValidarBooking(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }
    
////////////
    public void validarBookingSala(Sala sala) {
    	boolean valido = false; //si una sola vez la hora está ocupada, cierra
    	do {
        for (Peticion p : peticiones) {//bucle con el número de peticiones
        	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es == a el nombre que pasamos por parametro
        		int franjaDias = llamadaSala.franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los días
        		horaLimpia = llamadaSala.splitHoras();
        		for (int i=0; i<franjaDias; i++) {
        			enMascara=false; //lo fuerza a false para cada vez que de vuelta al bucle
        			dia = Sala.getDay((p.getFechaIni().getDayOfMonth() + i), p.getFechaIni().getMonthValue(), p.getFechaIni().getYear());//guardo el día que voy a comprobar si está en la máscara. (Monday...)
        			char [] mascara = p.getDias(); //traigo el array de getDias (que es la máscara)
        			for (int a=0; a<p.getDias().length; a++) {
        				String parseMascaraADia = idiomaMascara2(idioma.getInputLang(), mascara[a]); //Ej: Paso de "L" a "MONDAY" para poder comparar
        				if (dia.equalsIgnoreCase(parseMascaraADia)) enMascara=true;
        			}
        			if (enMascara) {
        				for (int z=0; z<horaLimpia.length; z++) { //el length da la primera posición del array bi
            				int franjaHoras = horaLimpia[i][1] - horaLimpia[i][0];//guardamos la franja horaria para comprobar todas las horas entre horaIn y horaFin
            				for (int y=0; y<franjaHoras; y++) {
            					valido=llamadaSala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[z][y]);//Si hay un sólo false, ya no valida
            					if (valido == false) EscrituraLog.escribir("Petición incorrecta por colisión.  " + p.toString());//si la petición da colisión, ya será incorrecta ergo escribimos al log
            					//llamadaSala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[i][1]);//
            				}
            			}
        			}
        		}
        	} 
        }
    	} while (valido == true);
    	
    	if (valido) { //Ahora asignamos. Esto debería hacerse con un "rollback", como una transcacción en SQL
    		for (Peticion p : peticiones) {//bucle con el número de peticiones
            	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es == a el nombre que pasamos por parametro
            		int franjaDias = llamadaSala.franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los días
            		horaLimpia = llamadaSala.splitHoras();
            		for (int i=0; i<franjaDias; i++) {
            			enMascara=false; //lo fuerza a false para cada vez que de vuelta al bucle
            			dia = Sala.getDay((p.getFechaIni().getDayOfMonth() + i), p.getFechaIni().getMonthValue(), p.getFechaIni().getYear());//guardo el día que voy a comprobar si está en la máscara. (Monday...)
            			char [] mascara = p.getDias(); //traigo el array de getDias (que es la máscara)
            			for (int a=0; a<p.getDias().length; a++) {
            				String parseMascaraADia = idiomaMascara2(idioma.getInputLang(), mascara[a]); //Ej: Paso de "L" a "MONDAY" para poder comparar
            				if (dia.equalsIgnoreCase(parseMascaraADia)) enMascara=true;
            			}
            			if (enMascara) {
            				for (int z=0; z<horaLimpia.length; z++) { //el length da la primera posición del array bi
                				int franjaHoras = horaLimpia[i][1] - horaLimpia[i][0];//guardamos la franja horaria para comprobar todas las horas entre horaIn y horaFin
                				for (int y=0; y<franjaHoras; y++) {
                					llamadaSala.asignarLibre2((p.getFechaIni().getDayOfMonth() + i), horaLimpia[z][y]);
                				}
                			}
            			}
            		}
            	}
            }
    	}
    }
    
    public void validarConMascara( ) {
    	
    }
    
    public static int getDiaSemana(Date dia){
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(dia);
    	return cal.get(Calendar.DAY_OF_WEEK);		
    }
    
    public static char[] idiomaMascara(String idioma) { 
    	if (idioma.equalsIgnoreCase("CAT")) {//LMCJVSG
    		char [] arrayMascaraCat = {'L', 'M', 'C', 'J', 'V', 'S', 'G'};
    		return arrayMascaraCat;
    	}
    	else if (idioma.equalsIgnoreCase("ESP")) {//LMXJVSD
    		char [] arrayMascaraEsp = {'L', 'M', 'X', 'J', 'V', 'S', 'D'};
    		return arrayMascaraEsp;
    	} else {//MTWHFSN
    		char [] arrayMascaraEng = {'M', 'T', 'W', 'H', 'F', 'S', 'N'};
    		return arrayMascaraEng;
    	}
    }
    
    public String tradMascara(String letraD) {
		if (letraD.equalsIgnoreCase("l")) return "lunes";
		else if (letraD.equalsIgnoreCase("m")) return "martes";
		else if (letraD.equalsIgnoreCase("c")) return "miercoles";
		else if (letraD.equalsIgnoreCase("j")) return "jueves";
		else if (letraD.equalsIgnoreCase("v")) return "viernes";
		else if (letraD.equalsIgnoreCase("s")) return "sabado";
		else return "domingo";
	}
    
    public static String idiomaMascara2(String idioma, char letraD) { 
    	if (idioma.equalsIgnoreCase("CAT")) {//LMXJVSD
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	}
    	else if (idioma.equalsIgnoreCase("ESP")) {//LMXJVSD
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	} else {//MTWHFSN
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	}
    }
    
    /*public static String idiomaMascara2(String idioma, char letraD) { 
    	if (idioma.equalsIgnoreCase("CAT")) {//LMXJVSD
    		if (letraD=='L') return "DILLUNS";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "DIMARTS";
    		else if (letraD=='X') return "DIMECRES";
    		else if (letraD=='J') return "DIJOUS";
    		else if (letraD=='V') return "DIVENDRES";
    		else if (letraD=='S') return "DISSABTE";
    		else return "DIUMENGE"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	}
    	else if (idioma.equalsIgnoreCase("ESP")) {//LMXJVSD
    		if (letraD=='L') return "LUNES";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "MARTES";
    		else if (letraD=='X') return "MIERCOLES";
    		else if (letraD=='J') return "JUEVES";
    		else if (letraD=='V') return "VIERNES";
    		else if (letraD=='S') return "SABADO";
    		else return "DOMINGO"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	} else {//MTWHFSN
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que sólo entren los carácteres válidos
    	}
    }*/
    
    
    
    ///////////// FALTA ASIGNAR HORAS Y MASCARA
}
