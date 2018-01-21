package booking;

import com.company.Peticion;
import log.EscrituraLog;

import java.util.List;

public class ValidarBooking {

    private List<Peticion> peticiones;
    private Sala llamadaSala;
    int [][] horaLimpia; //la hora ya limpia, sin 0 a la izquierda y parseada a int
    

    public ValidarBooking(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }
////////////
    public void validarBookingSala(Sala sala) {
    	boolean valido = false; //si una sola vez la hora está ocupada, cierra
    	do {
        for (Peticion p : peticiones) {//bucle con el número de peticiones
        	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es = a el nombre que pasamos por parametro
        		int franjaDias = llamadaSala.franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los días
        		horaLimpia = llamadaSala.splitHoras();
        		for (int i=0; i<franjaDias; i++) {
        			for (int z=0; z<horaLimpia.length; z++) { //el length da la primera posición del array bi
        				int franjaHoras = horaLimpia[i][1] - horaLimpia[i][0];//guardamos la franja horaria para comprobar todas las horas entre horaIn y horaFin
        				for (int y=0; y<franjaHoras; y++) {
        					valido=llamadaSala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[z][y]);//Si hay un sólo false, ya no valida
        					if (valido == false) EscrituraLog.escribir("Petición incorrecta por colisión.  " + p.toString());//si la petición da colisión, ya será incorrecta ergo escribimos al log
        					//llamadaSala.comprobarHorasLibres((p.getFechaIni().getDayOfMonth() + i), horaLimpia[i][1]);//
        				}
        			}
        		}
        		
        	} //else EscrituraLog.escribir("Petición incorrecta, la sala no existe" + p.toString());
            // Escribir en el log peticion con colision
            //EscrituraLog.escribir(p.toString());
        }
    	} while (valido == true);
    }
    
    ///////////// FALTA ASIGNAR HORAS Y MASCARA
}
