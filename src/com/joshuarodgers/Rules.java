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
        Predicate<String> v_len3 = s->values.contains(s.substring(0,2).toUpperCase());
        Predicate<String> v_len2 = s->values.contains(s.substring(0,1).toUpperCase());
        Predicate<String> s_len3 = s->suits.contains(s.substring(2).toUpperCase());
        Predicate<String> s_len2 = s->suits.contains(s.substring(1).toUpperCase());
        
        boolean result1;
        boolean result2;

        if(play_from.length() > 2){
            result1 = v_len3.and(s_len3).test(play_from);
        }else{
            result1 = v_len2.and(s_len2).test(play_from);
        }

        if(play_to.length() > 2){
            result2 = v_len3.and(s_len3).test(play_to);
        }else{
            result2 = v_len2.and(s_len2).test(play_to);
        }
        return result1 && result2;
    }
}
