package booking;

import com.company.ConfigReader;
import com.company.Peticion;
import log.EscrituraLog;
import booking.Sala;

import java.time.LocalDate;
import java.util.List;

public class ValidarBooking {

    private List<Peticion> peticiones;
    private ConfigReader idioma = new ConfigReader();
    private Sala llamadaSala = new Sala("", 0); 
    int [][] horaLimpia; //la hora ya limpia, sin 0 a la izquierda y parseada a int
    private String dia;
    private boolean enMascara; //si el d?a est? dentro de la m?scara = true
    int[][] partes;
    //private Peticion p = new Peticion("", "", null, null, null, null);
    
    

    public ValidarBooking(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }
    
/////////////
    public void validarBookingSala(Sala sala) {
    	boolean valido = false; //si una sola vez la hora est? ocupada, cierra
    	do {
        for (Peticion p : peticiones) {//bucle con el n?mero de peticiones
        	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es == a el nombre que pasamos por parametro
        		int franjaDias = franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los d?as
        		horaLimpia = splitHoras(p);
        		for (int i=0; i<franjaDias; i++) {
        			enMascara=false; //lo fuerza a false para cada vez que de vuelta al bucle
        			dia = getDay((p.getFechaIni().getDayOfMonth() + i), p.getFechaIni().getMonthValue(), p.getFechaIni().getYear());//guardo el d?a que voy a comprobar si est? en la m?scara. (Monday...)
        			char [] mascara = p.getDias(); //traigo el array de getDias (que es la m?scara)
        			for (int a=0; a<p.getDias().length; a++) {
        				String parseMascaraADia = idiomaMascara2(idioma.getInputLang(), mascara[a]); //Ej: Paso de "L" a "MONDAY" para poder comparar
        				if (dia.equalsIgnoreCase(parseMascaraADia)) enMascara=true;
        			}
        			if (enMascara) {
        				for (int z=0; z<horaLimpia.length; z++) { //el length da la primera posici?n del array bi
            				int franjaHoras = horaLimpia[i][1] - horaLimpia[i][0];//guardamos la franja horaria para comprobar todas las horas entre horaIn y horaFin
            				for (int y=0; y<franjaHoras; y++) {
            					valido=sala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[z][y]);//Si hay un s?lo false, ya no valida
            					if (valido == false) EscrituraLog.escribir("Peticion incorrecta por colision.  " + p.toString());//si la petici?n da colisi?n, ya ser? incorrecta ergo escribimos al log
            					//llamadaSala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[i][1]);//
            				}
            			}
        			}
        		}
        	} 
        }
    	} while (valido == true);
    	
    	if (valido) { //Ahora asignamos. Esto deber?a hacerse con un "rollback", como una transcacci?n en SQL
    		for (Peticion p : peticiones) {//bucle con el n?mero de peticiones
            	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es == a el nombre que pasamos por parametro
            		int franjaDias = franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los d?as
            		horaLimpia = splitHoras(p);
            		for (int i=0; i<franjaDias; i++) {
            			enMascara=false; //lo fuerza a false para cada vez que de vuelta al bucle
            			dia = getDay((p.getFechaIni().getDayOfMonth() + i), p.getFechaIni().getMonthValue(), p.getFechaIni().getYear());//guardo el d?a que voy a comprobar si est? en la m?scara. (Monday...)
            			char [] mascara = p.getDias(); //traigo el array de getDias (que es la m?scara)
            			for (int a=0; a<p.getDias().length; a++) {
            				String parseMascaraADia = idiomaMascara2(idioma.getInputLang(), mascara[a]); //Ej: Paso de "L" a "MONDAY" para poder comparar
            				if (dia.equalsIgnoreCase(parseMascaraADia)) enMascara=true;
            			}
            			if (enMascara) {
            				for (int z=0; z<horaLimpia.length; z++) { //el length da la primera posici?n del array bi
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
    
    
    
    public static String idiomaMascara2(String idioma, char letraD) { 
    	if (idioma.equalsIgnoreCase("CAT")) {//LMXJVSD
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	}
    	else if (idioma.equalsIgnoreCase("ESP")) {//LMXJVSD
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	} else {//MTWHFSN
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	}
    }
    
    public int[][] splitHoras(Peticion p) {//esto me devuelve las horas limpias y en int
    	for (int i=0; i<p.getHoras().size(); i++) {
    		//String linea = p.getHoras()[i];
    		List<String> arrayList = p.getHoras(); //traigo el arrayList de string que contiene las franjas horarias
    		String lineaSucio = arrayList.get(i); //guardo la posicion i en un string
    		//TODO esto debería ir después del split, ya que sólo me controla el primer 0, no el segundo si lo hubiere
    		String linea = lineaSucio.replaceFirst("^0*", "");//por si acaso hay un 0 delante, eliminarlo y que int no lo tome como octal		
    		//partes = [i][linea.split("-")]; //parto el string por "-" y
    		String[] partesSucio = linea.split("-"); //parto
    		partes = new int [p.getHoras().size()][partesSucio.length];//hago un array bidimensional del tamo que tenga getHoras y de 2, que son la horaIn y horaFin
    		for (int z=0; z<partesSucio.length; z++) {
    			partes[i][z]  = Integer.parseInt(partesSucio[z]);//guardo la hora ya limpia en el arraybi partes
    		}
    		//horaIn = Integer.parseInt(partes[0]); //los convierto a int y los guardo en las variables static
    		//horaFin = Integer.parseInt(partes[1]);
    	}
    	return partes;	//ARRAY BIDIMENSIONAL PARA PARTES
    }
    
    public static String getDay(int day, int month, int year) {
		return LocalDate.of(year, month, day).getDayOfWeek().toString();
	}
    
  //Numero de horas entre franjas (para hacer el bucle)
  	public static int franjaHor(int inicio, int fin) {
  		int numHoras=fin-inicio;
  		return numHoras;
  	}
    
    
  	
  	////////////////////////SIN USO O PARA FUTURAS MEJORAS, NO LEER/////////////////////////////////////////
    /*public static String idiomaMascara2(String idioma, char letraD) { 
    	if (idioma.equalsIgnoreCase("CAT")) {//LMXJVSD
    		if (letraD=='L') return "DILLUNS";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "DIMARTS";
    		else if (letraD=='X') return "DIMECRES";
    		else if (letraD=='J') return "DIJOUS";
    		else if (letraD=='V') return "DIVENDRES";
    		else if (letraD=='S') return "DISSABTE";
    		else return "DIUMENGE"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	}
    	else if (idioma.equalsIgnoreCase("ESP")) {//LMXJVSD
    		if (letraD=='L') return "LUNES";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "MARTES";
    		else if (letraD=='X') return "MIERCOLES";
    		else if (letraD=='J') return "JUEVES";
    		else if (letraD=='V') return "VIERNES";
    		else if (letraD=='S') return "SABADO";
    		else return "DOMINGO"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	} else {//MTWHFSN
    		if (letraD=='L') return "MONDAY";//tipos primitivos se comparan con ==
    		else if (letraD=='M') return "TUESDAY";
    		else if (letraD=='X') return "WEDNESDAY";
    		else if (letraD=='J') return "THURSDAY";
    		else if (letraD=='V') return "FRIDAY";
    		else if (letraD=='S') return "SATURDAY";
    		else return "SUNDAY"; //ya se comprueba previamente que s?lo entren los car?cteres v?lidos
    	}
    }*/
    
    
  	/*static int getDiaSemana(Date dia){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(dia);
	return cal.get(Calendar.DAY_OF_WEEK);		
}*/

/*public static char[] idiomaMascara(String idioma) { 
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
}*/

/*public String tradMascara(String letraD) {
	if (letraD.equalsIgnoreCase("l")) return "lunes";
	else if (letraD.equalsIgnoreCase("m")) return "martes";
	else if (letraD.equalsIgnoreCase("c")) return "miercoles";
	else if (letraD.equalsIgnoreCase("j")) return "jueves";
	else if (letraD.equalsIgnoreCase("v")) return "viernes";
	else if (letraD.equalsIgnoreCase("s")) return "sabado";
	else return "domingo";
}*/
}
