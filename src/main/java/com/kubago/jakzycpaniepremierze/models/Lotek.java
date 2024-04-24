package com.kubago.jakzycpaniepremierze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa do przechowywania numerów do losowania lotka i jego logiki
 */
public class Lotek {

    /**
     * Numery postawione w lotku
     */
    private ArrayList<Integer> numery;

    /**
     * @param numery Lista numerów do stworzenia lotka
     */
    public Lotek(ArrayList<Integer> numery) {
        for (int numer: numery){
            if (numer > 24 || numer < 1){
                throw new IllegalArgumentException("Numery mogą być jedynie z zakresu 1 do 24");
            }
        }
        this.numery = numery;
    }

    /**
     * @param numer Numer do utworzenia pierwszego typu lotka
     */
    public Lotek(int numer){
        this(new ArrayList<>(List.of(numer)));
    }

    /**
     * @param numer Numer do dodania jako kolejny typ
     */
    public void dodajNumer(Integer numer){
        if (numer > 24 || numer < 1){
            throw new IllegalArgumentException("Numery mogą być jedynie z zakresu 1 do 24");
        } else if (this.numery.size() > 2) {
            throw new ArrayIndexOutOfBoundsException("Można wytypować tylko na 3 numery");
        }
        this.numery.add(numer);
    }

    /**
     * @param wygranyNumer Wygrany numer
     * @return true, jeśli wygrana
     */
    public Boolean sprawdzWygrana(int wygranyNumer){
        if (wygranyNumer > 24 || wygranyNumer < 1){
            throw new IllegalArgumentException("Numery mogą być jedynie z zakresu 1 do 24");
        }
        for (int numer : numery){
            if (wygranyNumer == numer){
                return true;
            }
        }
        return false;
    }
}
