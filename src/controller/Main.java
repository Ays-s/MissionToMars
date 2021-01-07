package controller;


import spaceship.Item;
import spaceship.Rocket;
import spaceship.U2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Simulation sim = new Simulation();

        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-1.txt");
        ArrayList<Item> listItemsPhase2 = Simulation.loadItems("src/ressources/Phase-2.txt");


        System.out.println("Liste d'items 1 :");
        System.out.println(listItemsPhase1);

        System.out.println("Liste d'items 2 :");
        System.out.println(listItemsPhase2);


        ArrayList<U2> list_Phase1 = Simulation.loadU2(listItemsPhase1);
        ArrayList<U2> list_Phase1_safe = Simulation.loadU2PeopleSafe(listItemsPhase1);

        ArrayList<U2> list_Phase2 = Simulation.loadU2(listItemsPhase2);
        ArrayList<U2> list_Phase2_safe = Simulation.loadU2PeopleSafe(listItemsPhase2);

        System.out.println("\n\tOptimisation des couts :");
        System.out.println("\nListe fusées U2 pour la phase 1 :");
        printRocket(list_Phase1);
        System.out.println("\nListe fusées U2 pour la phase 2 :");
        printRocket(list_Phase2);

        int budget1;
        int budget2;

        System.out.println("\nPhase 1 :");
        int[] resultatSimulation  = sim.runSimulation(list_Phase1);
        budget1 = resultatSimulation[0];

        System.out.println("Budget total = "+ budget1);

        System.out.println("\nPhase 2 :");
        int[] resultatSimulation2  = sim.runSimulation(list_Phase1);
        budget2 = resultatSimulation2[0];
        System.out.println("Budget total = "+ budget2);

        System.out.println("\n\tOptimisation sécurité :");
        System.out.println("\nListe fusées U2 pour la phase 1 :");
        printRocket(list_Phase1_safe);
        System.out.println("\nListe fusées U2 pour la phase 2 :");
        printRocket(list_Phase2_safe);

        System.out.println("\nPhase 1 :");
        int budget1_safe;
        int budget2_safe;

        int[] resultatSimulation_safe = sim.runSimulation(list_Phase1_safe);
        budget1_safe = resultatSimulation_safe[0];
        System.out.println("Budget total = "+ budget1_safe);

        int[] resultatSimulation_safe2 = sim.runSimulation(list_Phase1_safe);
        budget2_safe = resultatSimulation_safe2[0];
        System.out.println("Budget total = "+ budget2_safe);

    }

    public static <U extends Rocket> void printRocket(ArrayList<U> rocketArrayList){
        for (U r: rocketArrayList){
            System.out.println(r);
            //System.out.println("\t- " + "Weight = "+r.getWeight() + ", Cost = "+r.getCost() + ", NbrPeople = "+r.getNbrPeople());
        }

    }
}
