import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashMap;

public class loops {
    public static void main(String[] args) {
        //1 for loops 1-5 (Counting from 1 to 5)
        System.out.println("==== for loop =======");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        //2 - while loop (Countdown from 5 to 1)
        System.out.println("\n==== while loops =======");
        int countDown = 3;
        while (countDown > 0) {
            System.out.println("Countdown = " + countDown);
            countDown--;
        }

        //3 - Do while loop (Runs the body first and then checks the condition)
        System.out.println("\n==== Do while loops =======");
        int attempts = 1;
        do {
            System.out.println("Attempts = " + attempts);
            attempts++;
        } while (attempts <= 3);


        // 4- BREAK (exits the loop)
        System.out.println("\n====== for loop break ========");
        for (int i = 1; i <= 5; i++) {
            if (i == 4) {
                System.out.println("Found 4 - Stopping");
                break;
            }
            System.out.println("i = " + i);
        }

        //5 - break and continue
        System.out.println("\n====== for loop break ========");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                System.out.println("Found 3 - Skipping");
                continue;
            }
            System.out.println("i = " + i);
        }

        //6 - Array (holds multiple values of the same type in a single variable)
        System.out.println("\n ======== Array ===========");
        // String[] fruits = new String[5];
        String[] fruits = {"Apple", "Mangoes", "Banana"};

        System.out.println(fruits[0]);
        System.out.println(fruits[1]); // Mangoes
        System.out.println(fruits[2]);
        System.out.println("Length of fruits: " + fruits.length);

        //NESTED LOOPS
        System.out.println("\n===== NESTED LOOPS========");
        String[] drinks = {"Coffee", "Tea", "Juice"};
        String[] sizes = {"Small", "Medium", "Large"};

        // Variant 1: indexing
        System.out.println("\n===== NESTED LOOPS (Variant 1: Indexing========");
        for (int i = 0; i < drinks.length; i++) {
            for (int j = 0; j < sizes.length; j++) {
                System.out.println(sizes[j] + " " + drinks[i]);
            }
        }

        // Variant 2: for each
        System.out.println("\n===== NESTED LOOPS ( Variant 2: For each) ========");
        for (String drink : drinks) {
            for (String size : sizes) {
                System.out.println(size + " " + drink);
            }
        }

        // 7 Hashmap
        System.out.println();
        Double[]prices = new Double[5];
        HashMap<String, Double> map = new HashMap<>();



        // 8. Array list
        System.out.println("\n======= Array List =========");
        ArrayList<String> order = new ArrayList<>();
        order.add("Coffee");
        order.add("Tea");
        order.add("Juice");

        //customer decides to remove some product from the order
        order.remove("Coffee");
        System.out.println("Updated Order: " + order);

        //Calculate the Bill
        System.out.println("\n Bill");
        double total = 0;
        for(String item : order) {
        }
        System.out.println("Total Price: " + total);
    }
}