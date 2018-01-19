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
        for (Peticion p : peticiones) {
        	if (p.getEspacio().equalsIgnoreCase(sala.getNombre())) {
        		int franjaDias = llamadaSala.franjaHor(p.getFechaFin().getDayOfMonth(), p.getFechaIni().getDayOfMonth());
        		boolean valido = false;
        		for (int i=0; i>franjaDias; i++) {
        			
        		}
        		
        	} //else EscrituraLog.escribir("Petición incorrecta, la sala no existe" + p.toString());
            // Escribir en el log peticion con colision
            //EscrituraLog.escribir(p.toString());
        }
    }
}
