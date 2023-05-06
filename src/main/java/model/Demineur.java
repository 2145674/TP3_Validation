package model;

import view.DeminCase;

//le jeux...
public class Demineur extends MenuFactory {
    int nDrapeau = 0; //nombres de drapeaux posÃ©s
    private int nMines; //nombre total de mines
    private int LARGEUR; //nombre de cases selon la largeur
    private int HAUTEUR; //nombre de cases selon la hauteur
    private int nCases; //nombre de cases non dÃ©couvertes restantes
    DeminCase[][] jeux; //tableau des cases du jeux
    private String mines; //chaÃ®nes de caractÃ¨re qui contient la rÃ©partition des mines
    private int[][] casesSelectionnees = new int[8][2]; //reperages des cases selectionnees pour les deselectionnes lors du relachement de la sourie
    private int TYPE;


    public Demineur() {
    }

    public int getnDrapeau() {
        return nDrapeau;
    }

    public void setnDrapeau(int nDrapeau) {
        this.nDrapeau = nDrapeau;
    }

    public int getnMines() {
        return nMines;
    }

    public void setnMines(int nMines) {
        this.nMines = nMines;
    }

    public int getLARGEUR() {
        return LARGEUR;
    }

    public void setLARGEUR(int LARGEUR) {
        this.LARGEUR = LARGEUR;
    }

    public int getHAUTEUR() {
        return HAUTEUR;
    }

    public void setHAUTEUR(int HAUTEUR) {
        this.HAUTEUR = HAUTEUR;
    }

    public int getnCases() {
        return nCases;
    }

    public void setnCases(int nCases) {
        this.nCases = nCases;
    }

    public DeminCase[][] getJeux() {
        return jeux;
    }

    public void setJeux(DeminCase[][] jeux) {
        this.jeux = jeux;
    }

    public String getMines() {
        return mines;
    }

    public void setMines(String mines) {
        this.mines = mines;
    }

    public int[][] getCasesSelectionnees() {
        return casesSelectionnees;
    }

    public void setCasesSelectionnees(int[][] casesSelectionnees) {
        this.casesSelectionnees = casesSelectionnees;
    }

}

