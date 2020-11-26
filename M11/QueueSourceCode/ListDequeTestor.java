import java.util.Arrays;
import java.util.List;

public class ListDequeTestor {
    public static void main(String[] args) {
        // *** to use, temporarily make the instance data variables in ArrayDeque public

        ListDeque<String> wordDeque = new ListDeque<String>();

        try {
            String front = wordDeque.removeFront();
            System.out.println("removeFront incorrectly finds queue not empty\n");
        } catch(EmptyQueueException ex) {
            System.out.println("removeFront correctly finds deque empty\n");
        }

        try {
            String front = wordDeque.removeBack();
            System.out.println("removeBack incorrectly finds queue not empty\n");
        } catch(EmptyQueueException ex) {
            System.out.println("removeBack correctly finds deque empty\n");
        }

        System.out.println("add to front: amps");
        wordDeque.addToFront("amps");
        printQueue(wordDeque);

        System.out.println("add to front: bank");
        wordDeque.addToFront("bank");
        printQueue(wordDeque);

        System.out.println("add to back: card");
        wordDeque.addToBack("card");
        printQueue(wordDeque);

        System.out.println("add to front: door");
        wordDeque.addToFront("door");
        printQueue(wordDeque);

        System.out.println("add to back: ever");
        wordDeque.addToBack("ever");
        printQueue(wordDeque);

        System.out.println("remove from back");
        wordDeque.removeBack();
        printQueue(wordDeque);

        System.out.println("remove from back");
        wordDeque.removeBack();
        printQueue(wordDeque);

        System.out.println("remove from back");
        wordDeque.removeBack();
        printQueue(wordDeque);

        System.out.println("remove from front");
        wordDeque.removeFront();
        printQueue(wordDeque);

        System.out.println("add to back: frog");
        wordDeque.addToBack("frog");
        printQueue(wordDeque);

        System.out.println("add to front: golf");
        wordDeque.addToFront("golf");
        printQueue(wordDeque);

        System.out.println("remove from back");
        wordDeque.removeBack();
        printQueue(wordDeque);

        System.out.println("add to front: hats");
        wordDeque.addToFront("hats");
        printQueue(wordDeque);

        System.out.println("add to back: joke");
        wordDeque.addToBack("joke");
        printQueue(wordDeque);

        System.out.println("add to back: laugh");
        wordDeque.addToBack("laugh");
        printQueue(wordDeque);

        System.out.println("add to front: iris");
        wordDeque.addToFront("iris");
        printQueue(wordDeque);

    }

    private static void printQueue(ListDeque deque) {
        System.out.println(Arrays.toString(new List[]{deque.list})) ;
    }

}
