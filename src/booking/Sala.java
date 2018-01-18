package booking;
import log.EscrituraLog;

import java.util.Calendar;

public class Sala {
	
	String nombre;
	static int[][] calendario;
	
	
	public Sala(String nombre, int[][] calendario) {
		this.nombre = nombre;
		Sala.calendario = calendario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int[][] getCalendario() {
		return calendario;
	}


	public void setCalendario(int[][] calendario) {
		Sala.calendario = calendario;
	}
	
	public static int diasMes (int mes, int anyo) {
		int numDias = 0;
	    Calendar fecha = Calendar.getInstance();
	    fecha.set(anyo, mes, 0);
	    numDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
	    return numDias;
	}
	
	public static void asignarOcupado(boolean ocupado, int dia, int hora) {
		if (ocupado) calendario[dia][hora]=1;
		else EscrituraLog.escribir("mensaje de error al log");;
	}
	
	public static boolean comprobarHorasLibres(int dia, int hora) {
		if (calendario[dia-1][hora]==0) {
			//asignarOcupado();
			//calendario[dia][hora]=1;
			//setCalendario(int [dia][1]);
			return true;
		}
		else return false;		
	}
	
	//Número de horas entre franjas (para hacer el bucle)
	public static int franjaHor(int inicio, int fin) {
		int numHoras=fin-inicio;
		return numHoras;
	}
	
	//que día es (lunes, martes, miércoles...)
	public static String getDia(int dia, int mes, int anyo) {
		//String devuelve = "";
		Calendar c = Calendar.getInstance();
		c.set(anyo, mes, dia); // vairables int
		dia = c.get(Calendar.DAY_OF_WEEK);
		if (dia == Calendar.SUNDAY) {
			//devuelve="domingo";
			return "domingo";
		}
		else if (dia == Calendar.MONDAY) {
			return "lunes";
		}
		else if (dia == Calendar.TUESDAY) {
			return "martes";
		}
		else if (dia == Calendar.WEDNESDAY) {
			return "miercoles";
		}
		else if (dia == Calendar.THURSDAY) {
			return "jueves";
		}
		else if (dia == Calendar.FRIDAY) {
			return "viernes";
		}
		else return "sabado";
	}
	
	////////OTRA SOLUCIÓN SERÍA
	/*String diaSemana (int dia, int mes, int ano)  {
        String letraD="";
        //Calendar c = Calendar.getInstance();
        //c.set(ano, mes, dia, 0, 0, 0);
        //nD=c.get(Calendar.DAY_OF_WEEK);
        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        calendar.set(ano, mes-1, dia); // ENERO SERÍA EL MES 0, ASÍ QUE HAY QUE -1
        int nD=calendar.get(Calendar.DAY_OF_WEEK);
        //SYSO("result","diaSemana: "+nD+" dia:"+dia+" mes:"+mes+ "año:" +ano);
        switch (nD){
            case 1: letraD = "D";
                break;
            case 2: letraD = "L";
                break;
            case 3: letraD = "M";
                break;
            case 4: letraD = "X";
                break;
            case 5: letraD = "J";
                break;
            case 6: letraD = "V";
                break;
            case 7: letraD = "S";
                break;
        }

        return letraD;
    }*/
	
	
public static String tradMascara(String letraD) {
		if (letraD.equalsIgnoreCase("l")) return "lunes";
		else if (letraD.equalsIgnoreCase("m")) return "martes";
		else if (letraD.equalsIgnoreCase("c")) return "miercoles";
		else if (letraD.equalsIgnoreCase("j")) return "jueves";
		else if (letraD.equalsIgnoreCase("v")) return "viernes";
		else if (letraD.equalsIgnoreCase("s")) return "sabado";
		else return "domingo";
	}
	
	
	
	//asignarOcupado()
	//comprobarDiasLibres()
	//cogerMes()
	//todo mascara dias//
	

}
