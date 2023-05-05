package model;

import view.Demineur;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//dialogue pour personnaliser la taille
public class Personaliser {

    int H, L, M;
    view.Demineur demin;

    public Personaliser() {
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public Demineur getDemin() {
        return demin;
    }

    public void setDemin(Demineur demin) {
        this.demin = demin;
    }
}