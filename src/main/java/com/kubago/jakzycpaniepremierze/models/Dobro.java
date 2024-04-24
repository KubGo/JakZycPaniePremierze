package com.kubago.jakzycpaniepremierze.models;

/**
 * Podstawowa klasa Dobra które można zakupić w grze
 */
public class Dobro {
    private int cena;
    private int koszt_utrzymania;
    private int cena_sprzedazy;
    private boolean moznaSprzedac;
    private String[] bonusy;

    /**
     * @param cena Cena zakupu dobra
     * @param koszt_utrzymania Miesięczny koszt utrzymania
     * @param cena_sprzedazy Cena za jaką w następnym miesiącu można sprzedać dobro
     * @param bonusy Bonusy do wyświetlenia jakie daje dobro
     */
    public Dobro(int cena, int koszt_utrzymania, int cena_sprzedazy, String[] bonusy) {
        this.cena = cena;
        this.koszt_utrzymania = koszt_utrzymania;
        this.cena_sprzedazy = cena_sprzedazy;
        this.moznaSprzedac = false;
        this.bonusy = bonusy;
    }

    /**
     * @param cena Cena zakupu dobra
     * @param koszt_utrzymania Miesięczny koszt utrzymania
     * @param cena_sprzedazy Cena za jaką w następnym miesiącu można sprzedać dobro
     */
    public Dobro(int cena, int koszt_utrzymania, int cena_sprzedazy) {
        this(cena, koszt_utrzymania, cena_sprzedazy, new String[]{});
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
}
