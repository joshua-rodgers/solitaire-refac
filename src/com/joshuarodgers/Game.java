package com.joshuarodgers;
import java.util.Arrays;
import java.util.Stack;
import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

class Game{
    Stack<Card> deck;
    Stack<Card> waste;

    Game(){
        deck = new Stack<>();
        init_deck();
    }

    private void init_deck(){
        List<String> suits = Arrays.asList("\3", "\4", "\5", "\6");
        suits.stream().forEach(s->Rules.ranking.keySet().stream().forEach(v->deck.push(new Card(s, v))));
        deck.stream().forEach(s->System.out.println(s.suit + " " + s.value));
        System.out.println(deck.size());
    }
    
    public static void main(String[] args) {
        new Game();
    }
}