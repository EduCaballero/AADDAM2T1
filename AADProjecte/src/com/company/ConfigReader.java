package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ConfigReader {

    private LocalDate date;
    private String inputLang;
    private String outputLang;

    public void readConfig() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("config.txt")))) {
            String date = sc.nextLine();
            String language = sc.nextLine();

            String[] dateData = date.split(" ");
            String[] langData = language.split(" ");

            this.date = LocalDate.of(Integer.parseInt(dateData[0]), Integer.parseInt(dateData[1]), 1);
            this.inputLang = langData[0];
            this.outputLang = langData[1];
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String getInputLang() {
        return inputLang;
    }

    public String getOutputLang() {
        return outputLang;
    }
}
