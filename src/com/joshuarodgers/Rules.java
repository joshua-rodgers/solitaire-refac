package com.joshuarodgers;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.PrimitiveIterator;

public class Rules {
    static Hashtable<String, Integer> ranking;

    private static Hashtable<String, Integer> init_ranking(){
        if(ranking == null){
            ranking = new Hashtable<>();
            String[] values = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
            PrimitiveIterator.OfInt rank = IntStream.range(0, 13).iterator();
            Arrays.stream(values).forEach(v->ranking.put(v, Integer.valueOf(rank.nextInt())));
        }
        return ranking;
    }

    public static int get_rank(String value){
        if(ranking == null)
            init_ranking();
            
        return ranking.get(value);
    }
}
