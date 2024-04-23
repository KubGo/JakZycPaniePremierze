package com.kubago.jakzycpaniepremierze.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;



/**
 * Enumeracja zawierające możliwe do wybrania prace
 *  @author Jakub Golc
 *  @version 1.0
 *
 */
enum Prace {

    CELEBRYTA,
    POLITYK,
    NAUCZYCIEL
}

/**
 * Klasa pracy umożliwiające wybranie danej pracy i wraz z odpowiednimi zarobkami i bonusami do wyświetlniea
 */
public class Praca {
    /** Nazwa pracy */
    String nazwa;
    /** Lista z zarobkami dla poszczególnych awansów */
    private int[] zarobki;
    /** Poziom awansu pracy */
    private int poziom;
    /** Dodadkowe bonusy zapewniane przez klasę */
    private String[] bonusy;

    /**
     * Tworzy nowy obiekt Praca z podanymi wartościami
     * @param nazwa Nazwa pracy
     * @param zarobki Lista z zarobkami dla poszczególnych awansów
     * @param poziom Poziom awansu
     * @param bonusy Dodadkowe bonusy zapewniane przez klasę
     */
    private Praca(String nazwa, int[] zarobki, int poziom, String... bonusy) {
        this.zarobki = zarobki;
        this.poziom = poziom;
        this.bonusy = Objects.requireNonNullElseGet(bonusy, () -> new String[]{""});
    }

    /**
     * Zraca nazwę pracy
     * @return Nazwę pracy
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustaw nazwę pracy
     * @param nazwa Nazwa pracy do ustawienia
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca listę zarobków
     * @return Lista zarobków
     */
    public int[] getZarobki() {
        return zarobki;
    }

    /**
     * Ustaw listę zarobków
     * @param zarobki Lista zarobków
     */
    public void setZarobki(int[] zarobki) {
        this.zarobki = zarobki;
    }

    /**
     * Zwraca poziom awansu
     * @return Poziom awansu
     */
    public int getPoziom() {
        return poziom;
    }

    /**
     * Ustaw poziom awansu
     * @param poziom Poziom awansu
     */
    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    /**
     * Zwraca listę bunusów
     * @return Lista bonusów
     */
    public String[] getBonusy() {
        return bonusy;
    }

    /**
     * Ustaw listę bonusów
     * @param bonusy Lista bonusów
     */
    public void setBonusy(String[] bonusy) {
        this.bonusy = bonusy;
    }

    /**
     * Awansuj na kolejny poziom pracy, maksymanie 3 poziom
     */
    private void awans(){
        if (getPoziom() > 2){
            return;
        }
        poziom += 1;
    }

    /**
     * Zwraca kwotę miesięcznej wypłaty
     * @return Wypłata za dany miesiąc
     */
    public int wyplata(){
        return getZarobki()[getPoziom() - 1];
    }

    /**
     * Stwórz pracę z wybranej z enumeracji dostępnych prac
     * @param prace Praca
     * @return Pracę na 1 poziomie awansu
     */
    public Praca wybierz_prace(Prace prace) {
        switch (prace) {
            case CELEBRYTA -> {
                return new Praca("Celebtryta",
                        new int[]{500, 600, 800},
                        1);
            }
            case POLITYK -> {
                return new Praca("Polityk",
                        new int[]{400, 500, 700},
                        1,
                        "Nie działa na Ciebie karta redukcji etatów i upadku pracodawcy");
            }
            case NAUCZYCIEL -> {
                return new Praca("Nauczyciel",
                        new int[]{300, 400, 500},
                        1);
            }default -> {
                System.out.println("Nie ma takiej pracy");
                throw new IllegalArgumentException();
            }
        }
    }
}

