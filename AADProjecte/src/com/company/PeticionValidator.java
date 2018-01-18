package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeticionValidator {

    // Fecha del archivo config.txt que usaremos para comprobar que las fechas de las peticiones
    // se comprenden dentro del mismo mes
    private LocalDate date;
    // Idioma de entrada del archivo config.txt
    private String lang;
    private List<Peticion> peticionesValidas;
    private List<Peticion> peticionesPendientes;

    public PeticionValidator(List<Peticion> peticiones, LocalDate date, String lang) {
        this.peticionesValidas = new ArrayList<>();
        this.peticionesPendientes = new ArrayList<>();
        this.date = date;
        this.lang = lang;
    }

    public void validatePeticiones() {
        peticionesPendientes.forEach(peticion -> {
            if (isValid(peticion)) {
                peticionesValidas.add(peticion);
            } else {
                writeLog(peticion);
            }
        });
    }

    private boolean isValid(Peticion p) {
        // Si la fecha de inicio o la fecha fin no tienen el mismo mes que la fecha de config.txt
        // la peticion no es valida
        if (!date.getMonth().equals(p.getFechaIni().getMonth()) || !date.getMonth().equals(p.getFechaFin().getMonth())) {
            return false;
        }

        // Validacion mascara de dias


        // validacion de franjas horarias
        if (!validateFranjasHorarias(p.getHoras())) return false;

        return true;
    }

    private boolean validateFranjasHorarias(List<String> horas) {
        for (String franja : horas) {
            int horaIni;
            int horaFin;
            // Las franjas horarias solo pueden contener 2 horas,
            // En caso de haber un numero diferente, la peticion es incorrecta
            String[] data = franja.split("-");
            if (data.length != 2) {
                return false;
            }
            // Comprobamos que las horas son numeros
            try {
                horaIni = Integer.parseInt(data[0]);
                horaFin = Integer.parseInt(data[1]);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                return false;
            }
            // Comprobamos que la hora de inicio esta entre las 0 y las 23
            if (horaIni < 0 || horaIni > 23) return false;
            // Comprobamos que la hora fin esta entre la 1 y las 24
            if (horaFin < 1 || horaFin > 24) return false;
            // La franja horaria tiene que estar dentro de un mismo dia
            // y como minimo ha de haber una hora de diferencia
            // por lo tanto la hora inicial no puede ser mayor o igual que la hora de fin
            if (horaIni >= horaFin) return false;
        }
        return true;
    }



    private void writeLog(Peticion p) {
        try(BufferedWriter log = new BufferedWriter(new FileWriter("incidencies.log", true))) {
            log.write("Peticion con formato incorrecto: " + p.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
