package test;

import controller.Simulation;
import org.junit.jupiter.api.Test;
import spaceship.Item;
import spaceship.U1;
import spaceship.U2;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    @Test
    void loadItems(){
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        assertNotNull(listItemsPhase1);
        assertNotNull(listItemsPhase1.get(0));
        assertEquals("test" ,listItemsPhase1.get(0).getName());
        assertEquals(1 ,listItemsPhase1.get(0).getWeight());
        assertEquals(2 ,listItemsPhase1.get(0).getCost());
        assertEquals(3,listItemsPhase1.get(0).getNbrPeople());
    }

    @Test
    void loadU1(){
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        ArrayList<U1> listU1 = Simulation.loadU1(listItemsPhase1);
        assertNotNull(listU1);
        assertNotNull(listU1.get(0));
        assertEquals(11 ,listU1.get(0).getWeight());
        assertEquals(102 ,listU1.get(0).getCost());
        assertEquals(6 ,listU1.get(0).getNbrPeople());
    }

    @Test
    void loadU1PeopleSafe(){
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        ArrayList<U1> listU1 = Simulation.loadU1PeopleSafe(listItemsPhase1);
        assertNotNull(listU1);
        assertNotNull(listU1.get(0));
        assertEquals(11 ,listU1.get(0).getWeight());
        assertEquals(102 ,listU1.get(0).getCost());
        assertEquals(6 ,listU1.get(0).getNbrPeople());
    }

    @Test
    void loadU2(){
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        ArrayList<U2> listU2 = Simulation.loadU2(listItemsPhase1);
        assertNotNull(listU2);
        assertNotNull(listU2.get(0));
        assertEquals(19 ,listU2.get(0).getWeight());
        assertEquals(122 ,listU2.get(0).getCost());
        assertEquals(7 ,listU2.get(0).getNbrPeople());
    }

    @Test
    void loadU2PeopleSafe(){
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        ArrayList<U2> listU2 = Simulation.loadU2PeopleSafe(listItemsPhase1);
        assertNotNull(listU2);
        assertNotNull(listU2.get(0));
        assertEquals(19 ,listU2.get(0).getWeight());
        assertEquals(122 ,listU2.get(0).getCost());
        assertEquals(7 ,listU2.get(0).getNbrPeople());
    }

    @Test
    void number_of_simulation(){
        double epsilon = 0.1;
        float alpha = 0.9f;
        assertEquals(8,
                Simulation.number_of_simulation(new U1(),  epsilon, alpha ));
    }

    @Test
    void runSimulation(){
        Simulation simu = new Simulation();
        int[] result;
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-test.txt");
        ArrayList<U2> listU2 = Simulation.loadU2PeopleSafe(listItemsPhase1);
        result = simu.runSimulation(listU2);
        assertTrue(result[0]>100);
        assertFalse(result[1]<0);
    }
}


