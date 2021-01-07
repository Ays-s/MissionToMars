package test;

import org.junit.jupiter.api.Test;
import spaceship.Item;
import spaceship.U1;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void getWeight() {
        Item item = new Item("nom", 5, 0, 0);
        assertEquals(5, item.getWeight());
    }

    @Test
    void getCost() {
        Item item = new Item("nom", 0, 5, 0);
        assertEquals(5, item.getCost());
    }

    @Test
    void getName() {
        Item item = new Item("nom", 0, 0, 0);
        assertEquals("nom", item.getName());
    }

    @Test
    void hetNbrPeople() {
        Item item = new Item("nom", 0, 0, 5);
        assertEquals(5, item.getNbrPeople());
    }


    @Test
    void setWeight() {
        Item item = new Item("nom", 0, 0, 0);
        item.setWeight(10);
        assertEquals(10, item.getWeight());
    }

    @Test
    void setCost() {
        Item item = new Item("nom", 0, 0, 0);
        item.setCost(10);
        assertEquals(10, item.getCost());
    }

    @Test
    void setName() {
        Item item = new Item("nom", 0, 0, 0);
        item.setName("new");
        assertEquals("new", item.getName());
    }

    @Test
    void setNbrPeople() {
        Item item = new Item("nom", 0, 0, 0);
        item.setNbrPeople(10);
        assertEquals(10, item.getNbrPeople());
    }
    @Test
    void toStringTest() {
        Item item = new Item("nom", 0, 0, 0);
        String excpected = "name='" + "nom" + '\'' +
                ", weight=" + 0 +
                ", cost=" + 0 +
                ", nbrPeople=" + 0;
        assertEquals(excpected, item.toString());
    }

}
