package test;

import org.junit.jupiter.api.Test;
import spaceship.Item;
import spaceship.Rocket;
import spaceship.U1;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    @Test
    void launch() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertTrue(rocket.launch());
    }

    @Test
    void land() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertTrue(rocket.land());
    }

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

    @Test
    void getWeight() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertEquals(5, rocket.getWeight());
    }

    @Test
    void getNbrPeople() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertEquals(2, rocket.getNbrPeople());
    }

    @Test
    void getMaxWeight() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertEquals(20, rocket.getMaxWeight());
    }

    @Test
    void getMinWeight() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        assertEquals(4, rocket.getMinWeight());
    }

    @Test
    void toStringTest() {
        Rocket rocket = new Rocket(5, 4, 20, 10, 2);
        String expected =  "Rocket{" +
                        "weight=" + 5 +
                        ", cost=" + 10 +
                        ", nbrPeople=" + 2 + "}";
        assertEquals(expected, rocket.toString());
    }



}


