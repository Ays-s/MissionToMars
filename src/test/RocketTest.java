package test;

import org.junit.jupiter.api.Test;
import spaceship.Item;
import spaceship.U1;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    @Test
    void canCarry() {
        U1 rocket = new U1();
        Item item1 = new Item("Item 1", 2, 0, 0);
        Item item2 = new Item("Item 2", 10, 0, 0);

        boolean answer1 = rocket.canCarry(item1);
        assertTrue(answer1);

        boolean answer2 = rocket.canCarry(item2);
        assertFalse(answer2);
    }


    @Test
    void carry(){
        U1 rocket = new U1();
        Item item1 = new Item("Item 1", 2, 0, 0);
        Item item2 = new Item("Item 2", 5,0 , 0);

        rocket.carry(item1);
        assertEquals(12, rocket.getWeight());

        rocket.carry(item2);
        assertEquals(17, rocket.getWeight());
    }

}


