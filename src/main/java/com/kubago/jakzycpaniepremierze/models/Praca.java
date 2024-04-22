package com.kubago.jakzycpaniepremierze.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
enum Prace {
    CELEBRYTA,
    POLITYK,
    NAUCZYCIEL
}
public class Praca {
    String nazwa;
    private int[] zarobki;
    private int poziom;
    private String[] bonusy;

    public Praca(String nazwa, int[] zarobki, int poziom, String... bonusy) {
        this.zarobki = zarobki;
        this.poziom = poziom;
        this.bonusy = Objects.requireNonNullElseGet(bonusy, () -> new String[]{""});
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int[] getZarobki() {
        return zarobki;
    }

    public void setZarobki(int[] zarobki) {
        this.zarobki = zarobki;
    }

    public int getPoziom() {
        return poziom;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public String[] getBonusy() {
        return bonusy;
    }

    public void setBonusy(String[] bonusy) {
        this.bonusy = bonusy;
    }

    private void awans(){
        if (getPoziom() > 2){
            return;
        }
        poziom += 1;
    }
    public int wyplata(){
        return getZarobki()[getPoziom() - 1];
    }

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
                return null;
            }
        }
    }
}

