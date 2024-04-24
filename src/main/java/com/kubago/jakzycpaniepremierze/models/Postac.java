package com.kubago.jakzycpaniepremierze.models;

import java.nio.file.Path;
import java.util.HashMap;

/**
 * Klasa postaci w którą wciela sie gracz
 */
public class Postac {
    /**
     * Stały dzienny koszt przeżycia
     */
    private final Integer DZIENNY_WYDATEK = 10;
    /**
     * Nazwa postaci
     */
    private String name;
    /**
     * Ścieżka do obrazu z postacią
     */
    private Path path_to_image;
    /**
     * Liczba dostępnych środków przez postać
     */
    private int dostepneSrodki;
    /**
     * Praca, którą posiada akutualnie postać
     */
    private Praca praca;
    /**
     * Mapa dóbr posiadanych przez postać
     */
    private HashMap<String,Dobro> mapaDobr;
    /**
     * Mapa pożyczek posiadanych przez postać
     */
    private HashMap<String,Pozyczka> mapaPozyczek;
    /**
     * Lotek użytkownika
     */
    private final Lotek lotek = new Lotek();
    /**
     * Ilość miesięcy przez które spełnia kryteria wygranej
     */
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
        mapaDobr.put(dobro.getNazwa(), dobro);
    }
    public void sprzedajDobro(String nazwa){
        if (!mapaDobr.containsKey(nazwa)){
            throw new IllegalArgumentException("Ta postać nie ma takiego dobra.");
        }
        Dobro dobro = mapaDobr.remove(nazwa);
        this.dostepneSrodki += dobro.getCena_sprzedazy();
    }

    public void dodajPozyczke(Pozyczka pozyczka){
        mapaPozyczek.put(pozyczka.getName(), pozyczka);
        this.dostepneSrodki += pozyczka.getKwota();
    }
    public void usunPozyczke(String name){
        mapaPozyczek.remove(name);
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
        if (mapaPozyczek.isEmpty()){
            for ( Dobro dobro: mapaDobr.values()){
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
        for (Dobro dobro: this.mapaDobr.values()){
            this.dostepneSrodki -= dobro.getKoszt_utrzymania();
        }
        for (Pozyczka pozyczka : this.mapaPozyczek.values()){
            this.dostepneSrodki -= pozyczka.getRata();
            if (pozyczka.zaplacRate()){
                this.mapaPozyczek.remove(pozyczka.getName());
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
