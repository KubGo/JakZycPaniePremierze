package com.kubago.jakzycpaniepremierze.models;

import java.nio.file.Path;
import java.util.ArrayList;

public class Postac {
    private String name;
    private Path path_to_image;
    private int dostepneSrodki;
    private Praca praca;
    private ArrayList<Dobro> listaDobr;
    private ArrayList<Pozyczka> listaPozyczek;
    private Lotek lotek;


    public Postac(String name, Path path_to_image) {
        this.name = name;
        this.path_to_image = path_to_image;
    }

}
