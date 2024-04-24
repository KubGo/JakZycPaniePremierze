package com.kubago.jakzycpaniepremierze.models;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * @param name Nazwa postaci
     * @param path_to_image Ścieżka do zdjęcia
     */
    public Postac(String name, Path path_to_image) {
        this.name = name;
        this.path_to_image = path_to_image;
    }

    /**
     * @param praca Praca do podjęcia
     */
    public void setPraca(Praca praca) {
        this.praca = praca;
    }

    /**
     * Usuwa obecną prace i pozostawia na zasiłku dla bezrobotnych
     */
    public void strataPracy(){
        this.praca = praca.wybierz_prace(Prace.BEZROBOTNY);
    }

    /**
     * @param dobro Dobro do kupienia
     * @throws Exception Jeśli brak środków do kupienia dobra
     */
    public void kupDobro(Dobro dobro) throws Exception {
        if (this.dostepneSrodki < dobro.getCena()){
            throw new Exception("Nie można kupić. Niewystarczająca ilość środków.");
        }
        this.dostepneSrodki =- dobro.getCena();
        mapaDobr.put(dobro.getNazwa(), dobro);
    }

    /**
     * @param nazwa Sprzedaj to dobro
     */
    public void sprzedajDobro(String nazwa){
        if (!mapaDobr.containsKey(nazwa)){
            throw new IllegalArgumentException("Ta postać nie ma takiego dobra.");
        }
        Dobro dobro = mapaDobr.remove(nazwa);
        this.dostepneSrodki += dobro.getCena_sprzedazy();
    }

    /**
     * @param pozyczka Porzyczka do dodania postaci
     */
    public void dodajPozyczke(Pozyczka pozyczka){
        mapaPozyczek.put(pozyczka.getName(), pozyczka);
        this.dostepneSrodki += pozyczka.getKwota();
    }

    /**
     * @param name Usuń porzyczkę po jej spłacie
     */
    public void usunPozyczke(String name){
        mapaPozyczek.remove(name);
    }

    /**
     * @param numer Postaw numer w lotku
     */
    public void postawLotka(int numer){
        this.lotek.dodajNumer(numer);
    }

    /**
     * @param number Sprawdź lotka
     * @return true, jeśli wygrana
     */
    public boolean sprawdzLotka(int number){
        boolean result = lotek.sprawdzWygrana(number);
        lotek.reset();
        return result;
    }

    /**
     * @return true, jeśli przegrana
     */
    public boolean checkLoss(){
        return this.dostepneSrodki < 0;
    }

    /**
     * @return true, jeśli spełnia warunki wygranej
     */
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

    /**
     * Zapłać za dzienne wydatki
     */
    private void dzienneWydatki(){
        this.dostepneSrodki -= this.DZIENNY_WYDATEK;
    }

    /**
     * Uaktualnie stan konta na początek nowego miesiąca
     * @return true, jeśli porażka
     */
    private boolean uaktualnijSrodkiNaNowyMiesiac(){
        this.dostepneSrodki += praca.wyplata();
        for (Dobro dobro: this.mapaDobr.values()){
            this.dostepneSrodki -= dobro.getKoszt_utrzymania();
        }
        for (Pozyczka pozyczka : this.mapaPozyczek.values()){
            this.dostepneSrodki -= pozyczka.getRata();
            if (pozyczka.zaplacRate()){
                this.usunPozyczke(pozyczka.getName());
            }
        }
        return this.checkLoss();
    }

    /**
     * @param kwota Kwota dodana do dostępnych środkóœ
     */
    public void dodajKwote(int kwota){
        this.dostepneSrodki += kwota;
    }

    /**
     * @param kwota Kwota dodana do dostępnych środkóœ
     * @param n_razy Ile razy dodać tę kwotę
     */
    public void dodajKwote(int kwota, int n_razy){
        this.dostepneSrodki += (kwota * n_razy);
    }

    /**
     * @param kwota Kwota do odjęcia od środków
     * @return true, jeśli porażka
     */
    public boolean odejmijKwote(int kwota){
        this.dostepneSrodki -= kwota;
        return this.checkLoss();
    }
}
