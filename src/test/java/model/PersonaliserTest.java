package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Demineur;

import static org.junit.jupiter.api.Assertions.*;

class PersonaliserTest {

    Personaliser personaliser;

    @BeforeEach
    public void initEach() {
        //Given
        personaliser = new Personaliser();
    }

    @Test
    void setEtGetH() {
        //When
        personaliser.setH(50);

        //Then
        assertEquals(50, personaliser.getH());
    }

    @Test
    void setEtGetL() {
        //When
        personaliser.setL(50);

        //Then
        assertEquals(50, personaliser.getL());
    }

    @Test
    void setEtGetM() {
        //When
        personaliser.setL(80);

        //Then
        assertEquals(80, personaliser.getL());
    }

    @Test
    void setEtGetDemin() {
        //Given
        Demineur demineur = new Demineur(20, 30, 80, 3);

        //When
        personaliser.setDemin(demineur);

        //Then
        assertEquals(demineur, personaliser.getDemin());

    }

}