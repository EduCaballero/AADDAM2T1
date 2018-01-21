package booking;

import com.company.Peticion;
import log.EscrituraLog;

import java.util.List;

public class ValidarBooking {

    private List<Peticion> peticiones;
    private Sala llamadaSala;
    

    public ValidarBooking(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    public void validarBookingSala(Sala sala) {
        for (Peticion p : peticiones) {//bucle con el número de peticiones
        	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {//si el nombre de la sala es = a el nombre que pasamos por parametro
        		int franjaDias = llamadaSala.franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth()); //guardamos la franja de los días
        		boolean valido = false; //creamos un bool para abajo
        		for (int i=0; i>franjaDias; i++) {
        			llamadaSala.comprobarHorasLibres((p.getFechaFin().getDayOfMonth() + i), hora) //necesito pasar hora de string a int
        		}
        		
        	} //else EscrituraLog.escribir("Petición incorrecta, la sala no existe" + p.toString());
            // Escribir en el log peticion con colision
            //EscrituraLog.escribir(p.toString());
        }
    }
}
