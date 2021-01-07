package spaceship;



import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
//import java.util.List;
//import java.util.Scanner;

public class Simulation {

    public static ArrayList<Item> loadItems(String itemFilePath) {
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        try {
            File file = new File(itemFilePath);
            try ( //try with resources pour éviter un finally avec 3 close
                  FileInputStream fileIS = new FileInputStream(file); //fileInputStream
                  InputStreamReader fileISR = new InputStreamReader(fileIS);//fileInputStreamReader
                  BufferedReader fileBR = new BufferedReader(fileISR)) {
                String extractedString = fileBR.readLine();
                while (extractedString != null) {
                    var item = strtoitem(extractedString);
                    itemArrayList.add(item); // on l'item extrait
                    extractedString = fileBR.readLine();
                }
            } catch (FileNotFoundException notFoundException){
                System.out.println("Le fichier \""+itemFilePath+"\" n'existe pas.");
                notFoundException.printStackTrace();
            } catch (SecurityException securityException){
                System.out.println("Accès en écriture impossible sur le fichier \""+itemFilePath+"\".");
                securityException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (NullPointerException pointerException){ // si problème à l'ouverture de file
            pointerException.printStackTrace();
        }
        return itemArrayList;
    }

    private static Item strtoitem(String extractedString) {
        String itemStr = null;
        int itemWeight = 0;
        int itemCost = 0;
        int itemNbrPeople = 0;
        StringTokenizer st = new StringTokenizer(extractedString, "=",false);
        try{
            itemStr = st.nextToken();
            String itemWeightStr = st.nextToken();
            String itemCostStr = st.nextToken();
            String itemNbrPeopleStr = st.nextToken();
            try{
                itemWeight = Integer.parseInt(itemWeightStr)/1000; //on divise par 1000 pour se ramerner en tonnes, comme les attributs weight
                itemCost = Integer.parseInt(itemCostStr);
                itemNbrPeople = Integer.parseInt(itemNbrPeopleStr);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        catch (NoSuchElementException noSuchElementException){ // if there are no more tokens in this tokenizer's string
            noSuchElementException.printStackTrace();
        }
        return new Item(itemStr, itemWeight, itemCost, itemNbrPeople);
    }


    public static ArrayList<U1> loadU1(ArrayList<Item> itemArrayList, Probability probabilityDistribution){
        ArrayList<U1> u1ArrayList = new ArrayList<U1>();
        U1 currentU1 = new U1(probabilityDistribution); // une fusée de type U1
        u1ArrayList.add(currentU1);
        boolean carried = false;
        for (Item i: itemArrayList) { //pour tout objet de la liste itemArrayList
            carried = false;
            for (U1 u: u1ArrayList) { // on regarde dans les fusée qu'on a déja
                if (u.canCarry(i)) { //si une fusée peut accueillir l'item i
                    u.carry(i); // on carry
                    carried = true;
                    break;
                }
            }
            if (!carried){
                if (i.getWeight() <= (U1.initialMaxWeight - U1.initialWeight)) {
                    // si l'objet n'est pas trop gros pour une nouvelle fusée
                    // on en crée une nouvelle fusée
                    currentU1 = new U1(probabilityDistribution);
                    currentU1.carry(i);
                    u1ArrayList.add(currentU1);
                }
                else { // sinon on abandonne l'objet
                    System.out.println("L'objet " + i.toString() + " ne peut être mis dans une fusée.");
                }
            }
        }
        return u1ArrayList;
    }

    public static ArrayList<U1> loadU1(ArrayList<Item> itemArrayList){
        return loadU1(itemArrayList, new LinearProbability());
    }

    public static ArrayList<U1> loadU1PeopleSafe (ArrayList<Item> itemArrayList, Probability probabilityDistribution){
        ArrayList<U1> u1ArrayList = new ArrayList<U1>();
        U1 currentU1 = new U1(probabilityDistribution); // une fusée de type U1
        u1ArrayList.add(currentU1);
        boolean carried = false;
        for (Item i: itemArrayList) { //pour tout objet de la liste itemArrayList
            carried = false;
            if (i.getNbrPeople()>0 && i.getWeight() <= (U1.initialMaxWeight - U1.initialWeight)){
                currentU1 = new U1(probabilityDistribution);
                currentU1.carry(i);
                u1ArrayList.add(currentU1);
                carried = true;
            }
            if (!carried) {
                for (U1 u : u1ArrayList) { // on regarde dans les fusée qu'on a déja
                    if (u.getNbrPeople() == U1.initialNbrPeople && u.canCarry(i)) { //si une fusée peut accueillir l'item i
                        u.carry(i); // on carry
                        carried = true;
                        break;
                    }
                }
                if (!carried && i.getWeight() <= (U1.initialMaxWeight - U1.initialWeight)) {
                    // si l'objet n'est pas trop gros pour une nouvelle fusée
                    // on en crée une nouvelle fusée
                    currentU1 = new U1(probabilityDistribution);
                    currentU1.carry(i);
                    u1ArrayList.add(currentU1);
                    carried = true;
                }
            }
            if (!carried) { // sinon on abandonne l'objet
                System.out.println("L'objet " + i.toString() + " ne peut être mis dans une fusée.");
            }
        }
        return u1ArrayList;
    }

    public static ArrayList<U1> loadU1PeopleSafe (ArrayList<Item> itemArrayList){
        return loadU1PeopleSafe(itemArrayList, new LinearProbability());
    }

    public static ArrayList<U2> loadU2(ArrayList<Item> itemArrayList, Probability probabilityDistribution){
        ArrayList<U2> u2ArrayList = new ArrayList<U2>();
        U2 currentU2 = new U2(probabilityDistribution); // une fusée de type U1
        u2ArrayList.add(currentU2);
        boolean carried = false;
        for (Item i: itemArrayList) { //pour tout objet de la liste itemArrayList
            carried = false;
            for (U2 u : u2ArrayList) { // on regarde dans les fusée qu'on a déja
                if (u.canCarry(i)) { //si une fusée peut accueillir l'item i
                    u.carry(i); // on carry
                    carried = true;
                    break;
                }
            }
            if (!carried) {
                if (i.getWeight() <= (U2.initialMaxWeight - U2.initialWeight)) {
                    // si l'objet n'est pas trop gros pour une nouvelle fusée
                    // on en crée une nouvelle fusée
                    currentU2 = new U2(probabilityDistribution);
                    currentU2.carry(i);
                    u2ArrayList.add(currentU2);
                } else { // sinon on abandonne l'objet
                    System.out.println("L'objet " + i.toString() + " ne peut être mis dans une fusée.");
                }
            }
        }
        return u2ArrayList;
    }

    public static ArrayList<U2> loadU2(ArrayList<Item> itemArrayList){
        return loadU2(itemArrayList, new LinearProbability());
    }

    public static ArrayList<U2> loadU2PeopleSafe (ArrayList<Item> itemArrayList, Probability probabilityDistribution){
        ArrayList<U2> u2ArrayList = new ArrayList<U2>();
        U2 currentU2 = new U2(probabilityDistribution); // une fusée de type U1
        u2ArrayList.add(currentU2);
        boolean carried = false;
        for (Item i: itemArrayList) { //pour tout objet de la liste itemArrayList
            carried = false;
            if (i.getNbrPeople()>0 && i.getWeight() <= (U2.initialMaxWeight - U2.initialWeight)){
                currentU2 = new U2(probabilityDistribution);
                currentU2.carry(i);
                u2ArrayList.add(currentU2);
                carried = true;
            }
            if (!carried) {
                for (U2 u : u2ArrayList) { // on regarde dans les fusée qu'on a déja
                    if (u.getNbrPeople() == U2.initialNbrPeople && u.canCarry(i)) { //si une fusée peut accueillir l'item i
                        u.carry(i); // on carry
                        carried = true;
                        break;
                    }
                }
                if (!carried && i.getWeight() <= (U2.initialMaxWeight - U2.initialWeight)) {
                    // si l'objet n'est pas trop gros pour une nouvelle fusée
                    // on en crée une nouvelle fusée
                    currentU2 = new U2(probabilityDistribution);
                    currentU2.carry(i);
                    u2ArrayList.add(currentU2);
                    carried = true;
                }
            }
            if (!carried) { // sinon on abandonne l'objet
                System.out.println("L'objet " + i.toString() + " ne peut être mis dans une fusée.");
            }
        }
        return u2ArrayList;
    }

    public static ArrayList<U2> loadU2PeopleSafe (ArrayList<Item> itemArrayList){
        return loadU2PeopleSafe(itemArrayList, new LinearProbability()); // si pas de densité de proba donnée, par défaut on prend la linéaire
    }

    public static int number_of_simulation(U u, double epsilon, float alpha){
        /* Donne le nombre de simulations nécessaires pour que le nombre moyen d'explosions soit compris dans un
        * intervalle 2 epsilon, au seuil de alpha (en %)
        * Valable si n>30 => grand échantilllon */
        Probability uProbabilityDistribution = u.getProbabilityDistribution();
        double V = uProbabilityDistribution.getVariance();
        RealDistribution g = new NormalDistribution();
        double a_T = g.inverseCumulativeProbability((1+alpha)/2);
        return((int) Math.ceil(Math.pow(V*a_T/(2*epsilon),2)));
    }

    public <U extends Rocket> int[] runSimulation(ArrayList<U> rocketArrayList){
        int budget = 0;
        int nombreFuseeCrashee = 0;
        int nbrDead =0;
        String nbrDeadStr;
        String nbrFuseeCrashStr;
        for (U r: rocketArrayList){
            // surement moins efficace qu'un remove sur index mais plus sur car
            // la boucle itérable prend en compte le changement du nombre d'éléments
            boolean cond;
            do{
                budget += r.getCost();
                cond = !(r.launch() && r.land());
                if (cond){
                    nombreFuseeCrashee += 1;
                    nbrDead += r.getNbrPeople();
                }
            }while (cond);

//            System.out.println(r+" Cost = "+r.getCost());
        }
        //
        //if (nbrDead ==0){nbrDeadStr = "Aucun mort.";}
        // else if (nbrDead ==1){nbrDeadStr = "1 mort.";}
        // else {nbrDeadStr = nbrDead + " morts.";}

        //if (nombreFuseeCrashee == 0){nbrFuseeCrashStr = "Aucun crash. ";}
        // else if (nombreFuseeCrashee == 1){nbrFuseeCrashStr = "1 fusée s'est crashée. ";}
        // else{nbrFuseeCrashStr = nombreFuseeCrashee+" fusées se sont crashées. ";}


        //System.out.println(nbrFuseeCrashStr + nbrDeadStr);

        double epsilon = 0.01;
        float alpha = 0.999f;
        String nbSimStr = Float.toString(number_of_simulation((spaceship.U) rocketArrayList.get(0), epsilon, alpha));
        System.out.println("Nombre de simulation nécessaires pour alpha = " + alpha + " et epsilon = "+epsilon+ ": " + nbSimStr);

        return new int[] {budget, nbrDead};
    }

}

