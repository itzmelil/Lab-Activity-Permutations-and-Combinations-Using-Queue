import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.println("[6] Dequeue Two Elements");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                case 6:
                    dequeueTwo();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    static void addElements() {
        System.out.print("Enter number of elements to add: ");
        int count = input.nextInt();
        if (count < 1) {
            System.out.println("Enter a positive number of elements.");
            return;
        }

        System.out.println("Enter " + count + " elements separated by spaces:");
        for (int i = 0; i < count; i++) {
            queue.add(input.next());
        }
        displayQueue();
    }

    static void displayQueue() {
        System.out.println("Queue: " + queue);
    }

    static void dequeueTwo() {
        for (int i = 0; i < 2 && !queue.isEmpty(); i++) {
            System.out.println("Front element: " + queue.peek());
            System.out.println("Dequeued: " + queue.poll());
        }
        displayQueue();
    }

    static void generatePermutations() {
        displayQueue();
        System.out.print("Enter number of elements to arrange: ");
        int count = input.nextInt();
        if (count < 1 || count > queue.size()) {
            System.out.println("Choose a number from 1 to the queue size.");
            return;
        }

        String[] elements = queue.toArray(new String[0]);
        long total = permute(elements, new boolean[elements.length], count, "");
        System.out.println("Total Permutations: " + total);
    }

    static long permute(String[] elements, boolean[] used, int remaining, String result) {
        if (remaining == 0) {
            System.out.println(result);
            return 1;
        }

        long total = 0;
        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                total += permute(elements, used, remaining - 1, result + elements[i]);
                used[i] = false;
            }
        }
        return total;
    }

    static void generateCombinations() {
        displayQueue();
        System.out.print("Enter number of elements to select: ");
        int count = input.nextInt();
        if (count < 1 || count > queue.size()) {
            System.out.println("Choose a number from 1 to the queue size.");
            return;
        }

        String[] elements = queue.toArray(new String[0]);
        long total = combine(elements, 0, count, "");
        System.out.println("Total Combinations: " + total);
    }

    static long combine(String[] elements, int start, int remaining, String result) {
        if (remaining == 0) {
            System.out.println(result);
            return 1;
        }

        long total = 0;
        // Only consider later positions so each selection is counted once.
        for (int i = start; i <= elements.length - remaining; i++) {
            total += combine(elements, i + 1, remaining - 1, result + elements[i]);
        }
        return total;
    }
}
