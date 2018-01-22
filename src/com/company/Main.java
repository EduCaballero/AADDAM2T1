package com.company;

import booking.Sala;
import booking.ValidarBooking;

import java.time.LocalDate;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        ConfigReader configReader = new ConfigReader();
        configReader.readConfig();

        PeticionReader peticionReader = new PeticionReader();
        peticionReader.readPeticiones();

        PeticionValidator peticionValidator = new PeticionValidator(peticionReader.getPeticiones(),
                configReader.getDate(), configReader.getInputLang());

        peticionValidator.validatePeticiones();

        LocalDate fecha = configReader.getDate();
        int dias = fecha.getMonth().length(false);
        Sala sala1 = new Sala("Sala1", dias);
        Sala sala2 = new Sala("Sala2", dias);

        //peticiones valid
        List<Peticion> request = peticionValidator.getPeticionesValidas();

        ValidarBooking validarBooking = new ValidarBooking(request);

        validarBooking.validarBookingSala(sala1);
    }
}
