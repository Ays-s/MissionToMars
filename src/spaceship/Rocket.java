package spaceship;


public class Rocket implements Spaceship {
    public int cost;
    public int weight;
    private final float maxWeight;
    public int nbrPeople;

    public Rocket(int weight, int maxWeight, int cost, int nbrPeople) {
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.cost = cost;
        this.nbrPeople = nbrPeople;
    }

    public int getCost() {
        // Getter de l'attribut cost
        return cost;
    }
    public void setCost(int cost){
        this.cost = cost;
    }

    public int getNbrPeople() {
        // Getter de l'attribut cost
        return nbrPeople;
    }
    public void setNbrPeople(int nbrPeople){
        this.nbrPeople = nbrPeople;
    }

    public int getWeight() {
        return weight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    @Override
    public boolean canCarry(Item item) {
        // La méthode doit vérifier si la fusée peut porter l'item
        // auquel cas elle renverra true et false sinon
        return (this.maxWeight >= (item.getWeight() + this.weight));

    }

    @Override
    public void carry(Item item) {
        // La méthode doit mettre à jour le poids de
        // la fusée en y ajoutant celui de l'item
        int newWeight = item.getWeight() + this.getWeight();
        int newCost = item.getCost() + this.getCost();
        int newNbrPeople = item.getNbrPeople() + this.nbrPeople;
        setWeight(newWeight);
        setCost(newCost);
        setNbrPeople(newNbrPeople);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "weight=" + weight +
                ", cost=" + cost +
                ", nbrPeople=" + nbrPeople +
                '}';
    }
}
