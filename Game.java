import java.util.Arrays;
import java.util.function.*;

class Game{
    public static void main(String[] args) {
        String s = "Well";
        String ss = "now";
        BiFunction<String, String, String> str_func = (s1, s2) -> s1 + " " + s2; 

        System.out.println(str_func.apply(s, ss));
    }
}