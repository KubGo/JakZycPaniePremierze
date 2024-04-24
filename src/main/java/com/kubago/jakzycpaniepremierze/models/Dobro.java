package com.kubago.jakzycpaniepremierze.models;

import java.util.HashMap;

/**
 * Podstawowa klasa Dobra które można zakupić w grze
 */
public class Dobro {
    private String nazwa;
    private int cena;
    private int koszt_utrzymania;
    private int cena_sprzedazy;
    private boolean moznaSprzedac;
    private String[] bonusy;

    /**
     * @param nazwa Nazwa dobra
     * @param cena Cena zakupu dobra
     * @param koszt_utrzymania Miesięczny koszt utrzymania
     * @param cena_sprzedazy Cena za jaką w następnym miesiącu można sprzedać dobro
     * @param bonusy Bonusy do wyświetlenia jakie daje dobro
     */
    public Dobro(String nazwa, int cena, int koszt_utrzymania, int cena_sprzedazy, String[] bonusy) {
        this.cena = cena;
        this.koszt_utrzymania = koszt_utrzymania;
        this.cena_sprzedazy = cena_sprzedazy;
        this.moznaSprzedac = false;
        this.bonusy = bonusy;
    }

    /**
     * @param nazwa Nazwa dobra
     * @param cena Cena zakupu dobra
     * @param koszt_utrzymania Miesięczny koszt utrzymania
     * @param cena_sprzedazy Cena za jaką w następnym miesiącu można sprzedać dobro
     */
    public Dobro(String nazwa, int cena, int koszt_utrzymania, int cena_sprzedazy) {
        this(nazwa,cena, koszt_utrzymania, cena_sprzedazy, new String[]{});
    }

    /**
     * @return Miesięczny koszt utrzymania
     */
    public int getKoszt_utrzymania() {
        return koszt_utrzymania;
    }

    /**
     * @return Zwróć cenę sprzedaży dobra
     */
    public int getCena_sprzedazy() {
        return cena_sprzedazy;
    }

    /**
     * @return true, jeśli można sprzedać
     */
    public boolean isMoznaSprzedac() {
        return moznaSprzedac;
    }

    /**
     * Włącz możliwość sprzedaży dobra
     */
    public void setMoznaSprzedac() {
        this.moznaSprzedac = true;
    }

    /**
     * @return Cena dobra
     */
    public int getCena() {
        return cena;
    }

    /**
     * @return Nazwa dobra
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Klasa dobra luksusowego, które można sprzedać za różną cenę w zależności od wylosowanej liczby oczek
     * Dziedziczy z klasy Dobro
     */
    public class DobroLuksusowe extends Dobro{

        /**
         * Ceny sprzedaży w zależności od wyrzuconej liczby oczek
         */
        private HashMap<Integer, Integer> ceny_sprzedazy;

        /**
         * @param cena Cena zakupu dobra
         * @param ceny_sprzedazy Ceny sprzedaży w zależności od wyrzuconej liczby oczek
         */
        public DobroLuksusowe(String nazwa, int cena, HashMap<Integer, Integer> ceny_sprzedazy) {
            super(nazwa, cena, 0, 0);
            this.ceny_sprzedazy = ceny_sprzedazy;
        }
    }

    /**
     * Klasa dobra mieszkalnego które można dodatkowo wynająć
     */
    public class DobroMieszkalne extends Dobro{
        /**
         * Jeśli true, wynajmowane
         */
        private boolean wynajmowane;
        /**
         * Zysk z wynajmu mieszkania
         */
        private int zyskWynajmu;

        /**
         * @param cena Koszt zakupu
         * @param koszt_utrzymania Miesięczny koszt utrzymania
         * @param cena_sprzedazy Cena sprzedaży
         * @param bonusy Lista bonusów gwarantowanych przez dobro
         */
        public DobroMieszkalne(String nazwa, int cena, int koszt_utrzymania, int cena_sprzedazy, String[] bonusy) {
            super(nazwa, cena, koszt_utrzymania, cena_sprzedazy, bonusy);
            this.wynajmowane = false;
            this.zyskWynajmu = 0;
        }

        /**
         * @param cena Koszt zakupu
         * @param koszt_utrzymania Miesięczny koszt utrzymania
         * @param cena_sprzedazy Cena sprzedaży
         * @param bonusy Lista bonusów gwarantowanych przez dobro
         * @param wynajmowane true, jeśli mieszkanie wynajmowane
         * @param zyskWynajmu Zysk związany z wynajmem
         */
        public DobroMieszkalne(String nazwa, int cena, int koszt_utrzymania, int cena_sprzedazy, String[] bonusy, boolean wynajmowane, int zyskWynajmu) {
            super(nazwa, cena, koszt_utrzymania, cena_sprzedazy, bonusy);
            this.wynajmowane = wynajmowane;
            this.zyskWynajmu = zyskWynajmu;
        }

        /**
         * @return Koszt utrzymania pomniejszony o zwyski z wynajmu, jeśli takie są
         */
        @Override
        public int getKoszt_utrzymania() {
            if (wynajmowane){
                return super.getKoszt_utrzymania() - this.zyskWynajmu;
            }
            return super.getKoszt_utrzymania();
        }

        /**
         * Pozbądź się najemcy i ustaw zysk na zero
         */
        public void usunNajemce(){
            this.wynajmowane = false;
            this.zyskWynajmu = 0;
        }
    }
}
