package model;

import view.Graphisme;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * C'est une case de jeux en partie autonome
 * Elle gère elle même certains evènements de la souris
 */

public class DeminCase {

    private int etat = 0; //0 = rien; 1==enfonc�e; 2=drapeau; 3=?; 4=boum; 5=mine; 6=erreur de drapeau
    private boolean mine = false; //Si il y a une mine
    private boolean selected = false; //case enfonc�e
    private boolean blocked = false; //bloqu�e
    private int chiffre = 0; //chiffre affich� s'il doit �tre affich�
    private view.Graphisme gr = null; //l'objet qui contient les graphismes. Il est indiqu� par setGraphisme(Graphisme)

    public DeminCase() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getChiffre() {
        return chiffre;
    }

    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }

    public Graphisme getGr() {
        return gr;
    }

    public void setGr(Graphisme gr) {
        this.gr = gr;
    }
}


//integrating javafx to a swing component sur le site oracle

