package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ConfigReader configReader = new ConfigReader();
        configReader.readConfig();

        PeticionReader peticionReader = new PeticionReader();
        peticionReader.readPeticiones();

        PeticionValidator peticionValidator = new PeticionValidator(peticionReader.getPeticiones(),
                configReader.getDate(), configReader.getInputLang());

        peticionValidator.validatePeticiones();

    }

//    private static void readConfig() {
//        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("config.txt")))) {
//            date = sc.nextLine();
//            language = sc.nextLine();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static ArrayList<String> readPeticiones() {
//        ArrayList<String> peticiones = new ArrayList<>();
//        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("peticiones.txt")))) {
//            while (sc.hasNext()) {
//                peticiones.add(sc.nextLine());
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return peticiones;
//    }

//    private static void validatePeticones(ArrayList<String> peticiones) {
//        for (String pet : peticiones) {
//            if (isValidPeticion(pet)) {
//                Peticion peticion = createPeticion(pet);
////                if (isCollisionFree(peticion)) {
//                    validatedPeticiones.add(peticion);
////                }
//            }
//        }
//    }

//    private static boolean isValidPeticion(String peticion) {
//        String[] peticionData = peticion.split(" ");
////        String[] fechaIniSplit = peticionData[2].split("/");
////        String[] fechaFinSplit = peticionData[3].split("/");
////        String[] configDateSplit = date.split(" ");
//
//        if (!isValidDate(peticionData[2]) || !isValidDate(peticionData[3])) {
//            return false;
//        }

//        Calendar configDate = Calendar.getInstance();
//        Calendar fechaIni = Calendar.getInstance();
//        Calendar fechaFin = Calendar.getInstance();
//
//        // Los meses estan indexados de 0 a 11
//        configDate.set(Calendar.YEAR, Integer.parseInt(configDateSplit[0]));
//        configDate.set(Calendar.MONTH, Integer.parseInt(configDateSplit[1]) - 1);
//
//        fechaIni.set(Calendar.YEAR, Integer.parseInt(fechaIniSplit[2]));
//        fechaIni.set(Calendar.MONTH, Integer.parseInt(fechaIniSplit[1]) - 1);
//
//        fechaFin.set(Calendar.YEAR, Integer.parseInt(fechaFinSplit[2]));
//        fechaFin.set(Calendar.MONTH, Integer.parseInt(fechaFinSplit[1]) - 1);


        // Las fechas de inicio y fin no pertenecen al mismo mes y año que la
        // indicada en el archivo config
//            System.out.println(configDate.compareTo(fechaIni));
//        if (configDate.compareTo(fechaIni) != 0 || configDate.compareTo(fechaFin) != 0) {
//            return false;
//        }

//        char[] days = new char[7];
//        String[] langSplit = language.split(" ");
//        if (langSplit[0].equals("ESP")) {
//            days[0] = 'L';
//            days[1] = 'M';
//            days[2] = 'C';
//            days[3] = 'J';
//            days[4] = 'V';
//            days[5] = 'S';
//            days[6] = 'G';
//        } else if (langSplit[0].equals("ENG")) {
//            days[0] = 'M';
//            days[1] = 'T';
//            days[2] = 'W';
//            days[3] = 'H';
//            days[4] = 'F';
//            days[5] = 'S';
//            days[6] = 'N';
//        }
//
//        char[] daysPeticion = peticionData[4].toCharArray();
//        for (char c : daysPeticion) {
//            if (!containsDay(days, c)) {
//                System.out.println("Contiene dias erroneos");
//                return false;
//            }
//        }
//
//
//        String[] franjasHorarias = peticionData[5].split("_");
//        if (franjasHorarias.length > 5)
//        for (String s : franjasHorarias) {
//            if (!isValidFranja(s)) {
//                System.out.println("franja no valida");
//                return false;
//            }
//        }
//
//
////                switch (daysPeticion[i]) {
////                    case days[0]:
////                        return false;
////
////                    case days[1]:
////                        if (daysPeticion[i-1] != days[0]) {
////                            return false;
////                        }
////                        break;
////
////                    case days[2]:
////                        if (daysPeticion[i-1] != days[0] && daysPeticion[i-1] != days[1]) {
////                            return false;
////                        }
////                        break;
////
////                    case 'J':
////                        if (daysPeticion[i-1] != days[0] && daysPeticion[i-1] != 'M' && daysPeticion[i-1] != 'C') {
////                            return false;
////                        }
////                        break;
////
////                    case 'V':
////                        if (daysPeticion[i-1] != days[0] && daysPeticion[i-1] != 'M' && daysPeticion[i-1] != 'C' && daysPeticion[i-1] != 'J') {
////                            return false;
////                        }
////                        break;
////
////                    case 'S':
////                        if (daysPeticion[i-1] != days[0] && daysPeticion[i-1] != 'M' && daysPeticion[i-1] != 'C' && daysPeticion[i-1] != 'J'
////                                && daysPeticion[i-1] != 'V') {
////                            return false;
////                        }
////                        break;
////
////                    case 'G':
////                        if (daysPeticion[i-1] != days[0] && daysPeticion[i-1] != 'M' && daysPeticion[i-1] != 'C' && daysPeticion[i-1] != 'J'
////                                && daysPeticion[i-1] != 'V' && daysPeticion[i-1] != 'S') {
////                            return false;
////                        }
////                        break;
////                    default:
////                        return false;
////                }
//
//
//        System.out.println("true");
//        return true;
//    }
//
//    static private boolean containsDay(char[] days, char day) {
//        for (char c : days) {
//            if (day == c) return true;
//        }
//        return false;
//    }
//
//
//
//
//
//    private static Peticion createPeticion(String peticion) {
//        String[] peticionData = peticion.split(" ");
//        String[] fechaIni = peticionData[2].split("/");
//        String[] fechaFin = peticionData[3].split("/");
//        LocalDate ini = LocalDate.of(Integer.parseInt(fechaIni[2]), Integer.parseInt(fechaIni[1]), Integer.parseInt(fechaIni[0]));
//
//        LocalDate fin = LocalDate.of(Integer.parseInt(fechaFin[2]), Integer.parseInt(fechaFin[1]), Integer.parseInt(fechaFin[0]));
//        char[] days = peticionData[4].toCharArray();
//
//        return new Peticion(peticionData[0], peticionData[1], ini, fin, days, peticionData[5]);
//    }


}
