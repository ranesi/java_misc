package com.ranesi;

/**
 * Created by RH Anesi on 1/27/2017.
 */
public class Card {
    public static final String[] suits = new String[]{
            "Hearts", "Diamonds", "Spades", "Clubs"
    };
    public static final String[] ranks = new String[]{
            "Ace", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen", "King"
    };

    private int rank, suit;

    public Card(int r, int s){
        rank = 0;
        suit = 0;
        rank = r;
        suit = s;
    }

    public String strCard(){
        String temp = String.format("%s of %s", ranks[this.rank], suits[this.suit]);
        return temp;
    }

    public void setSuit(int s) { this.suit = s; }

    public void setRank(int r) { this.rank = r; }

    public int getSuit(){ return this.suit; }

    public int getRank() { return this.rank; }
}
