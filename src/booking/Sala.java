package booking;
import log.EscrituraLog;

import java.util.Calendar;
import java.util.List;

import com.company.Peticion;

public class Sala {

	String nombre;
	int[][] calendario;
	private Peticion p;
	static int horaIn;
	static int horaFin;
	int [][] partes;
	int [][] horaLimpia;

	public Sala(String nombre, int horizontal) {
		this.nombre = nombre;
		this.calendario = new int [24][horizontal];
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
		this.calendario = calendario;
	}

	//d?as que tiene el mes
	public int diasMes (int mes, int anyo) {
		int numDias = 0;
	    Calendar fecha = Calendar.getInstance();
	    fecha.set(anyo, mes, 0);
	    numDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
	    return numDias;
	}

	public void asignarLibre(boolean libre, int dia, int hora) {
		if (libre) calendario[dia-1][hora]=1;
		else EscrituraLog.escribir("mensaje de error al log");
	}

	public boolean comprobarHorasLibres(int dia, int hora) {
		if (calendario[dia-1][hora]==0) {
			//asignarOcupado();
			//calendario[dia][hora]=1;
			//setCalendario(int [dia][1]);
			return true;
		}
		else return false;
	}

	//N?mero de horas entre franjas (para hacer el bucle)
	public int franjaHor(int inicio, int fin) {
		int numHoras=fin-inicio;
		return numHoras;
	}

	//que d?a es (lunes, martes, mi?rcoles...)
	public String getDia(int dia, int mes, int anyo) {
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

	////////OTRA SOLUCI?N SER?A
	/*String diaSemana (int dia, int mes, int ano)  {
        String letraD="";
        //Calendar c = Calendar.getInstance();
        //c.set(ano, mes, dia, 0, 0, 0);
        //nD=c.get(Calendar.DAY_OF_WEEK);
        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        calendar.set(ano, mes-1, dia); // ENERO SER?A EL MES 0, AS? QUE HAY QUE -1
        int nD=calendar.get(Calendar.DAY_OF_WEEK);
        //SYSO("result","diaSemana: "+nD+" dia:"+dia+" mes:"+mes+ "a?o:" +ano);
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


public String tradMascara(String letraD) {
		if (letraD.equalsIgnoreCase("l")) return "lunes";
		else if (letraD.equalsIgnoreCase("m")) return "martes";
		else if (letraD.equalsIgnoreCase("c")) return "miercoles";
		else if (letraD.equalsIgnoreCase("j")) return "jueves";
		else if (letraD.equalsIgnoreCase("v")) return "viernes";
		else if (letraD.equalsIgnoreCase("s")) return "sabado";
		else return "domingo";
	}

//hacer split de las horas
public int[][] splitHoras() {//esto me devuelve las horas limpias y en int
	for (int i=0; i<p.getHoras().size(); i++) {
		//String linea = p.getHoras()[i];
		List<String> arrayList = p.getHoras(); //traigo el arrayList de string que contiene las franjas horarias
		String lineaSucio = arrayList.get(i); //guardo la posición i en un string
		String linea = lineaSucio.replaceFirst("^0*", "");//por si acaso hay un 0 delante, eliminarlo y que int no lo tome como octal		
		//partes = [i][linea.split("-")]; //parto el string por "-" y
		String[] partesSucio = linea.split("-"); //parto
		partes = new int [p.getHoras().size()][partesSucio.length];//hago un array bidimensional del tamaño que tenga getHoras y de 2, que son la horaIn y horaFin
		for (int z=0; z<partesSucio.length; z++) {
			partes[i][z]  = Integer.parseInt(partesSucio[z]);//guardo la hora ya limpia en el arraybi partes
		}
		//horaIn = Integer.parseInt(partes[0]); //los convierto a int y los guardo en las variables static
		//horaFin = Integer.parseInt(partes[1]);
	}
	return partes;	//ARRAY BIDIMENSIONAL PARA PARTES
}

	//asignarOcupado()
	//comprobarDiasLibres()
	//cogerMes()
	//todo mascara dias//
//eliminar 0 a la izquierda de las horas
/*String cadenaOriginal = "000000543210";
// La expresión a utilizar sería:
cadenaResultadoString = cadenaOriginal.replaceFirst ("^0*", "");*/

}
