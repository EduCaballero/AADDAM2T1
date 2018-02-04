package com.company;

import add.Generador;
import booking.Sala;
import booking.ValidarBooking;
import java.io.IOException;

import java.time.LocalDate;
import java.util.List;


public class Main {
     
    public static void main(String[] args) throws IOException {

        ConfigReader configReader = new ConfigReader();
        configReader.readConfig();

        PeticionReader peticionReader = new PeticionReader();
        peticionReader.readPeticiones();
        
        
        PeticionValidator peticionValidator = new PeticionValidator(peticionReader.getPeticiones(),
                configReader.getDate(), configReader.getInputLang());

        peticionValidator.validatePeticiones();
        
        
        LocalDate fecha = configReader.getDate();
        int dias = fecha.getMonth().length(false);
        //Sala sala1 = new Sala("Sala1", dias);
        Sala sala1 = new Sala("Sala1");

        //Sala sala2 = new Sala("Sala2", dias);

        //peticiones valid
        List<Peticion> request = peticionValidator.getPeticionesValidas();
        request.forEach(System.out::println);

        ValidarBooking validarBooking = new ValidarBooking(configReader.getInputLang(), request);

        validarBooking.validarBookingSala(sala1);
        
        
        /////
        /*for (int[] a:sala1.getCalendario()) {
        	for (int b : a) {
        		System.out.println(b);
        	}
        }*/
        /////
        
     //(String idiom,String dias[],String horas[], String mysala,int [][] matriz);
       // Generador g = new Generador();
       // g.gestionPeticion(configReader.getOutputLang(), sala1.getNombre(), sala1.getCalendario());

    }//
}
