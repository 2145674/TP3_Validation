package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTest {

    Segment segment;

    @BeforeEach
    public void initEach() {
        //Given
        segment = new Segment();
    }

    @Test
    void setEtGetChiffres() {
        //Given
        boolean[][] chiffres = {
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

        //When
        segment.setChiffres(chiffres);

        //Then
        assertEquals(chiffres, segment.getChiffres());

    }

    @Test
    void etantDonneLeTableauDeBoolean_quandUneCaseEstAppelee_alorsElleRetourneLaBonneValeurBoolean(){
        //Given
        boolean[][] chiffres = {
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

        //When
        segment.setChiffres(chiffres);

        //Then
        assertTrue(segment.getChiffres()[0][0]);
        assertFalse(segment.getChiffres()[1][0]);
    }

    @Test
    void setEtGetValeur() {
        //When
        segment.setValeur(80);

        //Then
        assertEquals(80, segment.getValeur());
    }

}