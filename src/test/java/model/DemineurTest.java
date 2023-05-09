package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DeminCase;

import static org.junit.jupiter.api.Assertions.*;

class DemineurTest {

    Demineur demineur;

    @BeforeEach
    public void initEach() {
        //Given
        demineur = new Demineur();
    }

    @Test
    void setEtGetnDrapeau() {
        //When
        demineur.setnDrapeau(8);

        //Then
        assertEquals(8, demineur.getnDrapeau());
    }

    @Test
    void setEtGetnMines() {
        //When
        demineur.setnMines(80);

        //Then
        assertEquals(80, demineur.getnMines());
    }

    @Test
    void setEtGetLARGEUR() {
        //When
        demineur.setLARGEUR(20);

        //Then
        assertEquals(20, demineur.getLARGEUR());
    }

    @Test
    void setEtGetHAUTEUR() {
        //When
        demineur.setHAUTEUR(20);

        //Then
        assertEquals(20, demineur.getHAUTEUR());
    }

    @Test
    void setEtGetnCases() {
        //When
        demineur.setnCases(100);

        //Then
        assertEquals(100, demineur.getnCases());
    }

    @Test
    void setEtGetJeux() {
        //Given
        DeminCase[][] jeux = new DeminCase[20][25];

        //When
        demineur.setJeux(jeux);

        //Then
        assertEquals(jeux, demineur.getJeux());
    }

    @Test
    void setEtGetMines() {
        //When
        demineur.setMines("0");

        //Then
        assertEquals("0", demineur.getMines());
    }

}