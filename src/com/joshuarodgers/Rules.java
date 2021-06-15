package com.joshuarodgers;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;
import java.util.PrimitiveIterator;
import java.util.function.Predicate;

public class Rules {
    static final LinkedHashSet<String> values = new LinkedHashSet<>(Arrays.asList("K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"));
    static final LinkedHashSet<String> suits = new LinkedHashSet<>(Arrays.asList("C", "D", "H", "S")); // Clubs, Diamonds, Hearts, Spades
    static Hashtable<String, Integer> ranking = init_ranking();

    private static Hashtable<String, Integer> init_ranking(){
        if(ranking == null){
            ranking = new Hashtable<>();
            PrimitiveIterator.OfInt rank = IntStream.range(0, 13).iterator();
            values.forEach(v->ranking.put(v, Integer.valueOf(rank.nextInt())));
        }
        System.out.println(ranking);
        System.out.println(suits);
        return ranking;
    }

    public static int get_rank(String value){
        return ranking.get(value);
    }

    public static boolean is_valid(String play_from, String play_to){
        Predicate<String> first = s->values.contains(s.substring(0, 1).toUpperCase());
        Predicate<String> second = s->suits.contains(s.substring(1, 2).toUpperCase());
        boolean result_from;
        boolean result_to;

        if(play_from.length() > 2){
            first = s->values.contains(s.substring(0, 2).toUpperCase());
            second = s->suits.contains(s.substring(2).toUpperCase());
        }

        result_from = first.and(second).test(play_from);

        first = s->values.contains(s.substring(0, 1).toUpperCase());
        second = s->suits.contains(s.substring(1, 2).toUpperCase());

        if(play_to.length() > 2){
            first = s->values.contains(s.substring(0, 2).toUpperCase());
            second = s->suits.contains(s.substring(2).toUpperCase());
        }

        result_to = first.and(second).test(play_to);

        return result_from && result_to;
    }
    
    public static boolean validate(String[] tokens){
        boolean result1 = Arrays.stream(tokens).filter(t->t.length() > 2).anyMatch(s->values.contains(s.substring(0,2)));
    }
}
