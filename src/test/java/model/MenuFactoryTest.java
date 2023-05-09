package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuFactoryTest {

    MenuFactory menuFactory;

    @BeforeEach
    public void initEach() {
        //Given
        menuFactory = new MenuFactory();
    }

    @Test
    void setEtGetType() {
        //When
        menuFactory.setType(2);

        //Then
        assertEquals(2, menuFactory.getType());

    }

}