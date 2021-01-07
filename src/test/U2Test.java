package test;

import org.junit.jupiter.api.Test;
import spaceship.ExponentialProbability;
import spaceship.Item;
import spaceship.U1;
import spaceship.U2;

import static org.junit.jupiter.api.Assertions.*;

class U2Test {

    @Test
    void testProba(){
        double lambda = 0.5;
        U2 rocket = new U2(new ExponentialProbability(lambda));
        assertNotNull(rocket);
    }

    @Test
    void getCost() {
        U1 rocket = new U1();
        assertEquals(100, rocket.getCost());
    }

    @Test
    void getProbabilityDistribution() {
        double lambda = 0.5;
        ExponentialProbability proba = new ExponentialProbability(lambda);
        U2 rocket = new U2(proba);
        assertEquals(proba, rocket.getProbabilityDistribution());
    }

    @Test
    void launch() {
        int succesWithItem;
        int nbrLoop = 100000;

        System.out.println("Test launch() U2:");
        for (int weight = 0; weight <= 11; weight++) {
            Item item = new Item("Item 2", weight, 0, 0);
            succesWithItem = 0;
            for (int i = 0; i < nbrLoop; i++) {
                U2 rocket = new U2();
                rocket.carry(item);
                if (rocket.launch()) {
                    succesWithItem += 1;
                }
            }
            System.out.println("Résultats avec un poids supplémentaire de : " + weight + "\n" + succesWithItem + " - rate : " + (float) succesWithItem / nbrLoop);

            assertNotEquals(0, succesWithItem);
            assertNotEquals(nbrLoop, succesWithItem);
            boolean answer1 = (succesWithItem > nbrLoop);
            assertFalse(answer1);
            boolean answer2 = (succesWithItem < 0);
            assertFalse(answer2);
        }

    }

    @Test
    void land() {
        int succesWithItem;
        int nbrLoop = 100000;

        System.out.println("\nTest land() U2:");
        for (int weight = 0; weight <= 11; weight++) {
            Item item = new Item("Item 2", weight, 0, 0);
            succesWithItem = 0;
            for (int i = 0; i < nbrLoop; i++) {
                U2 rocket = new U2();
                rocket.carry(item);
                if (rocket.land()) {
                    succesWithItem += 1;
                }
            }
            System.out.println("Résultats avec un poids supplémentaire de : " + weight + "\n" + succesWithItem + " - rate : " + (float) succesWithItem / nbrLoop);

            assertNotEquals(0, succesWithItem);
            assertNotEquals(nbrLoop, succesWithItem);
            boolean answer1 = (succesWithItem > nbrLoop);
            assertFalse(answer1);
            boolean answer2 = (succesWithItem < 0);
            assertFalse(answer2);
        }

    }
}
