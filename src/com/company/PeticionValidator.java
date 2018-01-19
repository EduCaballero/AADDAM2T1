package com.company;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.peticionesPendientes = peticiones;
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
//        peticionesValidas.forEach(System.out::println);
    }

    private boolean isValid(Peticion p) {
        // Si la fecha de inicio o la fecha fin no tienen el mismo mes que la fecha de config.txt
        // la peticion no es valida
        if (!date.getMonth().equals(p.getFechaIni().getMonth()) || !date.getMonth().equals(p.getFechaFin().getMonth())) {
            return false;
        }

        // Validacion mascara de dias
        System.out.println(p);
        if (!validateDayMask(p.getDias())) {
            return false;
        }

        // validacion de franjas horarias
        if (!validateFranjasHorarias(p.getHoras())) return false;

        return true;
    }

    private boolean validateDayMask(char[] mask) {
        char[] internationalMask = readDayMask();
        if (internationalMask == null) {
            System.out.println("No se ha leido bien la mascara de dias del archivo internacional.EXT");
        }
        // comprobamos que los dias de la mascara de la peticion
        // estan contenidos en la mascara del archivo internacional
        // (por ejemplo, que la mascara de la peticion no tenga caracteres raros,
        // o una W si la peticion supuestamente es en ESP y no en ENG
        boolean found = false;
        for (char day : mask) {
            found = false;
//            System.out.println("current char = " + day);
            for (char interDay : internationalMask) {
//                System.out.println("Is " + day + " = " + interDay);
                if (day == interDay) {
                    found = true;
                    break;
                }
            }
        }

        if (!found) return false;

        // Comprobar que el orden de la mascara de dias es creciente
        // Es decir, que no ponga Miercoles > Lunes > Viernes sino Lunes > Miercoles > Viernes

        String maskString = new String(mask);
//        System.out.println("Mascara dias = " + maskString);
        switch (lang) {
            case "ESP":
                if (!maskString.matches("([L])?([M])?([X])?([J])?([V])?([S])?([D])?")) {
                    return false;
                }
            case "CAT":
                if (!maskString.matches("([L])?([M])?([C])?([J])?([V])?([S])?([G])?")) {
                   return false;
                }
            case "ENG":
                if (!maskString.matches("([M])?([T])?([W])?([H])?([F])?([S])?([N])?")) {
                    return false;
            }
        }
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
            log.write("Peticion con formato incorrecto: " + p.toString() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private char[] readDayMask() {
        String internacionalFile;
        switch (this.lang) {
            case "ESP":
                internacionalFile = "internacional.ESP";
                break;
            case "ENG":
                internacionalFile = "internacional.ENG";
                break;
            case "CAT":
                internacionalFile = "internacional.CAT";
                break;
            // No deberia ocurrir, pero en caso de que no se haya encontrado un idioma,
            // se asumira el ESP por defecto
            default:
                internacionalFile = "internacional.ESP";
        }

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(internacionalFile)))) {
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(";");
                if (line[0].equals("003")) {
                    return line[1].toCharArray();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Peticion> getPeticionesValidas() {
        return peticionesValidas;
    }
}
