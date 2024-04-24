package com.kubago.jakzycpaniepremierze.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Postac {
    private String name;
    private Path path_to_image;
    private int dostepneSrodki;
    private Praca praca;
    private HashMap<String,Dobro> listaDobr;
    private HashMap<String,Pozyczka> listaPozyczek;
    private final Lotek lotek = new Lotek();


    public Postac(String name, Path path_to_image) {
        this.name = name;
        this.path_to_image = path_to_image;
    }
    public void setPraca(Praca praca) {
        this.praca = praca;
    }
    public void strataPracy(){
        this.praca = praca.wybierz_prace(Prace.BEZROBOTNY);
    }
    public void kupDobro(Dobro dobro) throws Exception {
        if (this.dostepneSrodki < dobro.getCena()){
            throw new Exception("Nie można kupić. Niewystarczająca ilość środków.");
        }
        this.dostepneSrodki =- dobro.getCena();
        listaDobr.put(dobro.getNazwa(), dobro);
    }
    public void sprzedajDobro(String nazwa){
        if (!listaDobr.containsKey(nazwa)){
            throw new IllegalArgumentException("Ta postać nie ma takiego dobra.");
        }
        Dobro dobro = listaDobr.remove(nazwa);
        this.dostepneSrodki += dobro.getCena_sprzedazy();
    }

    public void dodajPozyczke(Pozyczka pozyczka){
        listaPozyczek.put(pozyczka.getName(), pozyczka);
        this.dostepneSrodki += pozyczka.getKwota();
    }
    public void usunPozyczke(String name){
        listaPozyczek.remove(name);
    }
    public void postawLotka(int numer){
        this.lotek.dodajNumer(numer);
    }
    public boolean sprawdzLotka(int number){
        boolean result = lotek.sprawdzWygrana(number);
        lotek.reset();
        return result;
    }
}
