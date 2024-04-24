package com.kubago.jakzycpaniepremierze.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Postac {
    private final Integer DZIENNY_WYDATEK = 10;
    private String name;
    private Path path_to_image;
    private int dostepneSrodki;
    private Praca praca;
    private HashMap<String,Dobro> listaDobr;
    private HashMap<String,Pozyczka> listaPozyczek;
    private final Lotek lotek = new Lotek();
    private int winningMonths = 0;


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
    public boolean checkLoss(){
        return this.dostepneSrodki < 0;
    }
    public boolean checkWinCondition(){
        if (listaPozyczek.isEmpty()){
            for ( Dobro dobro: listaDobr.values()){
                if (dobro instanceof Dobro.DobroMieszkalne && ((Dobro.DobroMieszkalne) dobro).isWynajmowane()){
                    return true;
                }
            }
        }
        return false;
    }

    private void dzienneWydatki(){
        this.dostepneSrodki -= this.DZIENNY_WYDATEK;
    }

    private boolean uaktualnijSrodkiNaNowyMiesiac(){
        this.dostepneSrodki += praca.wyplata();
        for (Dobro dobro: this.listaDobr.values()){
            this.dostepneSrodki -= dobro.getKoszt_utrzymania();
        }
        for (Pozyczka pozyczka : this.listaPozyczek.values()){
            this.dostepneSrodki -= pozyczka.getRata();
            if (pozyczka.zaplacRate()){
                this.listaPozyczek.remove(pozyczka.getName());
            }
        }
        return this.checkLoss();
    }

    public void dodajKwote(int kwota){
        this.dostepneSrodki += kwota;
    }

    public void dodajKwote(int kwota, int n_razy){
        this.dostepneSrodki += (kwota * n_razy);
    }
    public boolean odejmijKwote(int kwota){
        this.dostepneSrodki -= kwota;
        return this.checkLoss();
    }
}
