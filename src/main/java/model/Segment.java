package model;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


//Afficheur 3 chiffres à 7 segments chacun;
//utilisé pour le temps et les mines
public class Segment {
    /*NUMEROS DES SEGMENTS
     2___
    1|   |3
     7---
    4|___|6
       5
    */
    private boolean[][] chiffres = {
            //TABLE DE VERITE DES SEGMENTS POUR CHAQUE CHIFFRE
            {true, true, true, true, true, true, false},//0
            {false, false, true, false, false, true, false},//1
            {false, true, true, true, true, false, true},//2
            {false, true, true, false, true, true, true},//3
            {true, false, true, false, false, true, true},//4
            {true, true, false, false, true, true, true},//5
            {true, true, false, true, true, true, true},//6
            {false, true, true, false, false, true, false},//7
            {true, true, true, true, true, true, true},//8
            {true, true, true, false, true, true, true}//9
    };
    private int valeur;

    public Segment() {
    }

    public boolean[][] getChiffres() {
        return chiffres;
    }

    public void setChiffres(boolean[][] chiffres) {
        this.chiffres = chiffres;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
