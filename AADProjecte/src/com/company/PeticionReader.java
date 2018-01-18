package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PeticionReader {

    private List<Peticion> peticiones;

    public PeticionReader() {
        this.peticiones = new ArrayList<>();
    }

    public void readPeticiones() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("peticiones.txt")))) {
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Peticion peticion = createPeticion(s);
                // Si la creacion de peticion ha fallado se escribe en el log de indicencias
                // de lo contrario se guarda en la lista de peticiones, pendiente de validar
                if (peticion != null) {
                    peticiones.add(peticion);
                } else {
                    writeLog(s);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        peticiones.forEach(System.out::println);
    }

    private Peticion createPeticion(String s) {
        String[] peticionData = s.split(" ");
        // Las peticiones han de tener exactamente 6 campos
        // 1. Actividad 2. Espacio 3. Fecha de incio 4. Fecha fin 5. Mascara de dias 6. Franja horaria
        // Si no tiene exactamente 6 campos, la peticion es invalida.
        if (peticionData.length != 6) {
            return null;
        }
        String[] horasData = peticionData[5].split("_");

        // Parseamos las fechas para comprobar que tengan un formato correcto
        LocalDate fechaIni;
        LocalDate fechaFin;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            fechaIni = LocalDate.parse(peticionData[2], formatter);
            fechaFin = LocalDate.parse(peticionData[3], formatter);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return null;
        }

        // Convertimos el string de la mascara de dias en un char[]
        char[] dias = peticionData[4].toCharArray();

        // Dividimos el string de horas y lo colocamos en un arraylist de franjas horarias (xx-xx)
        List<String> horas = new ArrayList<>(Arrays.asList(horasData));
        // Como maximo puede tener 5 franjas horarias.
        if (horas.size() > 5) {
            return null;
        }
        return new Peticion(peticionData[0], peticionData[1], fechaIni, fechaFin, dias, horas);
    }

    public List<Peticion> getPeticiones() {
        return peticiones;
    }


    private void writeLog(String s) {
        try(BufferedWriter log = new BufferedWriter(new FileWriter("incidencies.log", true))) {
            log.write("Peticion con formato incorrecto: " + s + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
