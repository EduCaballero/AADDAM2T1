package com.company;

import java.time.LocalDate;
import java.util.List;

public class Peticion {

    private String actividad;
    private String espacio;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private char[] dias;
    private List<String> horas;

    public Peticion(String actividad, String espacio, LocalDate fechaIni, LocalDate fechaFin,
                    char[] dias, List<String> horas) {
        this.actividad = actividad;
        this.espacio = espacio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.horas = horas;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public char[] getDias() {
        return dias;
    }

    public void setDias(char[] dias) {
        this.dias = dias;
    }

    public List<String> getHoras() {
        return horas;
    }

    public void setHoras(List<String> horas) {
        this.horas = horas;
    }

//    @Override
//    public String toString() {
//        return "Peticion{" +
//                "actividad='" + actividad + '\'' +
//                ", espacio='" + espacio + '\'' +
//                ", fechaIni=" + fechaIni +
//                ", fechaFin=" + fechaFin +
//                ", dias=" + Arrays.toString(dias) +
//                ", horas='" + horas + '\'' +
//                '}';
//    }
}
