package cs112.ud3.models;

import java.util.Random;

public class RockPaperScissors extends Game implements Comparable<String> {

    //INSTANCE VARIABLES
    private final String R = "Rock";
    private final String P = "Paper";
    private final String S = "Scissors";

    private String choice;

    //CONSTRUCTORS

    public RockPaperScissors() {
        super("Rock Paper Scissors", 100, 2);
        setChoice("Rock");
    }

    public RockPaperScissors(String name, double bet, double odds, String choice) {
        super(name, bet, odds);
        setChoice(choice);
    }

    public RockPaperScissors(Game other) throws IllegalArgumentException {
        if (other == null || !(other instanceof RockPaperScissors)){
            throw new IllegalArgumentException("Null or non RockPaperScissors object in RockPaperScissors copy constructor");
        } else {
            RockPaperScissors o = (RockPaperScissors) other;
            setAll(o.getName(), o.getBet(), o.getOdds(), o.getChoice());
        }
    }

    //GETTERS

    public String getChoice() {

        return this.choice;
    }

    //SETTERS

    public void setChoice(String choice) throws IllegalArgumentException {
        if (choice == null || choice.isEmpty()) {
            throw new IllegalArgumentException("Invalid string for Game name");
        } else {
            this.choice = choice;
        }
    }

    public void setAll(String name, double bet, double odds, String choice) {
        super.setAll(name, bet, odds);
        setChoice(choice);
    }

    //TOSTRING

    public String toString() {

        return "Name: " + getName() + " || Bet: " + getBet() + " || Odds: " + getOdds() + " || Choice: " + getChoice();
    }

    //EQUALS

    public boolean equals(Game other) {
        if (other == null || (!(other instanceof RockPaperScissors))) {
            return false;
        }

        RockPaperScissors o = (RockPaperScissors) other;
        return (this.getName().equals(o.getName())) && (this.getBet() == o.getBet()) && (this.getOdds() == o.getOdds() && (this.getChoice() == o.getChoice()));
    }

    //COMPARETO

    public int compareTo(String other) throws IllegalArgumentException{
        if (other == null || other.isEmpty()) {
            throw new IllegalArgumentException("Invalid string for RockPaperScissors comparison");
        }
        if (choice.equals(R)){
            if (other.equals(R)){
                return 0;
            } else if (other.equals(P)){
                return -1;
            } else {
                return 1;
            }
        } else if (choice.equals(P)){
            if (other.equals(R)){
                return 1;
            } else if (other.equals(P)){
                return 0;
            } else {
                return -1;
            }
        } else {
            if (other.equals(R)){
                return -1;
            } else if (other.equals(P)){
                return 1;
            } else {
                return 0;
            }
        }
    }

    //PLAY

    public String play() {
        Random ran = new Random();
        String[] options = new String[]{R, P, S};
        String otherChoice = options[ran.nextInt(3)];
        int comparison = compareTo(otherChoice);

        //example return: Paper,You Win!50
        if (comparison == 0){
            return otherChoice + ",Draw!" + getBet();
        } else if (comparison == -1){
            return otherChoice + ",Opponent Wins!" + 0;
        } else {
            return otherChoice + ",You Win!" + (getBet() * getOdds());
        }
    }
}
