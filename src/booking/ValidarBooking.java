package booking;

import com.company.Peticion;
import log.EscrituraLog;

import java.util.List;

public class ValidarBooking {

    private List<Peticion> peticiones;
    private EscrituraLog escrituraLog;

    public ValidarBooking(List<Peticion> peticiones) {
        this.peticiones = peticiones;
        this.escrituraLog = new EscrituraLog();
    }

    public void validarBookingSala(Sala sala) {
        for (Peticion p : peticiones) {

            // Escribir en el log peticion con colision
            escrituraLog.escribir(p.toString());
        }
    }
}
