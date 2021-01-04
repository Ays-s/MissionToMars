package spaceship;


public class Item {
    private String name;
    private int weight;
    private int cost;
    private int nbrPeople;


    public Item(String name, int weight, int cost, int nbrPeople) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.nbrPeople = nbrPeople;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNbrPeople() {
        return nbrPeople;
    }

    public void setNbrPeople(int nbrPeople) {
        this.nbrPeople = nbrPeople;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", nbrPeople=" + nbrPeople;
    }
}
