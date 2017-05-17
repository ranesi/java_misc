package com.ranesi;

import java.util.*;

public class Main {

    static Scanner strScan = new Scanner(System.in);
    static Scanner numScan = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        boolean quit = false, playerCorrect, compCorrect;
        int guess;
        //Create some lists of cards to test logic
        List<Card> player;
        player = new ArrayList<>(Arrays.asList(
                new Card(2, 0), new Card(11, 3),
                new Card(3, 2), new Card(3, 0)
        ));
        List<Card> comp;
        comp = new ArrayList<>(Arrays.asList(
                new Card(2, 0), new Card(12, 3),
                new Card(6, 3), new Card(7, 1)
        ));
        while(!quit){
            printHand(player, "Player");
            printHand(comp, "Computer");
            System.out.println("Guess a rank (0-12");
            guess = numScan.nextInt();
            playerCorrect = false;
            for (int card = 0; card < comp.size(); card++){
                if (guess == comp.get(card).getRank()){

                    player.add(new Card(
                            comp.get(card).getRank(),
                            comp.get(card).getSuit()
                    ));
                    System.out.println(String.format(
                            "Got a %s", player.get(player.size() - 1).strCard()
                    ));
                    comp.remove(card);
                    playerCorrect = true;
                    break;
                }
            }
            if (!playerCorrect) System.out.println("GO FISH which doesn't do anything right now");
            guess = compGuess();
            System.out.println(String.format("Computer guesses %d",guess));
            compCorrect = false;
            for (int card = 0; card < player.size(); card++){
                if (guess == player.get(card).getRank()) {
                    comp.add(new Card(
                            player.get(card).getRank(),
                            player.get(card).getSuit()
                    ));
                    System.out.println(String.format(
                            "Computer got a %s", comp.get(comp.size() - 1).strCard()
                    ));
                    player.remove(card);
                    compCorrect = true;
                    break;
                }
            }
            if (!compCorrect) System.out.println("Computer GOES FISH which doesn't do anything right now");
        }
    }

    public static void printHand(List<Card> hand, String name){
        //Prints the contents of a hand
        System.out.println(String.format("This is %s's hand: ", name));
        for (int card = 0; card < hand.size(); card++){
            System.out.println(hand.get(card).strCard());
        }
        System.out.println();
    }

    public static int compGuess(){
        int guess = random.nextInt(13);
        return guess;
    }

}
