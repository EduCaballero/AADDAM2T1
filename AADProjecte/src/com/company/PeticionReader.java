package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PeticionReader {

    private List<Peticion> peticiones;
    private List<String> peticionesString;

    public PeticionReader() {
        this.peticionesString = new ArrayList<>();
        this.peticiones = new ArrayList<>();
    }

    private void readPeticiones() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("peticiones.txt")))) {
            while (sc.hasNext()) {
                Peticion peticion = createPeticion(sc.nextLine();
                if (peticion != null) {
                    peticiones.add();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Peticion createPeticion(String s) {
        String[] peticionData = s.split(" ");
        String[] fechaIniData = peticionData[2].split("/");
        String[] fechaFinData = peticionData[3].split("/");
        String[] horasData = peticionData[5].split("_");
        LocalDate fechaIni;
        LocalDate fechaFin;
        try {
            fechaIni = LocalDate.of(Integer.parseInt(fechaIniData[2]),
                    Integer.parseInt(fechaIniData[1]),
                    Integer.parseInt(fechaIniData[0]));

            fechaFin = LocalDate.of(Integer.parseInt(fechaFinData[2]),
                    Integer.parseInt(fechaFinData[1]),
                    Integer.parseInt(fechaFinData[0]));
        } catch (NumberFormatException e) {

            return null;
        }

        ArrayList<String> horas = new ArrayList<>();
        for (String franja : horasData) {
            horas.add(franja);
        }

        return new Peticion()

    }

    public List<Peticion> getPeticiones() {
        return peticiones;
    }
}
