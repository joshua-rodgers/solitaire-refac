package com.joshuarodgers;

public class Card {
    String suit;
    String value;
    boolean is_face_up = false;
    int rank;

    Card(String suit, String value){
        this.suit = suit;
        this.value = value;
        this.rank = Rules.get_rank(value);
    }
}
