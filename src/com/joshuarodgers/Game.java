package com.joshuarodgers;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

class Game{
    Stack<Card> deck;
    Stack<Card> waste;
    List<Stack<Card>> tableau;

    Game(){
        deck = new Stack<>();
        init_deck();
        shuffle_deck();
        deal();
    }

    private void init_deck(){
        List<String> suits = Arrays.asList("\3", "\4", "\5", "\6");
        suits.stream().forEach(s->Rules.ranking.keySet().stream().forEach(v->deck.push(new Card(s, v))));
    }

    private void shuffle_deck(){
        Random r = new Random();
        List<Integer> nums = new ArrayList<>();
        PrimitiveIterator.OfInt i = IntStream.range(0,52).iterator();
        IntConsumer ic = n->nums.add(Integer.valueOf(n));
        i.forEachRemaining(ic);
        Card[] new_deck = new Card[52];
        deck.stream().forEach(c->new_deck[nums.remove(r.nextInt(nums.size()))] = c);
        deck = new Stack<Card>();
        Arrays.stream(new_deck).forEach(c -> deck.push(c));
    }

    private void deal(){
        /* tableau is a list of stacks
        to deal iterate through outer 
        add(new Stack<Card>()) 
        deal (push) outer.size() cards in each
        peek top and turn face up 
        once dealt store all top cards in list
        moves search top cards list 
        if found and move valid
        pop from from current, push to new face up 
        update top cards */

        tableau = new ArrayList<>();
        // Create a stack to represent each column in tableau
        // then "deal" column number cards into stack
        // using current size of tableau to represent column number
        IntStream.range(0, 7).forEach(i->{
            tableau.add(new Stack<Card>());
            IntStream.range(0, tableau.size()).forEach((j->tableau.get(i).push(deck.pop())));
        });
        IntStream.range(0, 7).forEach(i->tableau.get(i).peek().is_face_up = true);
        tableau.forEach(s->s.forEach(c->System.out.println("Column:" + tableau.indexOf(s) + " " + c.suit + c.value)));
    }

    public static void main(String[] args) {
        new Game();
    }
}