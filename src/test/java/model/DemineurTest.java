package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemineurTest {

    Demineur demineur = new Demineur();

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
        demineur.setnDrapeau(8);

        //Then
        assertEquals(8, demineur.getnDrapeau());

    }

    @Test
    void setEtGetLARGEUR() {
    }

    @Test
    void setEtGetHAUTEUR() {
    }

    @Test
    void setEtGetnCases() {
    }

    @Test
    void setEtGetJeux() {
    }

    @Test
    void setEtGetMines() {
    }

}