package cs112.ud3.models;

import java.util.Random;

public class Slots extends Game {

    //CONSTRUCTORS

    public Slots() {
        super("Slots", 100, 2);
    }

    public Slots(String name, double bet, double odds) {
        super(name, bet, odds);
    }

    public Slots(Game other) throws IllegalArgumentException {
        if (other == null || !(other instanceof Slots)){
            throw new IllegalArgumentException("Null or non Slots object in Slots copy constructor");
        } else {
            Slots o = (Slots) other;
            setAll(o.getName(), o.getBet(), o.getOdds());
        }
    }

    //EQUALS

    public boolean equals(Game other) {
        if (other == null || (!(other instanceof Slots))) {
            return false;
        }

        Slots o = (Slots) other;
        return (this.getName().equals(o.getName())) && (this.getBet() == o.getBet()) && (this.getOdds() == o.getOdds());
    }

    //PLAY

    public String play(){
        Random ran = new Random();
        int ran1 = ran.nextInt(100);
        int ran2 = ran.nextInt(100);
        int ran3 = ran.nextInt(100);

        int slot1 = outcome(ran1);
        int slot2 = outcome(ran2);
        int slot3 = outcome(ran3);

        String message = slot1 + "" + slot2 + "" + slot3;

        if ((slot1 == slot2) && (slot2 == slot3)){
            if (slot1 == 0){
                setOdds(3);
            } else if (slot1 == 1){
                setOdds(8);
            } else if (slot1 == 2){
                setOdds(30);
            } else if (slot1 == 3){
                setOdds(200);
            } else {
                setOdds(100000);
            }
            message += "Full Match!" + (getOdds() * getBet());
        } else if ((slot1 == slot2) || (slot1 == slot3) || (slot2 == slot3)){
            if (slot1 == slot2){
                if (slot1 == 0){
                    setOdds(0.1);
                } else if (slot1 == 1){
                    setOdds(.5);
                } else if (slot1 == 2){
                    setOdds(1);
                } else if (slot1 == 3){
                    setOdds(3);
                } else {
                    setOdds(1000);
                }
            } else if (slot1 == slot3){
                if (slot1 == 0){
                    setOdds(0.1);
                } else if (slot1 == 1){
                    setOdds(.5);
                } else if (slot1 == 2){
                    setOdds(1);
                } else if (slot1 == 3){
                    setOdds(3);
                } else {
                    setOdds(1000);
                }
            } else {
                if (slot2 == 0){
                    setOdds(0.1);
                } else if (slot2 == 1){
                    setOdds(.5);
                } else if (slot2 == 2){
                    setOdds(1);
                } else if (slot2 == 3){
                    setOdds(3);
                } else {
                    setOdds(1000);
                }
            }
            message += "Partial Match!" + (getOdds() * getBet());
        } else {
            message += "No Match!0";
        }

        //example return: 011Partial Match!20
        //System.out.println(message);
        return message;
    }

    //HELPER

    public int outcome(int num){
        if (num < 40){
            return 0;
        } else if (num < 70){
            return 1;
        } else if (num < 90){
            return 2;
        } else if (num < 99){
            return 3;
        } else {
            return 4;
        }
    }
}
