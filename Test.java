import java.util.*;
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        // initialise instance variables
        x = 0;
    }

    public void test() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(4);
        queue.add(2);
        queue.add(3);
        queue.add(1);
        while (queue.isEmpty() == false) {
            System.out.printf("%d ", queue.remove());
        }
    }
}
