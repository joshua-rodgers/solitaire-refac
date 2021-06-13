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

    Game(){
        deck = new Stack<>();
        init_deck();
        shuffle_deck();
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

    public static void main(String[] args) {
        new Game();
    }
}