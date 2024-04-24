package com.kubago.jakzycpaniepremierze.models;

/**
 * Klasa Pożyczki którą należy spłacać co miesiąc
 */
public class Pozyczka {
    private static int index;
    private String name;
    /**
     * Kwota pożyczki
     */
    private int kwota;
    /**
     * Koszt raty
     */
    private int rata;
    /**
     * Ilość rat do spłaty
     */
    private int iloscRat;

    /**
     * @param kwota Kwota pożyczki
     * @param rata Koszt raty
     * @param iloscRat Ilość rat do spłaty
     */
    public Pozyczka(int kwota, int rata, int iloscRat) {
        this.name = "Pożyczka" + index++;
        this.kwota = kwota;
        this.rata = rata;
        this.iloscRat = iloscRat;
    }

    /**
     * @return true, jeśli cała pożyczka została spłacona
     */
    public Boolean zaplacRate(){
        this.kwota -= this.rata;
        this.iloscRat--;
        return this.iloscRat == 0;
    }

    /**
     * @param iloscRat Ilość rat do spłaty
     * @return true, jeśli cała pożyczka została spłacona
     */
    public Boolean zaplacRate(int iloscRat){
        int pozostaleRaty = this.iloscRat - iloscRat;
        if (pozostaleRaty < 0){
            throw new IllegalArgumentException("Nie można spłacić więcej rat niż pozostało do spłaty");
        }
        this.iloscRat = pozostaleRaty;
        this.kwota -= this.rata * iloscRat;
        return iloscRat == 0;
    }

    /**
     * @return Kwota pożyczki
     */
    public int getKwota() {
        return kwota;
    }

    /**
     * @return Ilość rat pozostałych do spłaty
     */
    public int getIloscRat() {
        return iloscRat;
    }

    /**
     * @return Koszt spłaty pojedyńczej raty
     */
    public int getRata() {
        return rata;
    }
    public String getName(){
        return this.name;
    }
}
