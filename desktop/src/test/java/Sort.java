import org.o7.Fire.Glopion.Brain.NeuralFunction;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] ints = new int[48];
        NeuralFunction.assignRandom(ints);
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
