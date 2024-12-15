package cs112.ud3.models;

public abstract class Game {

    //INSTANCE VARIABLES

    private String name; //Name of the game

    private double bet; //Amount of money being bet

    private double odds; //How much your money is multiplied if you win

    //CONSTRUCTORS

    public Game() {

        setAll("Game", 100, 2);
    }

    public Game(String name, double bet, double odds) {

        setAll(name, bet, odds);
    }

    public Game(Game other) throws IllegalArgumentException {
        if (other == null){
            throw new IllegalArgumentException("Null object in Game copy constructor");
        } else {
            setAll(other.getName(), other.getBet(), other.getOdds());
        }
    }

    //GETTERS

    public String getName() {
        return this.name;
    }

    public double getBet() {

        return this.bet;
    }

    public double getOdds() {

        return this.odds;
    }

    //SETTERS

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid string for Game name");
        } else {
            this.name = name;
        }
    }

    public void setBet(double bet) throws IllegalArgumentException{
        if (bet <= 0) {
            throw new IllegalArgumentException("Invalid double for Game bet");
        } else {
            double round = (int) (bet * 100);
            if (round == 0){
                round = 1;
            }
            this.bet = round / 100;
        }
    }

    public void setOdds(double odds) throws IllegalArgumentException{
        if (odds <= 0) {
            throw new IllegalArgumentException("Invalid double for Game odds");
        } else {
            this.odds = odds;
        }
    }

    public void setAll(String name, double bet, double odds) {
        setName(name);
        setBet(bet);
        setOdds(odds);
    }

    //TOSTRING

    public String toString() {

        return "Name: " + name + " || Bet: " + bet + " || Odds: " + odds + "";
    }

    //EQUALS

    public boolean equals(Game other) {
        if (other == null || (!(other instanceof Game))) {
            return false;
        }

        Game o = (Game) other;
        return (this.name.equals(o.getName())) && (this.bet == o.getBet()) && (this.odds == o.getOdds());
    }

    //PLAY

    public abstract String play();
}
