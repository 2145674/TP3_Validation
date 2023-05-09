package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Graphisme;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;

import static org.junit.jupiter.api.Assertions.*;

class DeminCaseTest {

    DeminCase deminCase;

    @BeforeEach
    public void initEach() {
        //Given
        deminCase = new DeminCase();
    }

    @Test
    void setEtGetEtat() {
        //When
        deminCase.setEtat(4);

        //then
        assertEquals(4, deminCase.getEtat());
    }

    @Test
    void setEtIsMine() {
        //When
        deminCase.setMine(true);

        //then
        assertTrue(deminCase.isMine());
    }

    @Test
    void setEtIsSelected() {
        //When
        deminCase.setSelected(true);

        //then
        assertTrue(deminCase.isSelected());
    }

    @Test
    void setEtIsBlocked() {
        //When
        deminCase.setBlocked(true);

        //then
        assertTrue(deminCase.isBlocked());
    }

    @Test
    void setEtGetChiffre() {
        //When
        deminCase.setChiffre(8);

        //then
        assertEquals(8, deminCase.getChiffre());
    }

    @Test
    void setEtGetGr() {
        Graphisme graphisme = new Graphisme();

        //When
        deminCase.setGr(graphisme);

        //then
        assertEquals(graphisme, deminCase.getGr());
    }

}