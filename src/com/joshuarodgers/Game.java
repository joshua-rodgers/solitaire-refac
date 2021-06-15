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
import java.util.Scanner;

class Game{
    Stack<Card> deck;
    Stack<Card> waste;
    List<Stack<Card>> tableau;
    boolean is_running;

    Game(){
        init_deck();
        shuffle_deck();
        deal();
        show();
        is_running = true;
    }

    private void init_deck(){
        this.deck = new Stack<>();
        List<String> suits = Arrays.asList("\3", "\4", "\5", "\6");
        suits.forEach(s->Rules.ranking.keySet().forEach(v->deck.push(new Card(s, v))));
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

        this.tableau = new ArrayList<>();
        // Create a stack to represent each column in tableau
        // then "deal" column number cards into stack
        // using current size of tableau to represent column number
        // top card of each stack turned face up
        IntStream.range(0, 7).forEach(i->{
            tableau.add(new Stack<Card>());
            IntStream.range(0, tableau.size()).forEach((j->tableau.get(i).push(deck.pop())));
        });
        IntStream.range(0, 7).forEach(i->tableau.get(i).peek().is_face_up = true);
    }

    public void show(){
        /* 
        Print each column, representing face down cards as an '*'
        print a newline when at the last card in column
        */
        tableau.forEach(s->s.forEach(c->{
            if(c.is_face_up)
                System.out.print(c.value + c.suit + " ");
            else
                System.out.print("* ");

            if(s.indexOf(c) == s.size() - 1)
                System.out.print("\n");
        })); 
    }

    public void proc_input(String input){
        String[] tokens = input.split(" ");

        if(tokens.length != 2){
            System.out.println("INVALID PLAY.");
        }else if(tokens[0].length() > 3 || tokens[1].length() > 3){
            System.out.println("INVALID PLAY.");
        }else{
            //test
            System.out.println(Rules.is_valid(tokens[0], tokens[1]));
        }

        show();

    }

    public static void main(String[] args) {
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        while(g.is_running){
            System.out.print("PLAY: ");
            g.proc_input(sc.nextLine());
        }
    }
}