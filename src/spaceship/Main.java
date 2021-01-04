package spaceship;


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

        System.out.println("\nPhase 1 :");
        int budget1 = sim.runSimulation(list_Phase1);
        System.out.println("Budget total = "+ budget1);

        System.out.println("\nPhase 2 :");
        int budget2 = sim.runSimulation(list_Phase2);
        System.out.println("Budget total = "+ budget2);

        System.out.println("\n\tOptimisation sécurité :");
        System.out.println("\nListe fusées U2 pour la phase 1 :");
        printRocket(list_Phase1_safe);
        System.out.println("\nListe fusées U2 pour la phase 2 :");
        printRocket(list_Phase2_safe);

        System.out.println("\nPhase 1 :");
        int budget1_safe = sim.runSimulation(list_Phase1_safe);
        System.out.println("Budget total = "+ budget1_safe);

        System.out.println("\nPhase 2 :");
        int budget2_safe = sim.runSimulation(list_Phase2_safe);
        System.out.println("Budget total = "+ budget2_safe);

    }

    public static <U extends Rocket> void printRocket(ArrayList<U> rocketArrayList){
        for (U r: rocketArrayList){
            System.out.println(r);
            //System.out.println("\t- " + "Weight = "+r.getWeight() + ", Cost = "+r.getCost() + ", NbrPeople = "+r.getNbrPeople());
        }

    }
}
