import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class scannerExercise {

    private static String askName(Scanner scanner) {
        System.out.print("Enter your name: "); // print for user input, doesn't immediately move to the next line.
        String name = scanner.nextLine();
        return name;
    }

    private static int askQuantity(Scanner scanner, String item) {
        int qty = -1;
        while (qty <= 0) {
            System.out.println("How many " + item + "(s)?");
            try {
                qty = scanner.nextInt();
                scanner.nextLine(); // clear the leftover new line
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer, try again.");
                scanner.nextLine(); // clear the invalid input from the buffer.
            }
        }
        return qty;
    }

    private static List<String> askShoppingList(Scanner scanner) {
        List<String> shoppingList = new ArrayList<>();
        System.out.println("Enter items one per line. Type done after finished");
        while (true) {
            System.out.print("Items: ");
            String item = scanner.nextLine();
            if(item.equalsIgnoreCase("done"))
                break;
            if(item.isEmpty()) {
                shoppingList.add(item);
            }
        }
        return shoppingList;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // 1. Read a name from a console
        System.out.println("===== 1. Read a name =====");
        String name = askName(scanner);
        System.out.println("Welcome " + name + ".\n");

        // 2. Read an Int
        System.out.println("==== 2. Read an int =====");
        int qty = askQuantity(scanner, "coffee");
        System.out.println("You want " + qty + " coffees.\n");

        // 3. Reading Multiple Items
        System.out.println("==== 3. Reading Multiple Items =====");
        

    }


}
